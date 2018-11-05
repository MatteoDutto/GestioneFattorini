package com.example.appCorriere.GestioneCorriere.Util;

/**
 * Created by inf.francoa3103 on 15/10/2018.
 */
public enum MessagesList {

    DATABASE_CONNECTION_ESTABLISHED("La connessione al database è stata effettuata"),
    SOCKET_OPENING_OK("L'apertura del socket del server è stata effettuata"),
    CONNECTION_CLIENT_ESTABLISHED("E' stata stabilita una connessione con un client: ");

    private String message;

    MessagesList(String message) {
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
