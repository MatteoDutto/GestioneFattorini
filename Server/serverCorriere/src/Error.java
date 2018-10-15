/**
 * Created by inf.francoa3103 on 15/10/2018.
 */
public enum Error {

    SOCKET_OPENING_ERROR("Errore nell'apertura del socket", -1),
    SOCKET_CLIENT_CONNECTION_ERROR("Errore nell'attesa di una comunicazione con un client", -2),
    DATABASE_CONNECTION_ERROR("Errore nella connessione al database", -3),
    CLIENT_REQUEST_ERROR("Errore nell'elaborazione della richiesta del client", -4);

    private String errorString;
    private int errorVal;

    Error(String errorString, int errorVal) {
        this.errorString = errorString;
        this.errorVal = errorVal;
    }

    public String getErrorString() {
        return errorString;
    }

    public int getErrorVal() {
        return errorVal;
    }

    public static void exitError(Error error) {
        System.exit(error.getErrorVal());
    }

    @Override
    public String toString() {
        return "[ERROR] : " + errorString + " (" + errorVal + ")";
     }
}
