import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by inf.golettos1701 on 08/10/2018.
 */
public class AlgoritmoPercorso {
    public static String partenza = "Corso Nizza 43";
    private Corriere corriere = null;

    public AlgoritmoPercorso(Corriere corriere) {
        this.corriere = corriere;

    }

    public void createPercorso(){
        ArrayList<Object> nodi = new ArrayList<>();
        ArrayList<Pacco> elencoPacchi = corriere.getElencoPacchi();
        String address = null;

        String nextAddress = elencoPacchi.get(0).getIndirizzo();
        int cicli= elencoPacchi.size();
        for(int i=0; i<cicli; i++) {
            for (Pacco pacco : elencoPacchi) {
                address = pacco.getIndirizzo();
                if (true) {
                    nextAddress = address;
                }/* CONTROLLO INDIRIZZO PIU VICINO TRAMITE API*/

            }
            nodi.add(nextAddress);
            elencoPacchi.remove()
        }

    }
}
