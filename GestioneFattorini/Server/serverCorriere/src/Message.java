/**
 * Created by inf.francoa3103 on 15/10/2018.
 */
public enum Message {

    DATABASE_CONNECTION_ESTABLISHED("La connessione al database è stata effettuata"),
    SOCKET_OPENING_OK("L'apertura del socket del server è stata effettuata");

    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "[MESSAGGE] : " + message;
    }
}
