package co.nf.axesoft.consumindourljson.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import co.nf.axesoft.consumindourljson.R;

public class FichaPeople extends AppCompatActivity {
    private TextView idFicha,nomeFicha,pesoFicha,alturaFicha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_people);

        idFicha = findViewById(R.id.txtFichaIdPeople);
        nomeFicha = findViewById(R.id.txtNomeFicha);
        pesoFicha = findViewById(R.id.txtPesoFicha);
        alturaFicha = findViewById(R.id.txtAlturaFicha);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null) {

            idFicha.setText(extras.getString("ID"));
            nomeFicha.setText(extras.getString("NOME"));
            pesoFicha.setText(extras.getString("PESO"));
            alturaFicha.setText(extras.getString("ALTURA"));

        }


    }
}
