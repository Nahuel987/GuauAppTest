package com.example.guauapp.vista;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.guauapp.R;
import com.example.guauapp.adaptador.AdaptadorListaImagenes;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImagenesPerritosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImagenesPerritosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";  //ESTE PARAM2 DEBE SER ELIMINADO Y TODA REFERENCIA A EL, PUESTO QUE YA NO SE UTILIZARA PARA EL SOFTWARE


    //SE CREA UNA LISTA PARA QUE RECIBA UNA LISTA CON LAS URL DE LOS PRERRITOS SELECCIONADO POR RAZA
    List <String> recibeURLS =new ArrayList<>();

    // TODO: Rename and change types of parameters
    private ArrayList<String> mParam1;
    //private String mParam2;

    private RecyclerView reciclaImagenesPerritos;

    private AdaptadorListaImagenes adaptadorListaImagenes;




    public ImagenesPerritosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImagenesPerritosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImagenesPerritosFragment newInstance(String param1, String param2) {
        ImagenesPerritosFragment fragment = new ImagenesPerritosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    //METODO QUE INSTANCIA UN NUEVO FRAGMENTO RECIBIENDO UN LIST DE STRING, SE USARA PARA RECIBIR LA LISTA DE PERRITOS SELECCIONADA
    public static ImagenesPerritosFragment newInstance(List<String> param1){

        ImagenesPerritosFragment fragment=new ImagenesPerritosFragment();
        Bundle args=new Bundle();
        args.putStringArrayList(ARG_PARAM1, (ArrayList<String>) param1);
        Log.e("RECIBO LISTA", String.valueOf(param1));
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ACA SE RECIBEN LOS ARGUMENTOS ENVIADOS EN fragment.setArguments(args) DEL METODO   ImagenesPerritosFragment newInstance(List<String> param1)
        //SE PREGUNTA SI SON DISTINTOS DE VACIO, SE LE ASIGNAN A mPARAM1 Y DESPUES SE LE ENTREGAN A recibeURLS
        if (getArguments() != null) {
            mParam1 = getArguments().getStringArrayList(ARG_PARAM1);

            //ESTE ES EL ARRAY QUE SE LE ASIGNARA AL ADAPTADOR, PARA QUE ESTE (ADAPTADOR) SEA LEIDO POR EL RECYCLER VIEW
            recibeURLS = mParam1;
            Log.e("RECIBO URLSSS", String.valueOf(recibeURLS));

        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista = inflater.inflate(R.layout.fragment_imagenes_perritos, container, false);

        reciclaImagenesPerritos= (RecyclerView) vista.findViewById(R.id.RecyclerListaImagenes);

        reciclaImagenesPerritos.setLayoutManager(new LinearLayoutManager(getContext()));

        adaptadorListaImagenes=new AdaptadorListaImagenes(recibeURLS,getContext());

        reciclaImagenesPerritos.setAdapter(adaptadorListaImagenes);


        //SEBA
        //IMPLEMENTO METODO ON LONG LISTENER CREADO EN EL ADAPTADOR DE LISTA IMAGENES
//        adaptadorListaImagenes.setOnLongClickListener(new View.OnLongClickListener() {
//           @Override
//            public boolean onLongClick(View v) {
//
//                String test;
//                Toast.makeText(getContext(), "IMAGENES DE PERRIWI", Toast.LENGTH_SHORT).show();
//
//                //test=recibeURLS.get();
//                test=recibeURLS.get(reciclaImagenesPerritos.getChildAdapterPosition(v));
//                Log.e("RECIBO CAFEST", String.valueOf(recibeURLS));
//
//                /* RESULTADO DEL CAFEST = VARIABLE STRING test RECIBE TODA LA LISTA DE recibeURLS, NO SOLO UN ELEMENTO*/
//
//               //IMPLEMENTAR LA SUBIDA DEL ARCHIVO DESDE LA CLASE ADPADTOR, YA QUE SE OBTIENE POR CADA ITERACION UNA URL
//                //IMPLEMENTAR ON CLICK DESDE EL ADAPTADOR
//
//
//                return false;
//            }
//        });

        return vista;
    }
}
