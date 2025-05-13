package visao;

import entidades.Ator;
import controle.ControleAtor;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class VisaoAtor {

    private static Scanner console = new Scanner(System.in);
    ControleAtor controleAtor;

    /*
     * Construtor da classe VisaoAtor
     */
    public VisaoAtor() throws Exception {
        this.controleAtor = new ControleAtor();
    }

    /*
     * menu - Função principal de menu da VisaoAtor
     * @param versao - Versão do sistema
     */
    public void menu(String versao) throws Exception {
        int opcao;

        do {
            // Cabeçalho do menu
            System.out.println("\nPUCFlix v" + versao);
            System.out.println("--------------------------");
            System.out.println("> Início > Atores");
            System.out.println("1 - Incluir Ator");
            System.out.println("2 - Excluir Ator");
            System.out.println("3 - Alterar Ator");
            System.out.println("4 - Buscar Um Ator");
            System.out.println("5 - Buscar Atuações de Um Ator");
            System.out.println("0 - Sair");
            System.out.print("\nOpção: ");

            // Leitura da opção
            try {
                opcao = Integer.parseInt(console.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            // Execução da ação conforme a opção
            switch (opcao) {
                case 1: incluirAtor();
                case 2: excluirAtor();
                case 3: alterarAtor();
                case 4: mostrarAtor(buscarUmAtor());
                case 5: buscarAtuacoes();
                case 0: System.out.println("Saindo...");
                default: System.err.println("[ERRO]: Opção inválida!");
            }

        } while (opcao != 0);
    }

    /*
     * incluirAtor - Função para incluir um novo ator no sistema
     */
    public void incluirAtor() {
        try {
            Ator a = lerAtor();
            int id = controleAtor.incluirAtor(a);
            System.out.println("\nAtor incluído com sucesso! ID: " + id);
        } catch (Exception e) {
            System.err.println("\n[ERRO]: " + e.getMessage());
        }
    }

    /*
     * alterarAtor - Função para alterar um ator já existente
     */
    public void alterarAtor() {
        try {
            Ator antigo = buscarUmAtor();
            Ator novo = lerAtor(antigo);

            // Confirmação da alteração
            System.out.print("\nConfirma as alterações? (S/N) ");
            char resp = console.nextLine().charAt(0);
            if (resp == 'S' || resp == 's') {
                if (controleAtor.alterarAtor(novo)) {
                    System.out.println("\nAtor alterado com sucesso.");
                } else {
                    System.err.println("\n[ERRO]: Não foi possível alterar o ator.");
                }
            }
        } catch (Exception e) {
            System.err.println("\n[ERRO]: " + e.getMessage());
        }
    }

    /*
     * excluirAtor - Função para excluir um ator do sistema
     */
    public void excluirAtor() {
        try {
            Ator a = buscarUmAtor();

            // Confirmação da exclusão
            System.out.print("\nConfirma a exclusão? (S/N) ");
            char resp = console.nextLine().charAt(0);
            if (resp == 'S' || resp == 's') {
                if (controleAtor.excluirAtor(a)) {
                    System.out.println("\nAtor excluído com sucesso.");
                } else {
                    System.err.println("\n[ERRO]: Não foi possível excluir o ator.");
                }
            }
        } catch (Exception e) {
            System.err.println("\n[ERRO]: " + e.getMessage());
        }
    }

    /*
     * lerAtor - Lê os dados de um novo ator a ser incluído
     * @return Ator criado
     */
    public Ator lerAtor() throws Exception {
        String nome, nacionalidade;
        char genero;
        LocalDate dataNascimento;

        // Entrada do nome
        System.out.print("Qual o nome do ator? (mínimo 3 caracteres): ");
        nome = console.nextLine();
        if (nome.length() < 3) throw new Exception("Nome muito curto!");

        // Entrada do gênero
        System.out.print("Qual o gênero (M/F/I)? ");
        genero = console.nextLine().toUpperCase().charAt(0);
        if ("MFI".indexOf(genero) == -1) throw new Exception("Gênero inválido!");

        // Entrada da data de nascimento
        System.out.print("Qual a data de nascimento (yyyy-mm-dd)? ");
        dataNascimento = LocalDate.parse(console.nextLine());

        // Entrada da nacionalidade
        System.out.print("Qual a nacionalidade? ");
        nacionalidade = console.nextLine();

        // Confirmação
        System.out.print("\nConfirmar dados? (S/N) ");
        char confirm = console.nextLine().charAt(0);
        if (confirm == 'S' || confirm == 's')
            return new Ator(nome, genero, dataNascimento, nacionalidade);
        else
            throw new Exception("Inclusão cancelada.");
    }

    /*
     * lerAtor - Lê os dados de um ator existente e permite edição
     * @param antigo - Ator a ser editado
     * @return Ator novo com dados atualizados
     */
    public Ator lerAtor(Ator antigo) throws Exception {
        String nome, nacionalidade;
        char genero;
        LocalDate dataNascimento;

        System.out.print("Nome (" + antigo.getNome() + "): ");
        nome = console.nextLine();
        if (nome.isEmpty()) nome = antigo.getNome();

        System.out.print("Gênero (M/F/I) (" + antigo.getGenero() + "): ");
        String g = console.nextLine();
        genero = g.isEmpty() ? antigo.getGenero() : g.toUpperCase().charAt(0);

        System.out.print("Data de nascimento (" + antigo.getDataNascimento() + "): ");
        String dn = console.nextLine();
        dataNascimento = dn.isEmpty() ? antigo.getDataNascimento() : LocalDate.parse(dn);

        System.out.print("Nacionalidade (" + antigo.getNacionalidade() + "): ");
        nacionalidade = console.nextLine();
        if (nacionalidade.isEmpty()) nacionalidade = antigo.getNacionalidade();

        return new Ator(antigo.getID(), nome, genero, dataNascimento, nacionalidade);
    }

    /*
     * buscarUmAtor - Busca um único ator pelo nome
     * @return Ator selecionado
     */
    public Ator buscarUmAtor() throws Exception {
        List<Ator> atores = buscarAtorNome();
        if (atores == null || atores.isEmpty())
            throw new Exception("Nenhum ator encontrado!");

        if (atores.size() == 1)
            return atores.get(0);

        // Exibe opções para o usuário escolher
        for (int i = 0; i < atores.size(); i++) {
            System.out.println("(" + i + ") " + atores.get(i).getNome());
        }

        System.out.print("\nEscolha um número: ");
        int idx = Integer.parseInt(console.nextLine());
        return atores.get(idx);
    }

    /*
     * buscarAtorNome - Solicita entrada de nome e busca atores correspondentes
     * @return Lista de atores encontrados
     */
    public List<Ator> buscarAtorNome() {
        System.out.print("Nome: ");
        String nome = console.nextLine();
        try {
            return controleAtor.buscarAtor(nome);
        } catch (Exception e) {
            System.err.println("\n[ERRO]: " + e.getMessage());
            return null;
        }
    }

    /*
     * buscarAtuacoes - Busca as atuações de um ator
     */
    public void buscarAtuacoes() {
        try {
            Ator a = buscarUmAtor();
            int[] idsSeries = controleAtor.buscarAtuacaoAtor(a.getID());

            System.out.println("\nAtuações de " + a.getNome() + ":");
            for (int idSerie : idsSeries) {
                System.out.println("- ID da Série: " + idSerie);
            }
        } catch (Exception e) {
            System.err.println("\n[ERRO]: " + e.getMessage());
        }
    }

    /*
     * mostrarAtor - Exibe informações completas do ator
     * @param a - Ator a ser exibido
     */
    public void mostrarAtor(Ator a) {
        if (a != null) {
            System.out.println("\n" + a);
        }
    }
}
