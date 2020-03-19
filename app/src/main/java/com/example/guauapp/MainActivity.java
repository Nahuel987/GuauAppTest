package com.example.guauapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.guauapp.adaptador.AdaptadorListaDeRazas;
import com.example.guauapp.api.ApiGuau;
import com.example.guauapp.api.RetrofitClient;
import com.example.guauapp.modelo.ListaDeRazasRespuesta;
import com.example.guauapp.modelo.ListaImagenesRespuesta;
import com.example.guauapp.vista.ImagenesFavoritasFragment;
import com.example.guauapp.vista.ListaRazasFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

//INSTACNCIO FRAGMENTO UNO
ListaRazasFragment fragUno;
ImagenesFavoritasFragment fragImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CREO OBJETO DE FRAGMENTO LISTADO RAZAS
        fragUno=new ListaRazasFragment();



        //SE EJECUTA EL ADMINISTRADOR DEL FRAGMENTO, SE INICIA SU TRANSACCION Y SE REMPLAZA CONTENEDOR FRAGMENTO (ID DE MAIN ACTIVITY XML POR OBJETO FRAG UNO DE FRAGMENTO LISTA RAZA PERROS)
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentoUno,fragUno).commit();




    }//on create



}//class
