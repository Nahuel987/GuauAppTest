package com.example.guauapp.vista;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.guauapp.R;
import com.example.guauapp.adaptador.AdaptadorListaDeRazas;
import com.example.guauapp.adaptador.AdaptadorListaImagenes;
import com.example.guauapp.api.ApiGuau;
import com.example.guauapp.api.RetrofitClient;
import com.example.guauapp.modelo.ListaDeRazasRespuesta;
import com.example.guauapp.modelo.ListaImagenesRespuesta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaRazasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaRazasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    List<String> perriwis;

    private RecyclerView recicla;

    private String perro1;

    FloatingActionButton boton;

    List<String> imagenesURL=new ArrayList<>();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaRazasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaRazasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaRazasFragment newInstance(String param1, String param2) {
        ListaRazasFragment fragment = new ListaRazasFragment();
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

        View vista=inflater.inflate(R.layout.fragment_lista_razas, container, false);

        List <String> perriwis= new ArrayList<String>();

        recicla= (RecyclerView) vista.findViewById(R.id.RecyclerListaRazas);

        recicla.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarListaRazas();

        boton =  vista.findViewById(R.id.floatingActionButton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "IMAGENES FAVORITAS", Toast.LENGTH_SHORT).show();

                pasarAfragmentoImagenesFavoritas();
            }
        });
        return vista;
    }

    private void llenarListaRazas(){

        ApiGuau api = RetrofitClient.getRetrofitInstance().create(ApiGuau.class);

        Call<ListaDeRazasRespuesta> call = api.getListaRazas();

        Log.e("ERROR","ESTO PASA");

        call.enqueue(new Callback<ListaDeRazasRespuesta>() {
            @Override
            public void onResponse(Call<ListaDeRazasRespuesta> call, Response<ListaDeRazasRespuesta> response) {

                final List<String> perritos = response.body().getListaRazas();
                perro1 = perritos.get(0);

                AdaptadorListaDeRazas adaptadorListaDeRazas = new AdaptadorListaDeRazas(perritos);

                recicla.setAdapter(adaptadorListaDeRazas);

                adaptadorListaDeRazas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(getContext(),"RAZA SELECCIONADA "+perritos.get(recicla.getChildAdapterPosition(view)),Toast.LENGTH_SHORT).show();

                        perro1=perritos.get(recicla.getChildAdapterPosition(view));

                        if (!perro1.equals("")) {
                            listaImagenesPerritos();
                        }

                    }
                });

                Log.e("PERRITOS", String.valueOf(perritos));
             }

            @Override
            public void onFailure(Call<ListaDeRazasRespuesta> call, Throwable t) {

                Log.e("NO HAY LIST DE GUAU", String.valueOf(t));
            }
        });


    }

    private void listaImagenesPerritos() {

        ApiGuau service = RetrofitClient.getRetrofitInstance().create(ApiGuau.class);

        Call<ListaImagenesRespuesta> callImages = service.getListaImagenesURL(perro1);

        callImages.enqueue(new Callback<ListaImagenesRespuesta>() {
            @Override
            public void onResponse(Call<ListaImagenesRespuesta> call, Response<ListaImagenesRespuesta> response) {

                List<String> imagenesURL = response.body().getListaImagenesURL();

                pasarAfragmentoImagenesPerritos(imagenesURL);

                Log.e("IMAGENES PERRITOS OK", String.valueOf(imagenesURL));
            }

            @Override
            public void onFailure(Call<ListaImagenesRespuesta> call, Throwable t) {
                Log.e("NO HAY IMG DE GUAU ", String.valueOf(t));

            }
        });

    }

    public void pasarAfragmentoImagenesFavoritas(){
      ImagenesFavoritasFragment fragImgFavoritas = new ImagenesFavoritasFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentoUno,fragImgFavoritas,"PASANDO A FRAG IMG FAVORITAS").addToBackStack("PASANDO A BACK").commit();
    }

    public void pasarAfragmentoImagenesPerritos(List <String> listaPerritosXrazas){

        ImagenesPerritosFragment fragImgPerritos=ImagenesPerritosFragment.newInstance(listaPerritosXrazas);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentoUno,fragImgPerritos,"PASANDO A FRAG IMG PERRITOS").addToBackStack("PASANDO A BACK").commit();
    }
}
