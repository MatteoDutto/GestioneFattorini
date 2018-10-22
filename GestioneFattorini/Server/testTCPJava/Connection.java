import java.io.*;
import java.net.Socket;

public class Connection extends Thread {
    private Socket client;

    public Connection(Socket client) {
        this.client = client;
        /* Avvio del thread: viene lanciato il metodo run del thread stesso */
        this.start();
    }

    @Override
    public void run() {
        try {
            /* Acquisizione di una stringa in ingresso */
            //BufferedReader readerFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //String stringFromClient = readerFromClient.readLine();
            /* Invio di una stringa convertita al client */
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());

            Oggetto oggetto = (Oggetto) ois.readObject();

            System.out.println(oggetto.getNomeOggetto() + oggetto.getNumeroOggetto());

            DataOutputStream stringToClient = new DataOutputStream(client.getOutputStream());
            stringToClient.writeBytes(oggetto.getNomeOggetto() + oggetto.getNumeroOggetto() + '\n');
            /* Chiusura del thread */
            this.stop();
        }catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}
