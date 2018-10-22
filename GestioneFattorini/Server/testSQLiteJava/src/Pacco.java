/**
 * Created by inf.francoa3103 on 08/10/2018.
 */
public class Pacco {
    private String codice;
    private double peso;
    private String descrizione;

    public Pacco() {}

    public Pacco(String codice, double peso, String descrizione) {
        this.codice = codice;
        this.peso = peso;
        this.descrizione = descrizione;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCodice() {
        return codice;
    }

    public double getPeso() {
        return peso;
    }

    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public String toString() {
        return "[Pacco : " + this.codice + ";" + this.peso + ";" + this.descrizione + "]";
    }
}
