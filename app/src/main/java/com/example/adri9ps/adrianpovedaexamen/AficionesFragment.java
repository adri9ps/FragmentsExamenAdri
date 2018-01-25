package com.example.adri9ps.adrianpovedaexamen;

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
import android.widget.RatingBar;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AficionesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AficionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AficionesFragment extends Fragment {

    //Variables
    private RadioButton rdBtnLibro, rdBtnRevistas, rdBtnRedes;
    private RatingBar starPuntuacion;
    private Button btnGuardar;
    private enviarAficiones c;      //Comunicador

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AficionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AficionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AficionesFragment newInstance(String param1, String param2) {
        AficionesFragment fragment = new AficionesFragment();
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
        View v = inflater.inflate(R.layout.fragment_aficiones, container, false);

        //Asignacion elementos vista
        rdBtnLibro = (RadioButton) v.findViewById(R.id.id_Libros);
        rdBtnRevistas = (RadioButton) v.findViewById(R.id.id_Revistas);
        rdBtnRedes = (RadioButton) v.findViewById(R.id.id_RedesSociales);
        starPuntuacion = (RatingBar) v.findViewById(R.id.ratingBar);
        btnGuardar = (Button) v.findViewById(R.id.id_guardar);

        //Eventos boton
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = (enviarAficiones) getActivity();        //Establecemos la actividad de la interfaz
                if (comprobarDatos()) {
                    //Creamos variables con sus valores
                    String lectura = lecturaCheck();
                    float estrellas = starPuntuacion.getRating();

                    c.recibirAficiones(lectura, estrellas);
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

    //Comprobar datos rellenados
    private boolean comprobarDatos(){
        if (starPuntuacion.getRating() > 0.0){
            return true;
        }

        return false;
    }

    //Comprobar que se ha seleccionado
    private String lecturaCheck(){
        if (rdBtnLibro.isChecked()){
            return "Libro";
        }
        if (rdBtnRevistas.isChecked()){
            return "Revistas";
        }
        if (rdBtnRedes.isChecked()){
            return "Redes Sociales";
        }

        //Si no hay ninguno seleccionado (Imposible!)
        return "Ninguno";
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
    }

    //Comunicador
    public interface enviarAficiones {
        void recibirAficiones(String lecture, float points);
    }
}
