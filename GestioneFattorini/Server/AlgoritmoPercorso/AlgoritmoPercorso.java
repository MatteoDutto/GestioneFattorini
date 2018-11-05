import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by inf.golettos1701 on 29/10/2018.
 */
public class AlgoritmoPercorso {

    public static String indirizzoMagazzino = null;

    public static void main(String[] args) {

    }

    public ArrayList<String> calcolaPercorso(ArrayList<Pacco> pacchi) {
        ArrayList<String> percorso = null;

        calcolaCoordinate(pacchi);

        Map<Pacco, Map<Pacco, Double>> grafoPacchi = creaGrafoPacchi(pacchi);

        percorso = calcolaOrdineIndirizzi(grafoPacchi);

        return percorso;
    }

    //Crea un'Arrylist con gli indirizzi dei pacchi ordinati secondo il percorso più veloce
    private ArrayList<String> calcolaOrdineIndirizzi(Map<Pacco, Map<Pacco, Double>> grafoPacchi) {

    }

    //DistanceMatrix -> crea il grafo dei pacchi
    private Map<Pacco,Map<Pacco,Double>> creaGrafoPacchi(ArrayList<Pacco> pacchi) {

        Map<Pacco, Map<Pacco, Double>> grafo = new Map<Pacco, Map<Pacco, Double>>();

        for()
    }

    //Per ogni pacco setta la latitudine e la longitudine -->Geocoding
    private void calcolaCoordinate(ArrayList<Pacco> pacchi) {

        for(Pacco pacco:pacchi){

        }
    }
}
