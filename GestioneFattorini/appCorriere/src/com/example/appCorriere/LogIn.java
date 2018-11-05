package com.example.appCorriere;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by inf.delmastroa0612 on 05/11/2018.
 */
public class LogIn extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.login);

        /* Acquisizione degli elementi utili del layout */
        EditText edtLogIn = (EditText) findViewById(R.id.edt_login);
        Button btnLogIn = (Button) findViewById(R.id.btn_login);

        /* Aggiunta di un listener al pulsante */
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Quando il pulsante viene cliccato viene innanzitutto controllato
                 * che il codice del fattorino sia stato inserito */
                if(edtLogIn.getText() == null) {
                    /* L'errore viene segnalato all'utente */
                    Toast.makeText(v.getContext(), getString(R.string.error_insert_delman_code_error), Toast.LENGTH_LONG).show();
                } else {
                    /* Il codice fattorino viene inviato all'activity chiamante */
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("codFatt", edtLogIn.getText().toString());
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

    }
}