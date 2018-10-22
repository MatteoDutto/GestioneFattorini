import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Created by inf.francoa3103 on 15/10/2018.
 */
public class ConnectionThread extends Thread {

    /** Socket di comunicazione con il client */
    private Socket client = null;

    /** Costruttore che riceve il socket relativo al client connesso */
    public ConnectionThread(Socket client) {
        this.client = client;
        /* Avvio del metodo run() */
        this.start();
    }

    @Override
    public void run() {
        try {
            /* Acquisizione della stringa inviata dal client al server per definire
             * la richiesta */
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String socketRequest = br.readLine();

            /* Divisione della stringa in due parti: la prima parte identifica la richiesta,
             * la seconda parte identifica i dati relativi alla richiesta */
            String[] socketRequestSplit = socketRequest.split(":");

            /* Switch effettuato su tutte le possibili richieste inoltrabili al server */
            if(socketRequestSplit[0].equals(DatabaseOperations.SELECT_PACK_FROM_ID)) {

                /* Richiesta di prelevare un pacco sulla base del suo identificativo */
                String sql = "SELECT * FROM pacchi WHERE codicePacco = ?";
                /* Creazione del comando SQL da lanciare */
                PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
                /* Inserimento dei parametri nel comando */
                ps.setString(1, socketRequestSplit[1]);

                /* Costruzione del pacco sulla base del record ritornato dal DB */
                Pack pack = new Pack(ps.executeQuery());

                /* Invio del pacco al client */
                sendObjectToClient(client, (Object)pack);

            }else if(socketRequestSplit[0].equals(DatabaseOperations.SELECT_DELIVERY_MAN_FROM_ID)) {

            }

        } catch(IOException ioe) {
            System.out.println(Error.CLIENT_REQUEST_ERROR);
            SocketUtil.closeSocket(client);
            Error.exitError(Error.CLIENT_REQUEST_ERROR);
        } catch(SQLException sqle) {
            System.out.println(Error.SQL_REQUEST_ERROR);
            SocketUtil.closeSocket(client);
            Error.exitError(Error.SQL_REQUEST_ERROR);
        }
    }

    /** Metodo che ricevuto un oggetto lo invia in rete al socket desiderato */
    public void sendObjectToClient(Socket client, Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        oos.writeObject(obj);
        oos.close();
    }
}
