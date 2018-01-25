package com.example.adri9ps.adrianpovedaexamen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DatosPersonalesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DatosPersonalesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatosPersonalesFragment extends Fragment {

    //Variables vista
    EditText txtNombreApellidos;
    EditText txtEdad;
    RadioButton rdBtnMasculino, rdBtnFemenino;
    Button btnGuardar;

    private enviarDatosPersonales c;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DatosPersonalesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatosPersonalesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatosPersonalesFragment newInstance(String param1, String param2) {
        DatosPersonalesFragment fragment = new DatosPersonalesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_datos_personales, container, false);

        //Variables vista -- controlador
        txtNombreApellidos = (EditText) v.findViewById(R.id.txtNombreApellidos);
        txtEdad = (EditText) v.findViewById(R.id.txtEdad);
        rdBtnMasculino = (RadioButton) v.findViewById(R.id.id_hombre);
        rdBtnFemenino = (RadioButton) v.findViewById(R.id.id_mujer);
        btnGuardar = (Button) v.findViewById(R.id.btn_Guardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = (enviarDatosPersonales) getActivity();        //Establecemos la actividad de la interfaz
                if (comprobarDatos()) {
                    //Creamos variables con sus valores
                    String nameApellidos = txtNombreApellidos.getText().toString();
                    String edad = txtEdad.getText().toString();
                    String sexo = sexoCheck();

                    c.recibirDatosPersonales(nameApellidos, edad, sexo);
                    Toast.makeText(getContext(), "Datos guardados correctamente", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(),"Tienes que rellenar todos los campos",Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        boolean algunFragment();
    }

    //Comprobar datos rellenados
    private boolean comprobarDatos(){
        if (txtNombreApellidos.getTextSize() < 0){
            return false;
        }

        if (txtEdad.getTextSize() < 0){
            return false;
        }

        return true;
    }

    //Checkear boton seleccionado (RadioButton)
    private String sexoCheck(){
        if (rdBtnMasculino.isChecked()){
            return "Masculino";
        } else if (rdBtnFemenino.isChecked()){
            return "Femenino";
        }

        //Si no se ha seleccionado ninguno (Imposible!)
        return "Ninguno";
    }

    //Enviar datos al MainActivity
    public interface enviarDatosPersonales {
        void recibirDatosPersonales(String nameApedillos, String age, String sex);
    }
}