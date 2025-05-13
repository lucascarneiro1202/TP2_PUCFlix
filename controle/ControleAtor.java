package controle;

import modelo.ArquivoAtor;
import modelo.ArquivoAtuacao;
import entidades.Ator;
import entidades.Atuacao;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ControleAtor {
    private ArquivoAtor arqAtor;
    private Ator ator;

    /*
     * Construtor da classe ControleAtor sem a vinculação de Ator específico
     */
    public ControleAtor() throws Exception {
        this.arqAtor = new ArquivoAtor();
    }

    /*
     * Construtor da classe ControleAtor com a vinculação de Ator
     * @param ator - Ator referente à classe atual
     */
    public ControleAtor(Ator ator) throws Exception {
        if (ator == null)
            throw new Exception("Ator nulo!");

        if (!validarAtor(ator.getID()))
            throw new Exception("Ator inválido!");

        this.arqAtor = new ArquivoAtor();
        this.ator = ator;
    }

    /*
     * incluirAtor - Função para adicionar um Ator ao Banco de Dados
     * @param a - Ator a ser inserido
     * @return id - ID do Ator inserido
     */
    public int incluirAtor(Ator a) throws Exception {
        if (a == null)
            throw new Exception("Ator nulo!");
        return arqAtor.create(a);
    }

    /*
     * excluirAtor - Função para excluir um Ator pelo ID
     * @param id - ID do Ator a ser excluído
     * @return boolean - true se bem sucedido, false caso contrário
     */
    public boolean excluirAtor(int id) throws Exception {
        return arqAtor.delete(id);
    }

    /*
     * excluirAtor - Função para excluir um Ator a partir do seu objeto
     * @param a - Ator a ser excluído
     * @return boolean - true se bem sucedido, false caso contrário
     */
    public boolean excluirAtor(Ator a) throws Exception {
        if (a == null)
            throw new Exception("Ator nulo!");
        return arqAtor.delete(a.getID());
    }

    /*
     * alterarAtor - Função para atualizar os dados de um Ator
     * @param novo - Objeto Ator alterado
     * @return boolean - true se bem sucedido
     */
    public boolean alterarAtor(Ator novo) throws Exception {
        if (novo == null)
            throw new Exception("Ator nulo!");
        return arqAtor.update(novo);
    }

    /*
    * buscarAtor - Retorna todos os Atores cadastrados
    * @return Lista de todos os Atores
    */
    public List<Ator> buscarAtor() throws Exception {
        // Faz uma leitura com prefixo vazio que retorna todos do índice
        Ator[] resultado = arqAtor.readNome("");
        return new ArrayList<>(Arrays.asList(resultado));
    }


    /*
     * buscarAtor - Retorna lista de Atores cujo nome começa com a string fornecida
     * @param entrada - prefixo do nome a ser buscado
     * @return Lista de atores encontrados
     */
    public List<Ator> buscarAtor(String entrada) throws Exception {
        Ator[] resultado = arqAtor.readNome(entrada);
        return new ArrayList<>(Arrays.asList(resultado));
    }

    /*
     * buscarAtor - Retorna ator por ID, se for igual ao da instância
     * @param id - ID a ser buscado
     * @return Ator encontrado
     */
    public Ator buscarAtor(int id) throws Exception {
        Ator a = arqAtor.read(id);
        if (a == null)
            throw new Exception("Ator não encontrado!");
        return a;
    }

    /*
     * buscarAtuacaoAtor - Retorna as atuações em que o Ator atuou
     * @param idAtor - ID do Ator
     * @return List<Atuacao> de atuacoes
     */
    public List<Atuacao> buscarAtuacaoAtor(int idAtor) throws Exception {
        ArquivoAtuacao arqAtuacao = new ArquivoAtuacao();
        return arqAtuacao.readAtor(idAtor);
    }

    /*
     * validarAtor - Verifica se o ID do Ator é válido no banco
     * @param idAtor - ID do Ator
     * @return boolean - true se existir, false caso contrário
     */
    public static boolean validarAtor(int idAtor) {
        boolean resposta;
        try {
            ArquivoAtor arqAtor = new ArquivoAtor();
            Ator a = arqAtor.read(idAtor);
            resposta = (a != null);
        } catch (Exception e) {
            resposta = false;
        }
        return resposta;
    }

    /*
     * povoar - Insere atores fictícios para testes
     */
    public void povoar() {
        try {
            incluirAtor(new Ator("Bryan Cranston", 'M', java.time.LocalDate.of(1956, 3, 7), "EUA"));
            incluirAtor(new Ator("Aaron Paul", 'M', java.time.LocalDate.of(1979, 8, 27), "EUA"));
            incluirAtor(new Ator("Anna Gunn", 'F', java.time.LocalDate.of(1968, 8, 11), "EUA"));
            System.out.println("\nAtores povoados com sucesso!");
        } catch (Exception e) {
            System.err.println("\n[ERRO]: " + e.getMessage());
        }
    }
}
