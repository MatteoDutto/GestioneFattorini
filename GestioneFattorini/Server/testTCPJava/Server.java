import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int defaultServerPort = 6666;

    public static void main(String[] args) throws IOException {
        /* Creazione di un ServerSocket: il ServerSocket attende le richieste che vengono inoltrate nella rete
         * alla porta definita dal costruttore sul computer dove il server è in esecuzione */
        ServerSocket server = new ServerSocket(defaultServerPort);

        /* Ciclo infinito di attesa ciclica sulla porta */
        while(true) {
            /* Creazione di un nuovo socket di comunicazione con il client:
             * Il metodo accept attende una comunicazione sul socket e la accetta */
            Socket client = server.accept();

            /* Ciascuna richiesta viene gestita da un thread separato */
            Connection connection = new Connection(client);
        }
    }
}
