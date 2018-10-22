import java.sql.ResultSet;
import java.sql.SQLException;

public class Pack {
    private String codicePacco = null;
    private String destinatario = null;
    private String edificio = null;
    private String indirizzo = null;
    private String CAP = null;
    private String paese = null;
    private String provincia = null;
    private int consegnato;
    private String noteFattorino = null;
    private String dataScadenza = null;

    public Pack(String codicePacco, String destinatario, String edificio, String indirizzo, String CAP, String paese, String provincia, int consegnato, String noteFattorino, String dataScadenza) {
        this.codicePacco = codicePacco;
        this.destinatario = destinatario;
        this.edificio = edificio;
        this.indirizzo = indirizzo;
        this.CAP = CAP;
        this.paese = paese;
        this.provincia = provincia;
        this.consegnato = consegnato;
        this.noteFattorino = noteFattorino;
        this.dataScadenza = dataScadenza;
    }

    /** Costruttore che a partire da un record del database rappresentante un pacco istanzia il pacco
     * con le proprietà valorizzate */
    public Pack(ResultSet packRecord) throws SQLException {
        this(packRecord.getString("codicePacco"),
                packRecord.getString("destinatario"),
                packRecord.getString("edificio"),
                packRecord.getString("indirizzo"),
                packRecord.getString("CAP"),
                packRecord.getString("paese"),
                packRecord.getString("provincia"),
                packRecord.getInt("consegnato"),
                packRecord.getString("noteFattorino"),
                packRecord.getString("dataScadenza"));
    }

    public String getCodicePacco() {
        return codicePacco;
    }

    public void setCodicePacco(String codicePacco) {
        this.codicePacco = codicePacco;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    public String getPaese() {
        return paese;
    }

    public void setPaese(String paese) {
        this.paese = paese;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getConsegnato() {
        return consegnato;
    }

    public void setConsegnato(int consegnato) {
        this.consegnato = consegnato;
    }

    public String getNoteFattorino() {
        return noteFattorino;
    }

    public void setNoteFattorino(String noteFattorino) {
        this.noteFattorino = noteFattorino;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }
}
