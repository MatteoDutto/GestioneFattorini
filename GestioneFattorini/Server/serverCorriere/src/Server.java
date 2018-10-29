import GestioneCorriere.Util.ErrorList;
import GestioneCorriere.Util.MessagesList;
import GestioneCorriere.Util.SocketUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Creato da Andrea Delmastro, Alessio Franco. <br><br>
 * La classe server contiene il metodo main.
 * Il main si occupa di aprire la connessione verso il database e verso il client.
 * Per ogni client viene creato un thread separato che si occupa della comunicazione
 * e dell'esecuzione delle operazioni sopra al database.
 */
public class Server {
    /* Socket relativo al server */
    private static ServerSocket serverSocket = null;
    /* Porta sulla quale risponde il server */
    private static final int serverPort = 3103;
    /* URL del Database */
    private static final String dbURL = "/home/delmastro/Pacchi.db";

    /**
     * Metodo che si occupa di aprire la connessione verso il database e verso il client.
     * Per ogni client viene creato un thread separato che si occupa della comunicazione
     * e dell'esecuzione delle operazioni sopra al database.
     */
    public static void main(String[] args) {

        /* Collegamento al database */
        try {
            DatabaseConnection.connect(dbURL);
            /* Segnalazione di corretta apertura della connessione verso il database */
            System.out.println(MessagesList.DATABASE_CONNECTION_ESTABLISHED);
        } catch(SQLException sqle) {
            /* Errore nella connessione con il database */
            System.out.println(ErrorList.DATABASE_CONNECTION_ERROR + ":" + sqle.toString());
            ErrorList.exitError(ErrorList.DATABASE_CONNECTION_ERROR);
        }
        /* ===== */

        /* Apertura del socket su una data porta */
        try {
            serverSocket = SocketUtil.openServerSocket(serverPort);
            /* Segnalazione della corretta apertura del socket */
            System.out.println(MessagesList.SOCKET_OPENING_OK);
        } catch (IOException ioe) {
            /* Errore nell'apertura del socket */
            System.out.println(ErrorList.SOCKET_OPENING_ERROR);
            ErrorList.exitError(ErrorList.SOCKET_OPENING_ERROR);
        }
        /* ===== */

        /* Ciclo infinito di attesa sulla porta: nel caso in cui il socket
         * non sia stato inizializzato (serverSocket = null), il ciclo non
         * comincia e il programma termina*/
        Socket client = null;
        while(true && serverSocket != null) {
            /* Creazione di un nuovo socket di comunicazione con il client */
            try {
                client = serverSocket.accept();
                /* Creazione di un thread separato per ogni client */
                new ConnectionThread(client);
            } catch (IOException ioe) {
                /* Errore nella connessione con il client */
                System.out.println(ErrorList.SOCKET_CLIENT_CONNECTION_ERROR);
                SocketUtil.closeServerSocket(serverSocket);
                ErrorList.exitError(ErrorList.SOCKET_CLIENT_CONNECTION_ERROR);
            }
        }
    }
}
