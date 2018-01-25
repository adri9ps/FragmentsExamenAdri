package com.example.adri9ps.adrianpovedaexamen;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.app.FragmentTransaction;
import android.widget.Toast;


//Debemos implementar los fragments con el metodo OnFragmentInteractionListener para poder utilizarlos.
public class MainActivity extends AppCompatActivity implements Fragment_Botonera.OnFragmentInteractionListener, Fragment_Dinamico.OnFragmentInteractionListener, AficionesFragment.enviarAficiones,DatosPersonalesFragment.enviarDatosPersonales,AficionesFragment.OnFragmentInteractionListener{

    String nombreUsuario;
    String edadUsuario;
    String sexo;
    String lectura;
    String puntuacion;
    public static boolean mostrando;
    RadioButton hombreUsuario;
    RadioButton mujerUsuario;
    Button datosPersonales;
    Button aficiones;
    ImageButton info;
    private FragmentManager fm;
    private FragmentTransaction ft;
    Button guardarDatos;
    final int SUBACT_1 = 365;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBackPressed() {
        FragmentTransaction ft;
        android.app.FragmentManager fm;
        fm = getFragmentManager();
        ft = fm.beginTransaction();

        if (mostrando){
            ft.remove(getFragmentManager().findFragmentById(R.id.fragmentDinamico));
            ft.commit();

            Toast.makeText(getApplicationContext(),"Fragment eliminado",Toast.LENGTH_LONG).show();
            mostrando = false;
        } else {
            //Si no hay ningun fragment, sal de la aplicación
            super.onBackPressed();
        }
    }


    public void onFragmentInteraction(Uri uri) {}

    @Override
    public void recibirDatosPersonales(String nom, String edad, String sex){

        nombreUsuario = nom;
        edadUsuario = edad;
        sexo = sex;
    }

    @Override
    public void recibirAficiones(String lec, float puntos){

        lectura = lec;
        puntuacion = Float.toString(puntos);
    }

    @Override
    public void onResume(){

        super.onResume();
    }

}
