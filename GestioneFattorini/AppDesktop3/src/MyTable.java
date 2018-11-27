import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MyTable extends JTable{

    public MyTable(){

        super();

    }

    public void addColumns(String[] n){

        DefaultTableModel model = (DefaultTableModel) this.getModel();

        this.setModel(model);

        for(int i = 0; i< n.length ; i++){

            model.addColumn(n[i]);
        }

        this.validate();
    }

    public void clean() {
        DefaultTableModel model = (DefaultTableModel) this.getModel();

        while(model.getRowCount()>0) {
            model.removeRow(0);
        }
        model.setColumnCount(0);
        this.setModel(model);
        this.validate();
    }

    public void addRow(String[] fields) {
        DefaultTableModel model = (DefaultTableModel) this.getModel();

        model.addRow(fields);

        this.setModel(model);
        this.validate();
    }

}
