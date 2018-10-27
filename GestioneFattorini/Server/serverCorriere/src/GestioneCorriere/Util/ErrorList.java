package GestioneCorriere.Util;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by inf.francoa3103 on 15/10/2018.
 */
public enum ErrorList {

    SOCKET_OPENING_ERROR("Errore nell'apertura del socket", -1),
    SOCKET_CLIENT_CONNECTION_ERROR("Errore nell'attesa di una comunicazione con un client", -2),
    DATABASE_CONNECTION_ERROR("Errore nella connessione al database", -3),
    CLIENT_REQUEST_ERROR("Errore nell'elaborazione della richiesta del client", -4),
    SQL_REQUEST_ERROR("Errore nell'elaborazione della richiesta SQL", -5);

    private String errorString;
    private int errorVal;

    ErrorList(String errorString, int errorVal) {
        this.errorString = errorString;
        this.errorVal = errorVal;
    }

    public String getErrorString() {
        return errorString;
    }

    public int getErrorVal() {
        return errorVal;
    }

    public static void exitError(ErrorList error) {
        System.exit(error.getErrorVal());
    }

    public static void defaultServerClosingOperation(Socket socket, ErrorList error) {
        System.out.println(ErrorList.CLIENT_REQUEST_ERROR);
        SocketUtil.closeSocket(socket);
    }

    @Override
    public String toString() {
        return "[ERROR] : " + errorString + " (" + errorVal + ")";
     }
}