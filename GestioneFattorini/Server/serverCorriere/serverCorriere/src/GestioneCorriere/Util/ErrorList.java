package GestioneCorriere.Util;

import java.net.Socket;

/**
 * Creato da Andrea Delmastro, Alessio Franco. <br><br>
 * Classe enumerazione che rappresenta l'insieme di errori generati da un programma
 * che sfrutti socket/database.
 * La classe può essere utilizzata sia per la segnalazione degli errori da parte del server,
 * sia per la segnalazione degli errori da parte del client.
 */
public enum ErrorList {

    SOCKET_OPENING_ERROR("Errore nell'apertura del socket", -1),
    SOCKET_CLIENT_CONNECTION_ERROR("Errore nell'attesa di una comunicazione con un client", -2),
    DATABASE_CONNECTION_ERROR("Errore nella connessione al database", -3),
    CLIENT_REQUEST_ERROR("Errore nell'elaborazione della richiesta del client", -4),
    SQL_REQUEST_ERROR("Errore nell'elaborazione della richiesta SQL", -5);

    private String errorString;
    private int errorVal;

    /** Metodo costruttore di un nuovo errore.
     * Da utilizzare per la costruzione di errori generati runtime.
     * @param errorString : stringa rappresentante l'errore
     * @param errorVal :  codice di errore */
    ErrorList(String errorString, int errorVal) {
        this.errorString = errorString;
        this.errorVal = errorVal;
    }

    /** @return stringa rappresentate l'errore */
    public String getErrorString() {
        return errorString;
    }

    /** @return codice di errore */
    public int getErrorVal() {
        return errorVal;
    }

    /**
     * Metodo che termina l'esecuzione di un programma.
     * Il programma viene terminato con un determinato codice di errore sulla
     * base dell'errore passato al metodo.
     * @param error = errore che genera l'uscita dal programma
     * */
    public static void exitError(ErrorList error) {
        System.exit(error.getErrorVal());
    }

    /**
     * Metodo che strandard per terminare l'esecuzione di un thread sul server.
     * Il programma viene terminato con un determinato codice di errore sulla
     * base dell'errore passato al metodo. Il messaggio di errore viene stampato a video.
     * Il socket di comunicazione viene chiuso.
     * @param error = errore che genera l'uscita dal programma
     * */
    public static void defaultServerClosingOperation(Socket socket, ErrorList error, Thread thread) {
        System.out.println(ErrorList.CLIENT_REQUEST_ERROR);
        SocketUtil.closeSocket(socket);
        thread.interrupt();
    }

    @Override
    public String toString() {
        return "[ERROR] : " + errorString + " (" + errorVal + ")";
     }
}
