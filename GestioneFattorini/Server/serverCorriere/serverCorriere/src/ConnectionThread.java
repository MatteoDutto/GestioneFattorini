import GestioneCorriere.Util.*;

import java.io.*;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Creato da Andrea Delmastro, Alessio Franco <br><br>
 * Classe che rappresenta un thread di connessione con un client.
 * Ogni volta che un client richiede una connessione al server, quando la
 * connessione viene accettata, viene creato un thread a lui riservato.
 * Il thread si occupa di leggere il messaggio (vedi classe Message) inviatogli
 * dal client e di operare sul DB in relazione a quanto scritto nel messaggio.
 * Il thread è responsabile anche della comunicazione di risposta verso il client. <br><br>
 */
public class ConnectionThread extends Thread {

    /** Socket di comunicazione con il client */
    private Socket client = null;

    /** Costruttore che riceve il socket relativo al client connesso */
    public ConnectionThread(Socket client) {
        this.client = client;
        /* Avvio del metodo run() */
        System.out.println(MessagesList.CONNECTION_CLIENT_ESTABLISHED + client.getInetAddress().toString() + ":" + client.getPort());
        this.start();
    }

    @Override
    public void run() {

        try {

            Message clientMessage = null;
            clientMessage = MessageUtil.recvMessageFrom(client);

            /* Switch effettuato su tutte le possibili richieste inoltrabili al server */
            if (clientMessage.getOperation().equals(DatabaseOperations.SELECT_PACK_FROM_ID))  selectPackFromID(clientMessage);
            else if(clientMessage.getOperation().equals(DatabaseOperations.SELECT_ALL_PACKS))  selectAllPacks(clientMessage);
            else if(clientMessage.getOperation().equals(DatabaseOperations.SELECT_ALL_NON_SENT_PACKS)) selectAllNonSentPacks(clientMessage);
            else if(clientMessage.getOperation().equals(DatabaseOperations.INSERT_PACK)) insertPack(clientMessage);
            else if(clientMessage.getOperation().equals(DatabaseOperations.REMOVE_PACK_FROM_ID)) removePackFromID(clientMessage);
            else if(clientMessage.getOperation().equals(DatabaseOperations.SELECT_DELIVERY_MAN_FROM_ID)) selectDeliveryManFromID(clientMessage);
            else if(clientMessage.getOperation().equals(DatabaseOperations.SELECT_ALL_DELIVERY_MEN)) selectAllDeliveryMen(clientMessage);
            else if(clientMessage.getOperation().equals(DatabaseOperations.INSERT_DELIVERY_MAN)) insertDeliveryMan(clientMessage);
            else if(clientMessage.getOperation().equals(DatabaseOperations.REMOVE_DELIVERY_MAN_FROM_ID)) removeDeliveryManFromID(clientMessage);
            else if(clientMessage.getOperation().equals(DatabaseOperations.SELECT_ALL_PACKS_FROM_DELIVERY_MAN_ID)) selectAllPacksFromDeliveryManID(clientMessage);
            else throw new IOException();

            /* Chiusura del socket */
            client.close();

        } catch(IOException ioe) { ErrorList.defaultServerClosingOperation(client, ErrorList.CLIENT_REQUEST_ERROR, this); }
          catch(SQLException sqle) { ErrorList.defaultServerClosingOperation(client, ErrorList.SQL_REQUEST_ERROR, this); }
          catch(ClassNotFoundException cnfe) { ErrorList.defaultServerClosingOperation(client, ErrorList.CLIENT_REQUEST_ERROR, this); }
    }

    /** Metodo che seleziona e invia al client un pacco basandosi su un determinato ID */
    private void selectPackFromID(Message clientMessage) throws SQLException, IOException {
        String sql = "SELECT * FROM pacchi WHERE codicePacco = ?";
        /* Creazione del comando SQL da lanciare */
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
        /* Inserimento dei parametri nel comando */
        ps.setString(1, clientMessage.getPackOptions().getCodicePacco());

        /* Acquisizione del risultatato */
        ResultSet res = ps.executeQuery();

        /* Costruzione del pacco sulla base del record ritornato dal DB */
        res.next();
        clientMessage.getResPacks().add(new Pack(res));

        MessageUtil.sendMessageTo(client, clientMessage);
    }

    /** Metodo che seleziona e invia al client tutti i pacchi disponibili nel sistema sotto forma di ArrayList di Pacchi */
    private void selectAllPacks(Message clientMessage) throws SQLException, IOException {
        String sql = "SELECT * FROM pacchi";

        /* Creazione del comando SQL da lanciare */
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);

        /* Acquisizone del risultato */
        ResultSet res = ps.executeQuery();

        /* Per ogni riga del risultato viene creato un pacco corrispondente */
        while(res.next()) clientMessage.getResPacks().add(new Pack(res));

        /* Invio del pacco al client */
        MessageUtil.sendMessageTo(client, clientMessage);
    }

    /** Metodo che seleziona e invia al client tutti i pacchi da inviare nel sistema sotto forma di ArrayList di Pacchi */
    private void selectAllNonSentPacks(Message clientMessage) throws SQLException, IOException {
        String sql = "SELECT * FROM pacchi WHERE consegnato = 0";

        /* Creazione del comando SQL da lanciare */
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);

        /* Costruzione del pacco sulla base del record ritornato dal DB */
        Pack pack = new Pack(ps.executeQuery());

        /* Acquisizone del risultato */
        ResultSet res = ps.executeQuery();

        /* Per ogni riga del risultato viene creato un pacco corrispondente */
        while(res.next()) clientMessage.getResPacks().add(new Pack(res));

        /* Invio del pacco al client */
        MessageUtil.sendMessageTo(client, clientMessage);
    }

    /** Metodo che aggiunge un pacco al database */
    private void insertPack(Message clientMessage) throws SQLException, IOException {
        String sql = "INSERT INTO pacchi VALUES(?,?,?,?,?,?,?,?,?,?)";

        /* Creazione del comando SQL da lanciare */
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);

        ps.setString(1, clientMessage.getPackOptions().getCodicePacco());
        ps.setString(2, clientMessage.getPackOptions().getDestinatario());
        ps.setString(3, clientMessage.getPackOptions().getEdificio());
        ps.setString(4, clientMessage.getPackOptions().getIndirizzo());
        ps.setString(5, clientMessage.getPackOptions().getCAP());
        ps.setString(6, clientMessage.getPackOptions().getPaese());
        ps.setString(7, clientMessage.getPackOptions().getProvincia());
        ps.setString(8, Integer.toString(clientMessage.getPackOptions().getConsegnato()));
        ps.setString(9, clientMessage.getPackOptions().getNoteFattorino());
        ps.setString(10, clientMessage.getPackOptions().getDataScadenza());

        ps.executeUpdate();

        MessageUtil.sendMessageTo(client, clientMessage);
    }

    /** Metodo che rimuove un pacco dal database basandosi su un determinato ID */
    private void removePackFromID(Message clientMessage) throws SQLException, IOException {
        String sql = "DELETE FROM pacchi WHERE codicePacco = ?";

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);

        ps.setString(1, clientMessage.getPackOptions().getCodicePacco());

        ps.executeUpdate();

        MessageUtil.sendMessageTo(client, clientMessage);
    }

    /** Metodo che seleziona un fattorino basandosi su un determinato ID */
    private void selectDeliveryManFromID(Message clientMessage) throws SQLException, IOException {
        String sql = "SELECT * FROM Fattorini WHERE codiceFattorino = ?";
        /* Creazione del comando SQL da lanciare */
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
        /* Inserimento dei parametri nel comando */
        ps.setString(1, clientMessage.getDelManOptions().getCodiceFattorino());

        /* Acquisizione del risultatato */
        ResultSet res = ps.executeQuery();

        /* Costruzione del pacco sulla base del record ritornato dal DB */
        res.next();

        clientMessage.getResDelMan().add(new DeliveryMan(res));

        MessageUtil.sendMessageTo(client, clientMessage);
    }

    /** Metodo che seleziona tutti i fattorini */
    private void selectAllDeliveryMen(Message clientMessage) throws SQLException, IOException {
        String sql = "SELECT * FROM fattorini";

        /* Creazione del comando SQL da lanciare */
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);

        /* Acquisizone del risultato */
        ResultSet res = ps.executeQuery();

        /* Per ogni riga del risultato viene creato un pacco corrispondente */
        while(res.next()) clientMessage.getResDelMan().add(new DeliveryMan(res));

        /* Invio del pacco al client */
        MessageUtil.sendMessageTo(client, clientMessage);
    }

    /** Metodo che aggiunge un fattorino al database */
    private void insertDeliveryMan(Message clientMessage) throws SQLException, IOException {
        String sql = "INSERT INTO fattorini VALUES(?,?,?,?,?)";

        /* Creazione del comando SQL da lanciare */
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);

        ps.setString(1, clientMessage.getDelManOptions().getCodiceFattorino());
        ps.setString(2, clientMessage.getDelManOptions().getNome());
        ps.setString(3, clientMessage.getDelManOptions().getCognome());
        ps.setString(4, clientMessage.getDelManOptions().getPaese());
        ps.setString(5, clientMessage.getDelManOptions().getCAP());

        ps.executeUpdate();

        MessageUtil.sendMessageTo(client, clientMessage);
    }

    /** Metodo che rimuove un fattorino dal database basandosi su un determinato ID */
    private void removeDeliveryManFromID(Message clientMessage) throws SQLException, IOException {
        String sql = "DELETE FROM fattorini WHERE codiceFattorino = ?";

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);

        ps.setString(1, clientMessage.getDelManOptions().getCodiceFattorino());

        ps.executeUpdate();

        MessageUtil.sendMessageTo(client, clientMessage);
    }

    /** Metodo che seleziona tutti i pacchi asseganti ad un dato fattorino */
    private void selectAllPacksFromDeliveryManID(Message clientMessage) throws SQLException, IOException {
        /* Andrebbero restituiti in ordine in base all'algoritmo di Dutto */
        String sql = "SELECT * FROM pacchi WHERE codFatt = ?";

        /* Creazione del comando SQL da lanciare */
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);

        ps.setString(1, clientMessage.getDelManOptions().getCodiceFattorino());

        /* Acquisizone del risultato */
        ResultSet res = ps.executeQuery();

        /* Per ogni riga del risultato viene creato un pacco corrispondente */
        while(res.next()) clientMessage.getResPacks().add(new Pack(res));

        /* Invio del pacco al client */
        MessageUtil.sendMessageTo(client, clientMessage);
    }
 }
