import com.sun.java.util.jar.pack.Instruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 * Created by inf.francoa3103 on 15/10/2018.
 */
public class ConnectionThread extends Thread {

    /** Socket di comunicazione con il client */
    private Socket client = null;

    /** Costruttore che riceve il socket relativo al client connesso */
    public ConnectionThread(Socket client) {
        this.client = client;
        /* Avvio del metodo run() */
        this.start();
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String socketRequest = br.readLine();
            String socketRequestType = socketRequest.substring(0, socketRequest.indexOf(":") + 1);

            switch(socketRequestType) {
                case(DatabaseOperations.SELECT_PACCO_FROM_ID.getString()):

                    break;

            }

        } catch(IOException ioe) {
            System.out.println(Error.CLIENT_REQUEST_ERROR);
            SocketUtil.closeSocket(client);
            Error.exitError(Error.CLIENT_REQUEST_ERROR);
        }
    }
}
