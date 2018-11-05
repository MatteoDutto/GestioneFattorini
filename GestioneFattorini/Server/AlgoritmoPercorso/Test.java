import java.util.ArrayList;

/**
 * Created by inf.golettos1701 on 08/10/2018.
 *
 *Mock Class (Pacco)
 */
public class Test {

    private ArrayList<Pacco> elencoPacchi = null;
    private ArrayList<Corriere> elencoCorrieri = null;

    public Test() {
        elencoPacchi=new ArrayList<Pacco>();
        elencoCorrieri=new ArrayList<Corriere>();
        init();

    }

    public ArrayList<Corriere> getElencoCorrieri() {
        return elencoCorrieri;
    }

    public ArrayList<Pacco> getElencoPacchi() {
        return elencoPacchi;
    }

    public void init() {
        int i;
        for (i = 0; i < 10; i++) {
            elencoPacchi.add(new Pacco(String.valueOf(i), "12010", "Via Fasulla 123", 10.1, 30.0, Priorità.ALTA));
        }
        for (i = 0; i<10; i++) {
            elencoPacchi.add(new Pacco(String.valueOf(i+10), "12100", "Via Roma 476", 7.1, 24.0, Priorità.ALTA));
        }
        for (i = 0; i<10; i++) {
            elencoPacchi.add(new Pacco(String.valueOf(i+20), "12650", "Via papa 56", 7.1, 24.0, Priorità.ALTA));
        }

        elencoCorrieri.add(new Corriere("1", "12010"));
        elencoCorrieri.add(new Corriere("2", "12100"));
        elencoCorrieri.add(new Corriere("3", "12650"));
    }

    public void initReale(){

    }


}
