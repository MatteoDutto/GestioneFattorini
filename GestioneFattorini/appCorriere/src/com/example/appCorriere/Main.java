package com.example.appCorriere;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
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
    private ArrayList<Pack> packsToBeDelivered = null;
    private int packsIndex = 0;

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

                /* == Viene controllato che il fattorino esista == */
                /* Creazione di un messaggio da inviare al server */
                Message messageToServer = new Message();
                /* Impostazione dell'operazione richiesta al server */
                messageToServer.setOperation(DatabaseOperations.SELECT_DELIVERY_MAN_FROM_ID);
                /* Impostazione dei dati del fattorino */
                DeliveryMan deliveryManOpt = new DeliveryMan();
                deliveryManOpt.setCodiceFattorino(codFatt);

                try {
                    /* Invio del messaggio al server */
                    MessageUtil.sendMessageTo(socket, messageToServer);
                    /* Ricezione di un messaggio di risposta dal server: dalla sezione risultato del messaggio vengono
                     * acquisite le informazioni relative al fattorino richiesto, che sarà anche l'unico nell'ArrayList
                     * di risultato */
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

                /* == Vengono visualizzate le informazioni relative al fattorino == */
                TextView txtDelMan = (TextView) findViewById(R.id.txt_del_man);
                txtDelMan.setText(delMan.toString());

                /* == Venogno caricati i pacchi relativi al fattorino == */
                /* Vengono richiesti al server tutti i pacchi assegnati ad un determinato fattorino */
                messageToServer.setOperation(DatabaseOperations.SELECT_ALL_PACKS_FROM_DELIVERY_MAN_ID);
                messageToServer.setDelManOptions(delMan);
                try {
                    /* Invio del messaggio al server */
                    MessageUtil.sendMessageTo(socket, messageToServer);
                    packsToBeDelivered = MessageUtil.recvMessageFrom(socket).getResPacks();
                } catch(IOException ioe) {
                    /* In caso di errore questo viene segnalato all'utente e l'applicazione viene chiusa */
                    Toast.makeText(this, ErrorList.SQL_REQUEST_ERROR.toString(), Toast.LENGTH_LONG);
                    finish();
                } catch (ClassNotFoundException cnfe) {
                    /* In caso di errore questo viene segnalato all'utente e l'applicazione viene chiusa */
                    Toast.makeText(this, ErrorList.SQL_REQUEST_ERROR.toString(), Toast.LENGTH_LONG);
                    finish();
                }

                /* Viene visualizzato il prossimo pacco */
                this.showNextPack();

            } else {
                /* Viene segnalato l'errore e chiusa l'applicazione */
                Toast.makeText(this, getString(R.string.error_log_in), Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    public void showNextPack() {
        TextView txtPack = (TextView) findViewById(R.id.txt_pack);
        txtPack.setText(packsToBeDelivered.get(packsIndex++).toString());
    }
}
