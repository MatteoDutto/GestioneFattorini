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
     * @param dest : socket destinatario.
     * @param message : <i>Message</i> messaggio.
     **/
    public static void sendMessageTo(Socket dest, GestioneCorriere.Util.Message message) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(dest.getOutputStream());
        oos.writeObject(message);
    }

    /**
     * Metodo utilizzato per la ricezione di un messaggio da un socket mittente.
     * @return il messaggio ricevuto.
     */
    public static GestioneCorriere.Util.Message recvMessageFrom(Socket mitt) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(mitt.getInputStream());
        GestioneCorriere.Util.Message ret = (GestioneCorriere.Util.Message) ois.readObject();
        return ret;
    }
}
