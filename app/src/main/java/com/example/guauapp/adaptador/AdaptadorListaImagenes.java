package com.example.guauapp.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.guauapp.R;
import com.example.guauapp.modelo.ListaImagenesRespuesta;

import java.util.List;

public class AdaptadorListaImagenes extends RecyclerView.Adapter<AdaptadorListaImagenes.ViewHolderImagenesRaza> {


    //PASO 1 SE CREA LA REFERENCIA DE LA LISTA A MOSTRAR
    List<String> perriwisImagenesUrl;// LA LISTA A MOSTRAR ES STRING  DE UN OBJETO LISTA DE RAZAS RESPUESTA

    //PASO 2 SE CREA CONSTRUCTOR DEL ADAPTADOR QUE TENDRA UNA LISTA COMO SU ATRIBUTO
    public AdaptadorListaImagenes(List<String> perriwisImagenesUrl) {
        this.perriwisImagenesUrl = perriwisImagenesUrl;
    }



    @NonNull
    @Override//METODO QUE ENLAZA EL ADAPTADOR CON EL ARCHIVO ITEM LISTA FOTOS XML
    public ViewHolderImagenesRaza onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //PASO 5 INFLAR EL VIEW Y RETORNARLO COMO UNA INSTANCIA
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_fotos,null,false);

        return new ViewHolderImagenesRaza(view);
    }

    @Override//ESTABLECE LA COMUNICACION ENTRE LA CLASE ADAPTADOR Y LA CLASE INTERNA VIEW HOLDER
    public void onBindViewHolder(@NonNull ViewHolderImagenesRaza holder, int position) {

        //PASO 7 SE ASIGNA UN OBJETO HOLDER CON UN METODO DE LA CLASE VIEW HOLDER Y LA LISTA A MOSTRAR
        //holder.asignarImagen(perriwisImagenesUrl.get(position));
        /*
        Glide.with(holder.imagenGuauView.getContext())
                .load(ListaImagenesRespuesta.get())
                .centerCrop()
                .into(holder.imagenGuauView);
                COMO ASIGNAR IMAGEN PARTE DOIS  ????

    */


    }

    @Override//RETORNA EL TAMAÑO DE LA LISTA
    public int getItemCount() {

        //PASO 6
        return perriwisImagenesUrl.size();
    }




    public class ViewHolderImagenesRaza extends RecyclerView.ViewHolder {

        //PASO 3 SE HACE LA REFERENCIA A IMAGEN VIEW DEL XML ITEM LISTA FOTOS
        ImageView imagenGuauView;


        public ViewHolderImagenesRaza(@NonNull View itemView) {
            super(itemView);

            //PASO 4 SE HACE LA REFERENCIA A IMAGEN VIEW imagenGuauView CON UN FIND VIEW BY ID
            itemView= (ImageView) itemView.findViewById(R.id.contenedorImagenes);
        }

        public void asignarImagen(ImageView imageView) {

            //PASO 9 SE ASIGNA A VARIABLE ImageView LA INFROMACION QUE VA A LLEGAR EN ESTE CASO STRING
            //ImageView.
            // COMO ASIGNAR IMAGEN?

        }




    }//clase View Holder ImagenesRaza




}//clase adptador
