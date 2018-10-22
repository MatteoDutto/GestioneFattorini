import com.sun.xml.internal.ws.server.ServerSchemaValidationTube;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Pipe;
import java.sql.SQLException;

/**
 * Created by inf.francoa3103 on 15/10/2018.
 */
public class Server {
    /* Porta sulla quale risponde il server */
    private static ServerSocket serverSocket = null;
    private static final int serverPort = 3103;
    private static final String dbURL = "C:/Users/inf.francoa3103/Desktop/dbProva.db";

    public static void main(String[] args) {

        /* Collegamento al database */
        try {
            DatabaseConnection.connect(dbURL);
            System.out.println(Message.DATABASE_CONNECTION_ESTABLISHED);
        } catch(SQLException sqle) {
            System.out.println(Error.DATABASE_CONNECTION_ERROR);
            Error.exitError(Error.DATABASE_CONNECTION_ERROR);
        }
        /* ===== */

        try {
            serverSocket = SocketUtil.openServerSocket(serverPort);
            System.out.println(Message.SOCKET_OPENING_OK);
        } catch (IOException ioe) {
            System.out.println(Error.SOCKET_OPENING_ERROR);
            Error.exitError(Error.SOCKET_OPENING_ERROR);
        }

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
                System.out.println(Error.SOCKET_CLIENT_CONNECTION_ERROR);
                SocketUtil.closeServerSocket(serverSocket);
                Error.exitError(Error.SOCKET_CLIENT_CONNECTION_ERROR);
            }

            /* Ciascuna richiesta viene gestita da un thread separato */

        }
    }
}
