import GestioneCorriere.Util.ErrorList;
import GestioneCorriere.Util.MessagesList;
import GestioneCorriere.Util.SocketUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Created by inf.francoa3103 on 15/10/2018.
 */
public class Server {
    /* Porta sulla quale risponde il server */
    private static ServerSocket serverSocket = null;
    private static final int serverPort = 3103;
    private static final String dbURL = "/home/delmastro/Pacchi.db";

    public static void main(String[] args) {

        /* Collegamento al database */
        try {
            DatabaseConnection.connect(dbURL);
            System.out.println(MessagesList.DATABASE_CONNECTION_ESTABLISHED);
        } catch(SQLException sqle) {
            System.out.println(ErrorList.DATABASE_CONNECTION_ERROR + ":" + sqle.toString());
            ErrorList.exitError(ErrorList.DATABASE_CONNECTION_ERROR);
        }
        /* ===== */

        try {
            serverSocket = SocketUtil.openServerSocket(serverPort);
            System.out.println(MessagesList.SOCKET_OPENING_OK);
        } catch (IOException ioe) {
            System.out.println(ErrorList.SOCKET_OPENING_ERROR);
            ErrorList.exitError(ErrorList.SOCKET_OPENING_ERROR);
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
                System.out.println(ErrorList.SOCKET_CLIENT_CONNECTION_ERROR);
                SocketUtil.closeServerSocket(serverSocket);
                ErrorList.exitError(ErrorList.SOCKET_CLIENT_CONNECTION_ERROR);
            }

            /* Ciascuna richiesta viene gestita da un thread separato */

        }
    }
}
