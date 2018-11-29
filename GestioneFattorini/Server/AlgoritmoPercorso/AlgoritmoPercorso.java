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
        percorso = calcolaOrdineIndirizzi(grafoPacchi);

               /*
        I m going to tell you how we can use Graphopper for getting
        distance and time matrix on the basis of latitude and longitude.
        Graphopper is free and openSource web service as google map which
        is very useful for who want to develop an application where time
        and distance matter between 2 or more address, I use this service
        in JAVA but you can use several other languages. Graphopper gives
        a matrix API which calculates time and distance between 2 or more
        points on the basis of latitude and longitude and type vehicle.
        so step by step process for achieving this goal as shown in:

        Step1.

            we need to make a account on it. we can use this link.https://graphhopper.com/dashboard/#/register

        Step2.

            After that make Api key for Api.

        Step3.

            Add the maven dependency in your pom.Xml file.

            <dependency>
                <groupId>com.graphhopper</groupId>
                <artifactId>directions-api-client</artifactId>
                <version>0.9.0</version>
            </dependency>

        Step4.

            public MatrixResponse getMatrixData() {

                //Make a Object of Matrix Api.

                MatrixApi api = new MatrixApi();

                //Add 2 or more points
                List<String> point = getLatsLongsFromLocations();
                String fromPoint = null;
                String toPoint = null;

                //Add required field you want to get

                List<String> requiredFields = Arrays.asList("weights", "distances", "times");

                //Add Vehicle I use Car for it but You can use this link for supported vehicle https://graphhopper.com/api/1/docs/supported-vehicle-profiles/

                String vehicle = "car";
                MatrixResponse response = null;
                try {
                    response =  api.matrixGet("API Key", point, fromPoint, toPoint, requiredFields, vehicle);
                //Matrixreponse object have distance and time matrix given location.

                } catch (ApiException e) {
                e.printStackTrace();
                }
                return response;
            }

            public List<String> getLatsLongsFromLocations() {
                List<String> allPoints = new ArrayList<>();

                //I Add 2 location lat,long but you can add max 80 locations

                    allPoints.add("Latitude0"+ "," + "Logitude0");

                    allPoints.add("Latitude1"+ "," + "Logitude1");
                    return allPoints;
            }

For more information use this link.https://www.graphhopper.com/developers/.

thanks.*/

        return percorso;
    }

    //Crea un'Arrylist con gli indirizzi dei pacchi ordinati secondo il percorso più veloce
    //Utilizziamo GraphHopper (https://graphhopper.com/api/1/docs/) per calcolare
    //il percorso migliore (date le coordinate di tutti i pacchi del fattorino)
    //Graphopper calcola il percorso per tutti i pacchi in base ai fattorini disponibili
    //La funzione restituisce un ArrayList di dimensioni pari al numero dei fattorini
    //contenente la lista degli indirizzi già ordinati
    private ArrayList<String> calcolaOrdineIndirizzi(Map<Pacco, Map<Pacco, Double>> grafoPacchi) {
        //Graphopper routing optimazed api..
        // LINK: https://www.graphhopper.com/developers/ -> Documentations -> Route Optimization API
    }



    //Per ogni pacco setta la latitudine e la longitudine -->Geocoding
    //Geocoding è il processo di conversione degli indirizzi (come un indirizzo stradale)
    // in coordinate geografiche (come latitudine e longitudine), che è possibile utilizzare
    // per posizionare i marcatori su una mappa o posizionare la mappa.
    private void calcolaCoordinate(ArrayList<Pacco> pacchi) throws MalformedURLException {
    	//Numero civico - Via - Città - Stato - CAP
    	String indirizzo = null;
    	Geocoding codifica = new Geocoding();
        //JSONParse serve a fare il parse di una stringa per convertirla in un oggetto JSON
    	JSONParser parser = new JSONParser();
        //https://www.json.org/
		JSONObject json = null;

        //Per ogni pacco, preso l'indirizzo per la Geocoding, vengono calcolate la
        //latitudine e la longitudine
        for(Pacco pacco:pacchi){
            //Settaggio dell'indirizzo come richiesto per la Geocoding
        	indirizzo = pacco.getIndirizzo() + "," + pacco.getCitta() + "," + "Italy" + "," + pacco.getCittaCap();
            String results = codifica.getJSONByGoogle(indirizzo);
            //Oggetto JSON che preleva il risultato della Geocoding
           JSONObject json = (JSONObject) parser.parse(results);
            //Metodo utilizzato per prelevare dall'oggetto JSON
            //solamente la latitudine e la longitudine dell'inidirizzo del pacco
            //per poi venire settati alle proprietà del pacco
           pacco.setLat(getJSONObject("results").getJSONObject("location").getJSONString("lat"); 
           pacco.setLongi(getJSONObject("results").getJSONObject("location").getJSONString("lng");

        }
    }
}
