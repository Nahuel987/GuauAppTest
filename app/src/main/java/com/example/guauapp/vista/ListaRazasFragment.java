package com.example.guauapp.vista;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.guauapp.R;
import com.example.guauapp.adaptador.AdaptadorListaDeRazas;
import com.example.guauapp.api.ApiGuau;
import com.example.guauapp.api.RetrofitClient;
import com.example.guauapp.modelo.ListaDeRazasRespuesta;
import com.example.guauapp.modelo.ListaImagenesRespuesta;

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

    /* --------------DECLARACION DE VARIABLES GLOBALES CICLO DE VIDA CARGAR RECYCLER VIEW EN ADAPTADOR------------ */

    //PASO 4 HACER LA REFERENCIA A LA LISTA DE LA CLASE ADAPTADOR
    List<String> perriwis;

    //PASO 2  HACER LA REFERENCIA AL RECYCLER VIEW DE DONDE SE MOSTRARA LA LISTA EN ESTE CASO EL RECYCLER ESTA EN FRAGMENT LISTA RAZAS XML
    private RecyclerView recicla;

    //SE CERA UN STRING PERRO1 PERO NO SE EN QUE PASO VA  ??????
    private String perro1;

    //ImagenesFavoritasFragment fragFavorita;

    Button boton;



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
    }//instancia del fragmento

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }//on create

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        /*
        // TESTING DE LLENADO DEL RECYCLER VIEW
        for(int i=0;i<=50;i++){

            perriwis.add("DATOSS  " + i + " OK ");

        }

        //PASO 13 INSTANCIAR EL ADAPTADOR Y DARLE COMO PARAMETROS LA LISTA A MOSTRAR
        AdaptadorListaDeRazas adaptadorListaDeRazas = new AdaptadorListaDeRazas(perriwis);

        //PASO 14 DAR COMO PARAMETRO AL RECYCLER VIEW LA LISTA
        recicla.setAdapter(adaptadorListaDeRazas);

*/


        // -- INICIO DEL PROCESO DE CARGAR EL FRAGMENTO CON RECICYCLER VIEW --
        //PASO 01 CREAR UN VIEW VISTA CON EL INFLATER QUE VENIA POR DEFECTO//INFLATE SIGNIFICA CARGAR UN FRAGMENTO

        View vista=inflater.inflate(R.layout.fragment_lista_razas, container, false);

        //PASO 5 INSTANCIO LA LISTA PARA MOSTRARLA EN EL RECYCLER VIEW
        List <String> perriwis= new ArrayList<String>();

        //PASO 3 HACER LA REFERENCIA AL RECYCLER VIEW DE DONDE SE MOSTRARA LA LISTA CON EL OBJETO VISTA EN ESTE CASO EL RECYCLER ESTA EN FRAGMENT LISTA RAZAS XML
        recicla= (RecyclerView) vista.findViewById(R.id.RecyclerListaRazas);

        //PASO 6 SE INDICA EL TIPO DE LAYOUT QUE TENDRA EL RECYCLER recicla PARA ESTE CASO SERA CON VISTA VERTICAL Y NO SE LE PASA "THIS" COMO PARAMETRO
        //YA QUE ESO SE OCUPA EN EL MAIN ACTIVITY, SE LE DA COMO PARAMETRO getContex() AL ESTAR EN UN FRAGMENTO
        recicla.setLayoutManager(new LinearLayoutManager(getContext()));

        //PASO 7 SE CREA EL METODO LLENAR LISTA EL CUAL LLENA LA LISTA A MOSTRAR
        llenarLista();



        /*  BOTON CON EL QUE SE PRETENDE PASAR DE UN FRAGMENTO A OTRO*/

        boton = (Button) vista.findViewById(R.id.botonFavoritos);

        //METODO DEL BOTONNNN INTENTA PASAR DE UN FRAGMENTO A OTRO
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagenesFavoritasFragment fragFavorita =new ImagenesFavoritasFragment();
                fragFavorita.
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentoImagenesFavoritas,fragFavorita)
                        .addToBackStack(null)
                        .commit();


                //DE ESTA LINEA PARA ABAJO EMPIEZA EL PROBLEM!!!!!!!
                //SE EJECUTA EL ADMINISTRADOR DEL FRAGMENTO, SE INICIA SU TRANSACCION Y SE REMPLAZA CONTENEDOR FRAGMENTO (ID DE MAIN ACTIVITY XML POR OBJETO FRAG UNO DE FRAGMENTO LISTA RAZA PERROS)
               // getActivity(getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentoImagenesFavoritas,fragFavorita).commit();
               // getFragmentManager().beginTransaction().replace(R.id.fragmentoImagenesFavoritas,fragFavorita).commit();
/*
                ImagenesFavoritasFragment nuevoFragmento = new ImagenesFavoritasFragment();
                FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentoImagenesFavoritas, nuevoFragmento);
                transaction.addToBackStack(null);

                // Commit a la transacción
                transaction.commit();  */

            }
        });


        return vista;
    }//on create view



    private void llenarLista(){

        //METODO QUE SE CONECTA A LA API CON RETROFIT Y DEVUELVE LA LISTA CON LAS RAZAS DE PERRITOS

        //retrofit //PASO 1: SE CREA UNA VARIABLE SERVICE DE LA INTERFACE APIGUAU Y SE CREA RETROFIT
        ApiGuau api = RetrofitClient.getRetrofitInstance().create(ApiGuau.class);

        //PASO 2: LA VARIABLE SERVICE LLAMA AL METODO QUE SE CREO EJ:getListaRazas() RECIBIENDO LA RESPUESTA EN EL OBJETO callImages.
        Call<ListaDeRazasRespuesta> call = api.getListaRazas();

        Log.e("ERROR","ESTO PASA");

        //PASO 3: SE UTILIZA METODO ENQUEUE PARA MANEJAR LOS RESULTADOS EN SUS METODOS INTERNOS
        call.enqueue(new Callback<ListaDeRazasRespuesta>() {
            @Override// METODO QUE SE EJECUTA CUANDO LLEGA LA RESPUESTA A LA CONSULTA REALIZADA --> Call<ListaDeRazasRespuesta>
            public void onResponse(Call<ListaDeRazasRespuesta> call, Response<ListaDeRazasRespuesta> response) {

                //SI LA RESPUESTA FUE EXITOSA SE OBTIENE LA LISTA DE RAZAS EN EL OBJETO getListaRazas() USANDO EL METODO response.body()
                //AQUI ES DONDE SE LLENA LA LISTA
                //PAA VERIFICAR QUE TODE ESTE FUNCIONADO SE PUEDE RECORRER LA LISTA CON UN ITERADOR FOR
                List<String> perritos = response.body().getListaRazas();
                perro1 = perritos.get(0);//LLAMO AL PRIMER PERRITO DE LA LISTA

                //PASO 8 INSTANCIAR EL ADAPTADOR Y DARLE COMO PARAMETROS LA LISTA A MOSTRAR
                AdaptadorListaDeRazas adaptadorListaDeRazas = new AdaptadorListaDeRazas(perritos);

                //PASO 9 DAR COMO PARAMETRO AL RECYCLER VIEW LA LISTA //FIN DEL CICLO RECYCLER VIEW
                // -- FIN DEL PROCESO DE CARGAR EL FRAGMENTO CON RECICYCLER VIEW --
                recicla.setAdapter(adaptadorListaDeRazas);


                //LOG PARA VER QUE DEVUELVE EL NOMBRE DE LA RAZA DE LA LISTA (PERRITOS)
                Log.e("PERRITOS", String.valueOf(perritos));


                //IF QUE PREGUNTA SI EL PRIMER PERRO DE LA LISTA ES DISTINTO DE VACIO Y EJECUTA METODO whoLetTheDogsOut();
                if (!perro1.equals("")) {
                    whoLetTheDogsOut();
                }

            }

            @Override// METODO QUE SE EJECUTA CUANDO FALLA LA RESPUESTA A LA CONSULTA REALIZADA --> Call<ListaDeRazasRespuesta> EJ: NO HAY INTERNET
            public void onFailure(Call<ListaDeRazasRespuesta> call, Throwable t) {

                //SE CREA UN TOAST Y UN LOG PARA VER SI FALLO LA REPUESTA
                //Toast.makeText(MainActivity.this,"FALLO LA CONECXIONNN", Toast.LENGTH_SHORT).show();
                Log.e("NO HAY LIST DE GUAU", String.valueOf(t));

            }
        });






   }//llenarLista


    private void whoLetTheDogsOut() {

        //METODO QUE DEVUELVE LA LISTA DE IMAGENES DE PERRITOS

        //PASO 1: SE CREA UNA VARIABLE SERVICE DE LA INTERFACE Y SE CREA RETROFIT
        ApiGuau service = RetrofitClient.getRetrofitInstance().create(ApiGuau.class);

        //PASO 2: LA VARIABLE SERVICE LLAMA AL METODO QUE SE CREO EJ:getBeedImageList RECIBIENDO LA RESPUESTA EN EL OBJETO callImages.
        Call<ListaImagenesRespuesta> callImages = service.getListaImagenesURL(perro1);

        //PASO 3: SE UTILIZA METODO ENQUEUE PARA MANEJAR LOS RESULTADOS EN SUS METODOS INTERNOS
        callImages.enqueue(new Callback<ListaImagenesRespuesta>() {
            @Override// METODO QUE SE EJECUTA CUANDO LLEGA LA RESPUESTA A LA CONSULTA REALIZADA --> Call<BreedImageListResponse>
            public void onResponse(Call<ListaImagenesRespuesta> call, Response<ListaImagenesRespuesta> response) {

                //SI LA RESPUESTA FUE EXITOSA SE OBTIENE LA LISTA DE IMAGENES EN EL OBJETO images URL USANDO EL METODO response.body()
                //PAA VERIFICAR QUE TODE ESTE FUNCIONADO SE PUEDE RECORRER LA LISTA CON UN ITERADOR FOR
                List<String> imagesURL = response.body().getListaImagenesURL();

                //INSTANCIAR EL ADAPTADOR Y DARLE COMO PARAMETROS LA LISTA A MOSTRAR
                //AdaptadorListaDeRazas adaptadorListaDeRazas = new AdaptadorListaDeRazas(imagesURL);

                //PASO DAR COMO PARAMETRO AL RECYCLER VIEW LA LISTA
                //recicla.setAdapter(adaptadorListaDeRazas);

                Log.e("IMAGENES PERRITOS OK", String.valueOf(imagesURL));
            }

            @Override// METODO QUE SE EJECUTA CUANDO FALLA LA RESPUESTA A LA CONSULTA REALIZADA --> Call<BreedImageListResponse> EJ: NO HAY INTERNET
            public void onFailure(Call<ListaImagenesRespuesta> call, Throwable t) {
                //SE CREA UN TOAST Y UN LOG PARA VER SI FALLO LA REPUESTA
                //Toast.makeText(MainActivity.this,"FALLO LA CONECXIONNN DE IMAGENES", Toast.LENGTH_SHORT).show();
                Log.e("NO HAY IMG DE GUAU ", String.valueOf(t));

            }
        });

    }// whoLetTheDogsOut


}//class fragmento
