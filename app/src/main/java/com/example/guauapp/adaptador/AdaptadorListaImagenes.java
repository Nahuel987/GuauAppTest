package com.example.guauapp.adaptador;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.guauapp.R;
import com.example.guauapp.modelo.ListaImagenesRespuesta;

import java.util.List;

public class AdaptadorListaImagenes extends RecyclerView.Adapter<AdaptadorListaImagenes.ViewHolderImagenesRaza> {


    //PASO 1 SE CREA LA REFERENCIA DE LA LISTA A MOSTRAR
    List<String> perriwisImagenesUrl;// LA LISTA A MOSTRAR ES STRING  DE UN OBJETO LISTA DE RAZAS RESPUESTA

    Context context;


    //EVENETO ON LONG CLICK LISTENER PASO 2 DECLARAR VARIABLE PARA ASOCIARLA AL METODO
//    private View.OnLongClickListener listener;


    //PASO 2 SE CREA CONSTRUCTOR DEL ADAPTADOR QUE TENDRA UNA LISTA COMO SU ATRIBUTO
    public AdaptadorListaImagenes(List<String> perriwisImagenesUrl) {
        this.perriwisImagenesUrl = perriwisImagenesUrl;
    }

    public AdaptadorListaImagenes(List<String> perriwisImagenesUrl, Context context) {
        this.perriwisImagenesUrl = perriwisImagenesUrl;
        this.context = context;
    }

    @NonNull
    @Override//METODO QUE ENLAZA EL ADAPTADOR CON EL ARCHIVO ITEM LISTA FOTOS XML
    public ViewHolderImagenesRaza onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //PASO 5 INFLAR EL VIEW Y RETORNARLO COMO UNA INSTANCIA
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_fotos,parent,false);//SI NO LLEVA PARENT LA APP NO MUESTRA LAS IMAGENES

        //EVENETO ON LONG CLICK LISTENER PASO 3 PONER A ESCUCHAR EVENTO ON CLICK EN CLASE ADAPTADOR   this ES PARA QU3E PUEDA ESCUCHAR EL EVENTO DE SELCCIONM
//        view.setOnLongClickListener(this);

        return new ViewHolderImagenesRaza(view);
    }

    @Override//ESTABLECE LA COMUNICACION ENTRE LA CLASE ADAPTADOR Y LA CLASE INTERNA VIEW HOLDER
    public void onBindViewHolder(@NonNull ViewHolderImagenesRaza holder, int position) {

        //PASO 7 SE ASIGNA UN OBJETO HOLDER CON UN METODO DE LA CLASE VIEW HOLDER Y LA LISTA A MOSTRAR
        //holder.asignarImagen(perriwisImagenesUrl.get(position));

        //holder.asignarDatos(perriwisImagenesUrl.get(position));//ESTA LINEA SE USO PARA VER SI SE RECIBIAN LAS URL

        //SE CREA VARIABLE FOTO URL LA CUAL RECIBIRA UNA URL DE ACUERDO A LA POSICION DEVUELTA POR GET POSISITION()
        String fotoUrlFavoritos=perriwisImagenesUrl.get(position);

        //SE USA LA LIBRERIA GLADE LA CUAL SE ENCARGA DE MOSTRAR UNA IMAGEN EN UN RECYCLER VIEW EN BASE A UNA URL
        Glide.with(holder.imagenGuauView.getContext())
                .load(fotoUrlFavoritos)         //RECIBE LA URL
                .centerCrop()                   //CENTRA LA IMAGEN
                .into(holder.imagenGuauView);   //LE DICE DONDE TIENE QUE MOSTRARLA


        /*PRUEBA DE IMPLEMENTAR UN EVENTO ON LONG LISTENER*/

        holder.imagenGuauView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(context, "MERENGUE MERENGUE", Toast.LENGTH_SHORT).show();

                return false;
            }
        });














    }//onBindViewHolder

    @Override//RETORNA EL TAMAÑO DE LA LISTA
    public int getItemCount() {

        //PASO 6
        return perriwisImagenesUrl.size();
    }



    //EVENTO ON LONG CLICK LISTENER PASO 4 SE CREA METODO PARA ESCUCHAR AL EVENTO ON CLICK
//    public void setOnLongClickListener(View.OnLongClickListener listener){
//
//        this.listener=listener;
//
//    }
//
//    @Override//EVENTO ON LONG CLICK LISTENER PASO 1 CREAR METODO PREVIA IMPLEMENTENCION DE ONCLICK LISTENER
//    public boolean onLongClick(View view) {
//
//        //EVENTO ON CLICK LISTENER PASO 5 VALIDAR SI EL EVENTO LISTENER ES DISTINTO DE VACIO, PARA QUE DEVUELVA UNA VISTA VIEW
//        if(listener!=null){
//            listener.onLongClick(view);
//        }
//        return false;
//    }




    public class ViewHolderImagenesRaza extends RecyclerView.ViewHolder {

        //PASO 3 SE HACE LA REFERENCIA A IMAGEN VIEW DEL XML ITEM LISTA FOTOS
        ImageView imagenGuauView;
        //TextView datoImagen;


        public ViewHolderImagenesRaza(@NonNull View itemView) {
            super(itemView);

            //PASO 4 SE HACE LA REFERENCIA A IMAGEN VIEW imagenGuauView CON UN FIND VIEW BY ID
            imagenGuauView= (ImageView) itemView.findViewById(R.id.contenedorImagenes);

            //se prueba si se recibe la url
            //datoImagen= (TextView) itemView.findViewById(R.id.textoURLprueba);
        }

        /*
        public void asignarImagen(ImageView imageView) {

            //PASO 9 SE ASIGNA A VARIABLE ImageView LA INFROMACION QUE VA A LLEGAR EN ESTE CASO STRING
            //ImageView.
            // COMO ASIGNAR IMAGEN?

        }

        */

/*
        //PASO 8 SE CREA EL METODO ASIGNAR DATOS
        public void asignarDatos(String datos) {

            //PASO 9 SE ASIGNA A VARIABLE DATO LA INFROMACION QUE VA A LLEGAR EN ESTE CASO STRING
            //datoImagen.setText(datos);
            //Log.e("RECIBO LISTA", String.valueOf(datoImagen));
        }
*/



    }//clase View Holder ImagenesRaza




}//clase adptador
