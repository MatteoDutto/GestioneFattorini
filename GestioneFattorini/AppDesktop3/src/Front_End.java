import GestioneCorriere.Util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class Front_End extends JFrame {

    MyTable myTable = new MyTable();

    public Front_End(){

        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void init() throws IOException {

        this.setTitle("AppDesktop");
        this.setSize(1000,800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton btnPacchi = new JButton("Visualizza pacchi");
        JButton btnFattorini = new JButton("Visualizza fattorini");
        JScrollPane jspFrame = new JScrollPane(myTable);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel1.add(btnPacchi);
        panel1.add(btnFattorini);

        this.add(jspFrame, BorderLayout.NORTH);
        this.add(panel1, BorderLayout.CENTER);

        Socket socket = GestioneCorriere.Util.SocketUtil.openSocket("127.0.0.1", 3103);

        btnPacchi.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                myTable.clean();

                String colonne[] = {"Codice del pacco","Destinatario", "Edificio", "Indirizzo", "CAP", "Paese", "Provincia", "Consegnato", "Note del fattorino", "Data di scadenza", "Codice del fattorino"};
                myTable.addColumns(colonne);

                Message m = new Message(DatabaseOperations.SELECT_ALL_PACKS);
                try {
                    MessageUtil.sendMessageTo(socket, m);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Message retMessage = null;
                try {
                    retMessage = MessageUtil.recvMessageFrom(socket);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

                for(Pack pack : retMessage.getResPacks()){

                    String[] componenti = {pack.getCodicePacco(), pack.getDestinatario(), pack.getEdificio(), pack.getIndirizzo(), pack.getCAP(), pack.getPaese(), pack.getProvincia(), String.valueOf(pack.getConsegnato()), pack.getNoteFattorino(), pack.getDataScadenza(), pack.getCodFatt()};

                    myTable.addRow(componenti);

                }

                SocketUtil.closeSocket(socket);
            }
        });
        btnFattorini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                myTable.clean();

                String colonne[] = {"Codice del fattorino", "Cognome", "Nome", "CAP", "Paese"};
                myTable.addColumns(colonne);

                Message m = new Message(DatabaseOperations.SELECT_ALL_DELIVERY_MEN);
                try {
                    MessageUtil.sendMessageTo(socket, m);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Message retMessage = null;
                try {
                    retMessage = MessageUtil.recvMessageFrom(socket);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

                for(DeliveryMan deliveryMan : retMessage.getResDelMan()){

                    String[] componenti = {deliveryMan.getCodiceFattorino(), deliveryMan.getCognome(), deliveryMan.getNome(), deliveryMan.getCAP(), deliveryMan.getPaese()};
                    myTable.addRow(componenti);

                }

                SocketUtil.closeSocket(socket);
            }
        });

        this.setVisible(true);
    }

    public static void main(String[] args) {

        Front_End front_End = new Front_End();
    }

}

