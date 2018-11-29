import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MyTable extends JTable{

    public MyTable(){

        super();

    }

    public void addColumns(String[] n){

        //Creazione del modello della tabella
        DefaultTableModel model = (DefaultTableModel) this.getModel();

        //si va a settare il nuovo modello all'interno della myTable
        this.setModel(model);

        //ciclo che esegue n giri pari al numero di campi presenti nel vettore di stringhe ricevuto
        for(int i = 0; i< n.length ; i++){

            //aggiunta delle colonne all'interno della Table
            model.addColumn(n[i]);
        }

        //validazione della table
        this.validate();
    }

    public void clean() {

        //Creazione del modello della tabella
        DefaultTableModel model = (DefaultTableModel) this.getModel();

        //esecuzione di un ciclo che non finisce fino a quando il numero delle righe all'interno della table non + uguale a 0
        while(model.getRowCount()>0) {

            //eliminazione della riga più in alto della tabella
            model.removeRow(0);
        }

        //eliminazione delle colonne
        model.setColumnCount(0);

        //settaggio del nuovo modello
        this.setModel(model);

        //validazione del modello della tabella
        this.validate();
    }

    public void addRow(String[] fields) {

        //Creazione del modello della tabella
        DefaultTableModel model = (DefaultTableModel) this.getModel();

        //si aggiunge alla tabella la riga composta da n campi salvati nel vettore di stringhe
        model.addRow(fields);

        //si va a settare il nuovo modello
        this.setModel(model);

        //si valida la tabella
        this.validate();
    }

}
