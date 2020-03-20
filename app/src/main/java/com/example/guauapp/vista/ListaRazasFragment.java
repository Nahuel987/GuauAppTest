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

    /* --------------DECLARACION DE VARIABLES GLOBALES CICLO DE VIDA CARGAR RECYCLER VIEW EN ADAPTADOR------------ */

    //PASO 4 HACER LA REFERENCIA A LA LISTA DE LA CLASE ADAPTADOR
    List<String> perriwis;

    //PASO 2  HACER LA REFERENCIA AL RECYCLER VIEW DE DONDE SE MOSTRARA LA LISTA EN ESTE CASO EL RECYCLER ESTA EN FRAGMENT LISTA RAZAS XML
    private RecyclerView recicla;

    //SE CERA UN STRING PERRO1 PARA QUE RECIBA UN PERRO DE ACUERDO A LA POSICION DE =>   perro1=perritos.get(recicla.getChildAdapterPosition(view));
    private String perro1;

    //SE DECLARA UN BOTON PARA PASAR AL FRAGMENTO DE IMAGENES FAVORITAS
   // private Button boton;

    //SE REEMPLAZA EL BOTON ANTERIROR POR UN FLOATING ACCTION BUTTON
    FloatingActionButton boton;



    //SE INSTANCIA LA LISTA IMAGENES URL PARA PODER USARLA EN EL METODO LLENAR LISTA IMAGENES Y DARSELA COMO PARAMETRO AL CONTRUCCTOR DE IMAGENES PRERRITOS FRAGMENT
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
        llenarListaRazas();


        /*  BOTON CON EL QUE SE PRETENDE PASAR DE UN FRAGMENTO A OTRO*/
        //HACER LA REFERENCIA AL BOTON CON EL OBJETO BOTON DEL XML
        //boton = (Button) vista.findViewById(R.id.botonFavoritos);

        boton =  vista.findViewById(R.id.floatingActionButton);

        //METODO DEL BOTONNNN INTENTA PASAR DE UN FRAGMENTO A OTRO
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "IMAGENES FAVORITAS", Toast.LENGTH_SHORT).show();

                //SI SE ACCIONA EVENTO CLICK DEL BOTON , SE EJECUTA EL METODO DE ABAJO
                pasarAfragmentoImagenesFavoritas();
            }
        });


        return vista;
    }//on create view














    private void llenarListaRazas(){

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
                final List<String> perritos = response.body().getListaRazas();
                perro1 = perritos.get(0);//LLAMO AL PRIMER PERRITO DE LA LISTA

                //PASO 8 INSTANCIAR EL ADAPTADOR Y DARLE COMO PARAMETROS LA LISTA A MOSTRAR
                AdaptadorListaDeRazas adaptadorListaDeRazas = new AdaptadorListaDeRazas(perritos);

                //PASO 9 DAR COMO PARAMETRO AL RECYCLER VIEW LA LISTA //FIN DEL CICLO RECYCLER VIEW
                // -- FIN DEL PROCESO DE CARGAR EL FRAGMENTO CON RECICYCLER VIEW --
                recicla.setAdapter(adaptadorListaDeRazas);

                //EVENTO ON CLICK LISTENER PASO 6 (LLEGANDO DE PASO 5 DE ADAPTADOR RAZAS DE PERRITOS)
                //AQUI SE DEBE ASIGNAR AL ADAPTADOR EL EVENTO SET ON CLICK LISTENER
                adaptadorListaDeRazas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //DE MOMENTO SE MUESTRA UN TOAST PARA MOSTRAR LA RAZA SELECCIONADA
                        Toast.makeText(getContext(),"RAZA SELECCIONADA "+perritos.get(recicla.getChildAdapterPosition(view)),Toast.LENGTH_SHORT).show();


                        //LINEA QUE HACE LA VERDADERA MAGIA!!!!!!
                        //LE ASIGNA A PERRO1 UN ARRAY CON LA LISTA DE LA RAZA DE PERRITO SELECCIONADA
                        perro1=perritos.get(recicla.getChildAdapterPosition(view));


                        //IF QUE PREGUNTA SI EL PRIMER PERRO DE LA LISTA ES DISTINTO DE VACIO Y EJECUTA METODO listaImagenesPerritos();
                        if (!perro1.equals("")) {
                            listaImagenesPerritos();
                            //pasarAfragmentoImagenesPerritos();//DUDO QUE VALLA AKISSSS
                        }



                    }
                });//on click listener adadptador



                //LOG PARA VER QUE DEVUELVE EL NOMBRE DE LA RAZA DE LA LISTA (PERRITOS)
                Log.e("PERRITOS", String.valueOf(perritos));


            }





            @Override// METODO QUE SE EJECUTA CUANDO FALLA LA RESPUESTA A LA CONSULTA REALIZADA --> Call<ListaDeRazasRespuesta> EJ: NO HAY INTERNET
            public void onFailure(Call<ListaDeRazasRespuesta> call, Throwable t) {

                //SE CREA UN TOAST Y UN LOG PARA VER SI FALLO LA REPUESTA
                //Toast.makeText(MainActivity.this,"FALLO LA CONECXIONNN", Toast.LENGTH_SHORT).show();
                Log.e("NO HAY LIST DE GUAU", String.valueOf(t));

            }
        });


    }//llenarLista



    private void listaImagenesPerritos() {

        //METODO QUE DEVUELVE LA LISTA DE IMAGENES DE PERRITOS

        //PASO 1: SE CREA UNA VARIABLE SERVICE DE LA INTERFACE Y SE CREA RETROFIT
        ApiGuau service = RetrofitClient.getRetrofitInstance().create(ApiGuau.class);

        //PASO 2: LA VARIABLE SERVICE LLAMA AL METODO QUE SE CREO EJ:getBeedImageList RECIBIENDO LA RESPUESTA EN EL OBJETO callImages.
        //PARA ESTE CASO GET LISTA IMAGENES SE LE ASIGNA LA POSICION QUE RECIBIO EN EL METODO ANTERIOR LLENAR LISTA (), EN PERRO1
        Call<ListaImagenesRespuesta> callImages = service.getListaImagenesURL(perro1);



        //PASO 3: SE UTILIZA METODO ENQUEUE PARA MANEJAR LOS RESULTADOS EN SUS METODOS INTERNOS
        callImages.enqueue(new Callback<ListaImagenesRespuesta>() {
            @Override// METODO QUE SE EJECUTA CUANDO LLEGA LA RESPUESTA A LA CONSULTA REALIZADA --> Call<BreedImageListResponse>
            public void onResponse(Call<ListaImagenesRespuesta> call, Response<ListaImagenesRespuesta> response) {

                //SI LA RESPUESTA FUE EXITOSA SE OBTIENE LA LISTA DE IMAGENES EN EL OBJETO images URL USANDO EL METODO response.body()
                //PAA VERIFICAR QUE TODE ESTE FUNCIONADO SE PUEDE RECORRER LA LISTA CON UN ITERADOR FOR
                List<String> imagenesURL = response.body().getListaImagenesURL();//PASAR ESTA LISTA AL FRAGMENTO IMAGNES PERRITOS


                //LE PASO COMO ARGUMENTO LA LISTA CON LAS IMAGENES DEL PERRITO
                pasarAfragmentoImagenesPerritos(imagenesURL);







                /*LLEVARME ESTO PARA FRAG IMANGES PERRITOS

                //INSTANCIAR EL ADAPTADOR Y DARLE COMO PARAMETROS LA LISTA A MOSTRAR
                //AdaptadorListaImagenes adaptadorLista = new AdaptadorListaDeRazas(imagesURL);
                AdaptadorListaImagenes adaptadorListaImagenes=new AdaptadorListaImagenes(imagenesURL);

                //PASO DAR COMO PARAMETRO AL RECYCLER VIEW LAS IMAGENES
                //recicla.setAdapter(AdaptadorListaImagenes);
                */


                Log.e("IMAGENES PERRITOS OK", String.valueOf(imagenesURL));
            }




            @Override// METODO QUE SE EJECUTA CUANDO FALLA LA RESPUESTA A LA CONSULTA REALIZADA --> Call<BreedImageListResponse> EJ: NO HAY INTERNET
            public void onFailure(Call<ListaImagenesRespuesta> call, Throwable t) {
                //SE CREA UN TOAST Y UN LOG PARA VER SI FALLO LA REPUESTA
                //Toast.makeText(MainActivity.this,"FALLO LA CONECXIONNN DE IMAGENES", Toast.LENGTH_SHORT).show();
                Log.e("NO HAY IMG DE GUAU ", String.valueOf(t));

            }
        });

    }// listaImagenesPerritos



    public void pasarAfragmentoImagenesFavoritas(){


        //METODO QUE PERMITE PASAR A FRAGMENTO DE IMAGENES FAVORITAS
        ImagenesFavoritasFragment fragImgFavoritas = new ImagenesFavoritasFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentoUno,fragImgFavoritas,"PASANDO A FRAG IMG FAVORITAS").addToBackStack("PASANDO A BACK").commit();

    }//pasarAfragmentoImagenesFavoritas


    public void pasarAfragmentoImagenesPerritos(List <String> listaPerritosXrazas){


        //METODO QUE PERMITE PASAR A FRAGMENTO DE IMAGENES FAVORITAS
        ImagenesPerritosFragment fragImgPerritos=ImagenesPerritosFragment.newInstance(listaPerritosXrazas);

        //ImagenesPerritosFragment fragImgPerritos = new ImagenesPerritosFragment();  //ESTA LINEA YA NO VA, YA QUE LA DECLARACION DEL FRAGMENTO IMAGENES PERRITOS CAMBIO, POR SOBRE CARGA DE METODOS
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentoUno,fragImgPerritos,"PASANDO A FRAG IMG PERRITOS").addToBackStack("PASANDO A BACK").commit();

    }//pasarAfragmentoImagenesPerritos



}//class fragmento