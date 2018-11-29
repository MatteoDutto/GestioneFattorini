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

        //inizializzazione della tabella con il settaggio del titolo della grandezza e della chiusure del processo quando si preme la X della finestra
        this.setTitle("AppDesktop");
        this.setSize(1000,510);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //creazione degli oggetti presenti nelkla parte grafica, ovvero bottoni table textfield e scrollpane
        JButton btnPacchi = new JButton("Visualizza pacchi");
        JButton btnFattorini = new JButton("Visualizza fattorini");

        //creazione della scrollpane alla quale varrà passato il contenuto, in questo la caso la Table, pre creata
        JScrollPane jspFrame = new JScrollPane(myTable);

        JButton btnCerca = new JButton("Cerca...");
        JTextField txtCerca = new JTextField(20);

        JButton btnRimPacco = new JButton("Rimuovi pacco");
        JButton btnRimFatt = new JButton("Rimuovi fattorino");

        //Creazione dei 3 principali panel che andrtenno a comporre la parte inferiore della finestra alle quali verranno aggiunti i vari componenti
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel1.add(btnPacchi);
        panel1.add(btnFattorini);

        panel2.add(btnRimPacco);
        panel2.add(btnRimFatt);
        panel2.add(txtCerca);
        panel2.add(btnCerca);

        this.add(jspFrame, BorderLayout.NORTH);
        this.add(panel1, BorderLayout.CENTER);
        this.add(panel2,BorderLayout.SOUTH);

        //Apertura del socket verso il server: 127.0.0.1(IP) 3103(PORT)
        Socket socket = GestioneCorriere.Util.SocketUtil.openSocket("127.0.0.1", 3103);

        //aggiunto un listener che attiverà i comendi presenti al suo interno quando si premerà sul pulsante btnPacchi
        btnPacchi.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //si svuota la Table
                myTable.clean();

                //si creano le colonne che andranno ad indicare la Tabella dei pacchi
                String colonne[] = {"Codice del pacco","Destinatario", "Edificio", "Indirizzo", "CAP", "Paese", "Provincia", "Consegnato", "Note del fattorino", "Data di scadenza", "Codice del fattorino"};

                //aggiunte alla Table le colonne che identificano i campi
                myTable.addColumns(colonne);

                //Creazione del messaggio dove viene definita l'operazione da svolgere e le condizioni di ricerca
                Message m = new Message(DatabaseOperations.SELECT_ALL_PACKS);
                try {

                    //invio del messaggio al server
                    MessageUtil.sendMessageTo(socket, m);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                //Creazione del contenitore del messaggio di corretta ricezione
                Message retMessage = null;
                try {

                    //Ricezione del messaggio inviato dal server
                    retMessage = MessageUtil.recvMessageFrom(socket);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

                //Ciclo che per ogni pacco presente all'interno del db salvera in un vettore di stringe tutti i componenti del record andandoli poi ad inserire all'interno della table
                for(Pack pack : retMessage.getResPacks()){

                    String[] componenti = {pack.getCodicePacco(), pack.getDestinatario(), pack.getEdificio(), pack.getIndirizzo(), pack.getCAP(), pack.getPaese(), pack.getProvincia(), String.valueOf(pack.getConsegnato()), pack.getNoteFattorino(), pack.getDataScadenza(), pack.getCodFatt()};

                    myTable.addRow(componenti);

                }

                //chiusura del socket
                SocketUtil.closeSocket(socket);
            }
        });

        //aggiunto un listener che attiverà i comendi presenti al suo interno quando si premerà sul pulsante btnFattorini
        btnFattorini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //si svuota la tabella
                myTable.clean();

                //si creano le colonne che andranno ad indicare la Tabella dei Fattorini
                String colonne[] = {"Codice del fattorino", "Cognome", "Nome", "CAP", "Paese"};

                //aggiunte alla Table le colonne che identificano i campi
                myTable.addColumns(colonne);

                //Creazione del messaggio dove viene definita l'operazione da svolgere e le condizioni di ricerca
                Message m = new Message(DatabaseOperations.SELECT_ALL_DELIVERY_MEN);
                try {

                    //invio del messaggio al server
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

                //Ciclo che per ogni fattorino presente all'interno del db salvera in un vettore di stringe tutti i componenti del record andandoli poi ad inserire all'interno della table
                for(DeliveryMan deliveryMan : retMessage.getResDelMan()){

                    String[] componenti = {deliveryMan.getCodiceFattorino(), deliveryMan.getCognome(), deliveryMan.getNome(), deliveryMan.getCAP(), deliveryMan.getPaese()};
                    myTable.addRow(componenti);

                }

                //chiusura del socket
                SocketUtil.closeSocket(socket);
            }
        });

        this.setVisible(true);
    }

    //MAIN
    public static void main(String[] args) {

        Front_End front_End = new Front_End();
    }

}

