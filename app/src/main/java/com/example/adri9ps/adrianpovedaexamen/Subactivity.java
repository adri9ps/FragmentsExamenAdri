package com.example.adri9ps.adrianpovedaexamen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Subactivity extends AppCompatActivity {

    //Elementos vista
    TextView txtNombreApellidos, txtEdad, txtSexo, txtPuntuacion, txtLectura;
    Button btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity);

        //Conectamos Vista - Controlador
        txtNombreApellidos = (TextView) findViewById(R.id.txt_InfoNombre);
        txtEdad = (TextView) findViewById(R.id.txtInfoEdad);
        txtSexo = (TextView) findViewById(R.id.txtInfoSexo);
        txtPuntuacion = (TextView) findViewById(R.id.txtInfoPuntuacion);
        txtLectura = (TextView) findViewById(R.id.txtInfoLecturas);
        btnCerrar = (Button) findViewById(R.id.id_cerrar);

        //Recibimos parametros
        Intent intent = getIntent();
        txtNombreApellidos.setText("Nombre y apellidos: "+intent.getStringExtra("nombre"));
        txtEdad.setText("Edad: "+intent.getStringExtra("edad"));
        txtSexo.setText("Sexo: "+intent.getStringExtra("sexo"));
        txtPuntuacion.setText("Puntuación: "+intent.getStringExtra("puntuacion"));
        txtLectura.setText("Lecturas: "+intent.getStringExtra("lectura"));

        //Cerrar activity
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
