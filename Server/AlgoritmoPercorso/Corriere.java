import java.util.ArrayList;

/**
 * Created by inf.golettos1701 on 01/10/2018.
 */
public class Corriere {

    /*Mock Class*/
    static final double PESO_MASSIMO = 100; //Cercare il peso massimo di un corriere
    static final double DIMENSIONE_MASSIMA = 10000; //idem

    private String codiceCorriere= null;
    private double caricoDimensione=0;
    private double caricoPeso=0;
    private String cittaCAP=null;
    private ArrayList<Pacco> elencoPacchi=null;

    public Corriere(String codiceCorriere, String cittaCAP) {
        this.codiceCorriere = codiceCorriere;
        this.cittaCAP = cittaCAP;
        this.elencoPacchi = new ArrayList<Pacco>();
    }

    public String getCodiceCorriere() {
        return codiceCorriere;
    }

    public double getCaricoDimensione() {
        return caricoDimensione;
    }

    public double getCaricoPeso() {
        return caricoPeso;
    }

    public String getCittaCAP() {
        return cittaCAP;
    }

    public void setCaricoDimensione(double caricoDimensione) {
        this.caricoDimensione = caricoDimensione;
    }

    public void setCaricoPeso(double caricoPeso) {
        this.caricoPeso = caricoPeso;
    }

    public void setCittaCAP(String cittaCAP) {
        this.cittaCAP = cittaCAP;
    }

    public void addPacco(Pacco pacco){
        elencoPacchi.add(pacco);
    }

    public ArrayList<Pacco> getElencoPacchi() {
        return elencoPacchi;
    }
}
