import java.io.*;

public class Oggetto implements Serializable {

    private static final long serialVersionUID = 5950169519310163575L;
    private String nomeOggetto = null;
    private int numeroOggetto = 0;

    public Oggetto(String nomeOggetto, int numeroOggetto) {
        this.nomeOggetto = nomeOggetto;
        this.numeroOggetto = numeroOggetto;
    }

    public String getNomeOggetto() {
        return nomeOggetto;
    }

    public void setNomeOggetto(String nomeOggetto) {
        this.nomeOggetto = nomeOggetto;
    }

    public int getNumeroOggetto() {
        return numeroOggetto;
    }

    public void setNumeroOggetto(int numeroOggetto) {
        this.numeroOggetto = numeroOggetto;
    }
}