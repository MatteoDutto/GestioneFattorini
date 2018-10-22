/**
 * Created by inf.golettos1701 on 01/10/2018.
 */
public class Pacco {
    /*
    Proprietà da Desde e Cico
     */

    private String codicePacco=null;
    private String cittaCAP=null;
    private String indirizzo=null;
    private double peso=0;
    private double dimensione=0;
    private Priorità priorità = Priorità.NULL;
    private Corriere corriere=null;
    private boolean esito=false;

    public Pacco(String codicePacco, String cittaCAP, String indirizzo, double peso, double dimensione, Priorità priorità) {
        this.codicePacco = codicePacco;
        this.cittaCAP = cittaCAP;
        this.indirizzo = indirizzo;
        this.peso = peso;
        this.dimensione = dimensione;
        this.priorità = priorità;
    }
    /*Geters and Setters*/
    public String getCodicePacco() {
        return codicePacco;
    }

    public String getCittaCAP() {
        return cittaCAP;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public double getPeso() {
        return peso;
    }

    public double getDimensione() {
        return dimensione;
    }

    public Priorità getPriorità() {
        return priorità;
    }

    public Corriere getCorriere() {
        return corriere;
    }

    public boolean isEsito() {
        return esito;
    }

    public void setCorriere(Corriere corriere) {
        this.corriere = corriere;
    }

    public void setEsito(boolean esito) {
        this.esito = esito;
    }


}
