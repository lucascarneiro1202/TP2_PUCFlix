import java.util.Scanner;

import controle.ControleSerie;
import controle.ControleEpisodio;
import visao.VisaoSerie;
import visao.VisaoEpisodio;

public class Principal {
    static String versao = "1.0";
    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        // Iniciar bloco try-catch
        try {
            // Definir variável auxiliar
            int opcao;
            // Iniciar bloco de seleção
            do {
                // Mostrar o cabeçalho
                System.out.println("\nPUCFlix v" + versao);
                System.out.println( "-----------");
                System.out.println("> Início");
                System.out.println("\n1 - Série");
                System.out.println("2 - Episódio");
                System.out.println("9 - Povoar dados");
                System.out.println("0 - Sair");
                // Ler a opção do usuário
                System.out.print("\nOpção: ");
                try {
                    opcao = Integer.valueOf(console.nextLine());
                } catch(NumberFormatException e) {
                    opcao = -1;
                }
                // Testar a opção do usuário
                switch (opcao) {
                    case 1: (new VisaoSerie()).menu(versao); break;
                    case 2: (new VisaoEpisodio()).menu(versao); break;
                    case 9: povoar(); break;
                    case 0: break;
                    default: System.err.println("\n[ERRO]: Opção inválida!"); break;
                }
            } while (opcao != 0);
        } catch(Exception e) {
            System.err.println("\n[ERRO]: " + e.getMessage());
        }
    }

    public static void povoar() {
        try {
            // Povoar as Séries antes
            ControleSerie controleSerie = new ControleSerie();
            controleSerie.povoar();
            // Povoar os Episódios depois
            ControleEpisodio controleEpisodio = new ControleEpisodio(controleSerie.buscarSerie(4));
            controleEpisodio.povoar();
        } catch (Exception e) {
            System.err.println("\n[ERRO]: " + e.getMessage());
        }
    }

}


