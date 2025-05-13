package controle;

import modelo.ArquivoAtor;
import entidades.Ator;
import modelo.ArquivoAtuacao;

import java.util.List;
import java.util.ArrayList;

public class ControleAtor {
    private ArquivoAtor arqAtor;

    /*
     * Construtor da classe ControleAtor
     */
    public ControleAtor() throws Exception {
        this.arqAtor = new ArquivoAtor();
    }

    /*
     * incluirAtor - Função para adicionar um Ator ao Banco de Dados
     * @param a - Ator a ser inserido
     * @return id - ID do Ator inserido
     */
    public int incluirAtor(Ator a) throws Exception {
        if (a == null)
            throw new Exception("Ator nulo!");

        int id = arqAtor.create(a);
        return id;
    }

    /*
     * excluirAtor - Função para excluir um Ator a partir do seu ID
     * @param id - ID do Ator a ser excluído
     * @return boolean - True se bem sucedido, False caso contrário
     */
    public boolean excluirAtor(int id) throws Exception {
        return arqAtor.delete(id);
    }

    /*
     * excluirAtor - Função para excluir um Ator a partir do seu objeto
     * @param a - Ator a ser excluído
     * @return boolean - True se bem sucedido, False caso contrário
     */
    public boolean excluirAtor(Ator a) throws Exception {
        if (a == null)
            throw new Exception("Ator nulo!");

        return arqAtor.delete(a.getID());
    }

    /*
     * verificarAtuacoesAtor - Função estática para verificar se existem atuações para um Ator
     * @param idAtor - ID do Ator a ser testado
     * @return resposta - True se existir alguma atuação vinculada, False caso contrário
     */
    public static boolean verificarAtuacoesAtor(int idAtor) {
        boolean resposta;

        try {
            ArquivoAtor arqAtor = new ArquivoAtor();
            int[] atuacoes = arqAtor.readAtuacao(idAtor);
            resposta = (atuacoes.length > 0);
        } catch (Exception e) {
            resposta = false;
        }

        return resposta;
    }

    /*
     * povoar - Função para popular o Banco de Dados com Atores amostrais
     */
    public void povoar() {
        try {
            incluirAtor(new Ator("Bryan Cranston", 'M', java.time.LocalDate.of(1956, 3, 7), "Estados Unidos"));
            incluirAtor(new Ator("Aaron Paul", 'M', java.time.LocalDate.of(1979, 8, 27), "Estados Unidos"));
            incluirAtor(new Ator("Anna Gunn", 'F', java.time.LocalDate.of(1968, 8, 11), "Estados Unidos"));
            System.out.println("\nAtores povoados!");
        } catch (Exception e) {
            System.err.println("\n[ERRO]: " + e.getMessage());
        }
    }
}
