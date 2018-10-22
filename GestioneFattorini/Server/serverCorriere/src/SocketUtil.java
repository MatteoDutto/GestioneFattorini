import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by inf.francoa3103 on 15/10/2018.
 */
public class SocketUtil {

    public static ServerSocket openServerSocket(int port) throws IOException {
        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(port);
        return  serverSocket;
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
