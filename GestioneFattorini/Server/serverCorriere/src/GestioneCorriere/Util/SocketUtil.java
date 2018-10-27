package GestioneCorriere.Util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by inf.francoa3103 on 15/10/2018.
 */
public class SocketUtil {

    public static ServerSocket openServerSocket(int port) throws IOException {
        return  new ServerSocket(port);
    }

    public static Socket openSocket(String IP, int port) throws IOException {
        return new Socket(IP, port);
    }

    public static void closeServerSocket(ServerSocket serverSocket) {
        try {
            serverSocket.close();
        } catch (IOException ioe) {}
    }

    public static void closeSocket(Socket socket) {
        try {
            socket.close();
        } catch (IOException ioe) {}
    }
}
