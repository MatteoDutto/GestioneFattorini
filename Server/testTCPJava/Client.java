import java.io.*;
import java.net.Socket;

public class Client {
    public static final int defaultServerPort = 6666;

    public static void main(String[] args) throws  Exception{
        /* Creazione di un socket per la comunicazione verso il server */
        /* ATTENZIONE: nel primo campo di Socket() va inserito l'host del computer server o null nel caso di loopback address */
        Socket client = new Socket("192.168.1.114", defaultServerPort);

		Oggetto oggetto = new Oggetto("Pippo", 42);

		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
		oos.writeObject(oggetto);
		//oos.close();

        //String sentence;
        //String modifiedSentence;
        
		/* Lettura di una stringa da riga di comando */
		//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		//sentence = inFromUser.readLine();

		/* Acquiszione dello stream per comunicare con il server in uscita */
		//DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
		//outToServer.writeBytes(sentence + '\n');

		/* Acquisizione dello stream per comunicare con il server in entrata */
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String oggettoStringa = inFromServer.readLine();
		System.out.println("FROM SERVER:" + oggettoStringa);

		client.close();
    }
}
