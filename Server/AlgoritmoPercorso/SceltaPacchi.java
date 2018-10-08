import java.util.ArrayList;

/**
 * Created by inf.golettos1701 on 01/10/2018.
 */
public class SceltaPacchi
{
    private Test test = null;

    public SceltaPacchi(Test test) {
        this.test = test;
    }

    /*Return Arraylist
    public void getCorrieri(){
        /*Riceve corrieri dal DB
    }*/

    /*Return Arraylist*/
    public void sceltaCaricoPacchi(){
        /*Riceve quali pacchi deve caricare sul corriere*/
        ArrayList<Pacco> elencoPacchi=test.getElencoPacchi();
        ArrayList<Corriere> elencoCorrieri=test.getElencoCorrieri();

        for(Pacco pacco:elencoPacchi){
            String cap = pacco.getCittaCAP();
            for(Corriere corriere:elencoCorrieri){
                if(cap.equals(corriere.getCittaCAP())){
                    corriere.addPacco(pacco);
                    break;
                }
            }
        }
    }


    /*Return Arraylist
    public void getPacchi(){
        /*Riceve i dati dei pacchi dal DB


    }*/


}
