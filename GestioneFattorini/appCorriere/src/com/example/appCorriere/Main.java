package com.example.appCorriere;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;
import com.example.appCorriere.GestioneCorriere.Util.*;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Main extends Activity {
    /**
     * Called when the activity is first created.
     */

    private String codFatt = null;
    private Socket socket = null;
    private DeliveryMan delMan = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            /* Alla creazione viene aperto un socket di comunicazione con il server */
            socket = SocketUtil.openSocket("127.0.0.1", 3103);
        } catch (IOException ioe) {
            /* In caso di errore questo viene segnalato all'utente e l'applicazione viene chiusa */
            Toast.makeText(this, ErrorList.SOCKET_OPENING_ERROR.toString(), Toast.LENGTH_LONG).show();
            finish();
        }

        /* Alla creazione viene lanciata l'activity che si occupa della gestione del login */
        Intent logInIntent = new Intent(this, LogIn.class);
        startActivityForResult(logInIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(resultCode == Activity.RESULT_OK){
                /* Viene acquisito il codice ritornato dall'activity di login */
                codFatt = data.getStringExtra("codFatt").toString();
                /* Viene controllato che il fattorino esista */
                Message messageToServer = new Message();
                messageToServer.setOperation(DatabaseOperations.SELECT_DELIVERY_MAN_FROM_ID);
                DeliveryMan deliveryManOpt = new DeliveryMan();
                deliveryManOpt.setCodiceFattorino(codFatt);

                try {
                    MessageUtil.sendMessageTo(socket, messageToServer);
                    delMan = MessageUtil.recvMessageFrom(socket).getResDelMan().get(0);
                } catch(IOException ioe) {
                     /* In caso di errore questo viene segnalato all'utente e l'applicazione viene chiusa */
                    Toast.makeText(this, ErrorList.SQL_REQUEST_ERROR.toString(), Toast.LENGTH_LONG).show();
                    finish();
                } catch (ClassNotFoundException cnfe) {
                    /* In caso di errore questo viene segnalato all'utente e l'applicazione viene chiusa */
                    Toast.makeText(this, ErrorList.SQL_REQUEST_ERROR.toString(), Toast.LENGTH_LONG).show();
                    finish();
                }
            } else {
                /* Viene segnalato l'errore e chiusa l'applicazione */
                Toast.makeText(this, getString(R.string.error_log_in), Toast.LENGTH_LONG).show();
                finish();
            }
        }

    }
}
