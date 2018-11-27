package GestioneCorriere.Util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Creato da Andrea Delmastro, Alessio Franco <br><br>
 * Utilities per la gestione dei socket.
 */
public class SocketUtil {

    /**
     * Metodo che apre un socket server su una data porta.
     * @param port : porta di comunicazione.
     */
    public static ServerSocket openServerSocket(int port) throws IOException {
        return  new ServerSocket(port);
    }
    /**
     * Metodo che apre un socket client su una data porta e verso un determinato IP.
     * @param IP : indirizzo IP del server.
     * @param port : porta di comunicazione.
     */
    public static Socket openSocket(String IP, int port) throws IOException {
        return new Socket(IP, port);
    }

    /**
     * Metodo che chiude su socket server.
     * @param serverSocket : socket server da chiudere.
     */
    public static void closeServerSocket(ServerSocket serverSocket) {
        try {
            serverSocket.close();
        } catch (IOException ioe) {}
    }

    /**
     * Metodo che chiude un socket clinet.
     * @param socket : socket client da chiudere.
     */
    public static void closeSocket(Socket socket) {
        try {
            socket.close();
        } catch (IOException ioe) {}
    }
}
