package GestioneCorriere.Util;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Creato da Andrea Delmastro <br><br>
 * Utilities per la comunicazione mediante l'utilizzo di <i>Message</i> messaggi.
 */

public class MessageUtil {

    /**
     * Metodo utilizzato per inviare un messaggio ad un socket destinatario.
     **/
    public static void sendMessageTo(Socket dest, Message message) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(dest.getOutputStream());
        oos.writeObject(message);
    }

    /**
     * Metodo utilizzato per la ricezione di un messaggio da un socket mittente.
     * @return il messaggio ricevuto.
     */
    public static Message recvMessageFrom(Socket mitt) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(mitt.getInputStream());
        Message ret = (Message) ois.readObject();
        return ret;
    }
}
