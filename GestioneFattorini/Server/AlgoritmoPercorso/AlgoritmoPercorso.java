import java.net.MalformedURLException;
import java.util.*;
import java.lang.Math;
import java.net.URL;

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

        double distanza = 0;
        Map<Pacco, Map<Pacco, Double>> grafo = new HashMap<Pacco, Map<Pacco, Double>>();

        for(Pacco pacco : pacchi){
            Map<Pacco, Double> temp = new HashMap<>();

            for(Pacco distPacco : pacchi) {
                distanza = Math.sqrt(Math.pow(distPacco.getLat(), 2) * Math.pow(distPacco.getLongi(), 2)) -
                        Math.sqrt(Math.pow(pacco.getLat(), 2) * Math.pow(pacco.getLongi(), 2));
                distanza = Math.abs(distanza);
                if (distanza != 0) {
                    temp.put(distPacco, distanza);
                }
            }
            grafo.put(pacco, temp);
        }

        return grafo;
    }

    //Per ogni pacco setta la latitudine e la longitudine -->Geocoding
    private void calcolaCoordinate(ArrayList<Pacco> pacchi) throws MalformedURLException {

        URL url = null;

        for(Pacco pacco:pacchi){
            String[] temp = pacco.getIndirizzo().split("\\s+");
            /*https://maps.googleapis.com/maps/api/geocode/json?address=<Numero Civico + Via, +Città, +Stato>&key=API_KEY*/
            String indirizzo = "http://example.com";

            url = new URL(indirizzo);

        }
    }
}
