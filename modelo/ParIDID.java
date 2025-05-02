package modelo;

import java.io.*;

import aeds3.RegistroArvoreBMais;

public class ParIDID implements RegistroArvoreBMais<ParIDID> {
    private int IDSerie;
    private int IDEpisodio;
    private final short TAMANHO = 8;

    /**
     * Construtor padrão, inicializa com valores -1
     */
    public ParIDID() {
        this.IDSerie = -1;
        this.IDEpisodio = -1;
    }

    /**
     * Construtor que recebe os IDs da série e do episódio
     * @param idS (int): ID da série
     * @param idEp (int): ID do episódio
     */
    public ParIDID(int idS, int idEp) {
        this.IDSerie = idS;
        this.IDEpisodio = idEp;
    }

    /**
     * Retorna o ID da série
     * @return (int) ID da série
     */
    public int getIDSerie() {
        return IDSerie;
    }

    /**
     * Retorna o ID do episódio
     * @return (int) ID do episódio
     */
    public int getIDEpisodio() {
        return IDEpisodio;
    }

    /**
     * Retorna o tamanho do registro
     * @return (short) Tamanho em bytes
     */
    public short size() {
        return TAMANHO;
    }

    /**
     * Converte o objeto para um array de bytes
     * @return (byte[]) Representação serializada do objeto
     */
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(IDSerie);
        dos.writeInt(IDEpisodio);
        return baos.toByteArray();
    }

    /**
     * Preenche o objeto a partir de um array de bytes
     * @param ba (byte[]): Array de bytes contendo os dados
     */
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.IDSerie = dis.readInt();
        this.IDEpisodio = dis.readInt();
    }

    /**
     * Compara este objeto com outro ParIDID baseado no ID do Episódio
     * @param obj (ParIDID): Objeto a ser comparado
     * @return (int) Valor negativo, zero ou positivo conforme a ordem crescente de IDs
     */
    public int compareTo(ParIDID obj) {
        if (this.IDSerie != obj.IDSerie)
            return this.IDSerie - obj.IDSerie;
        else
        // Só compara os valores de id2, se o id2 da busca for diferente de -1
        // Isso é necessário para que seja possível a busca de lista
            return this.IDEpisodio == -1 ? 0 : this.IDEpisodio - obj.IDEpisodio;
    }

    /**
     * Retorna uma cópia do objeto
     * @return (ParIDID) Clone do objeto atual
     */
    public ParIDID clone() {
        return new ParIDID(this.IDSerie, this.IDEpisodio);
    }

    /**
     * Representação textual do objeto
     * @return (String) Representação formatada do par de IDs
     */
    @Override
    public String toString() {
        return "(Série: " + IDSerie + ", Episódio: " + IDEpisodio + ")";
    }
}
