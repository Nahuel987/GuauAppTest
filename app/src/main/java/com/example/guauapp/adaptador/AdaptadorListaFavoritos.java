package com.example.guauapp.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.guauapp.R;

import java.util.List;

public class AdaptadorListaFavoritos extends RecyclerView.Adapter<AdaptadorListaFavoritos.ViewHolderImagenesFavoritos>{

    List<String> listaImagenesFavoritos;

    Context context;

    public AdaptadorListaFavoritos(List<String> listaImagenesFavoritos) {
        this.listaImagenesFavoritos = listaImagenesFavoritos;
    }


    public AdaptadorListaFavoritos(List<String> listaIamgenesFavoritos, Context context) {
        this.listaImagenesFavoritos = listaIamgenesFavoritos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderImagenesFavoritos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imagenes_favoritas,parent,false);

        return new ViewHolderImagenesFavoritos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderImagenesFavoritos holder, int position) {

        //SE CREA VARIABLE FOTO URL LA CUAL RECIBIRA UNA URL DE ACUERDO A LA POSICION DEVUELTA POR GET POSISITION()
        String fotoUrl=listaImagenesFavoritos.get(position);

        //SE USA LA LIBRERIA GLADE LA CUAL SE ENCARGA DE MOSTRAR UNA IMAGEN EN UN RECYCLER VIEW EN BASE A UNA URL
        Glide.with(holder.imagenesFavoritos.getContext())
                .load(fotoUrl)
                .into(holder.imagenesFavoritos);


    }

    @Override
    public int getItemCount() {

        return listaImagenesFavoritos.size();
    }

    public class ViewHolderImagenesFavoritos extends RecyclerView.ViewHolder {


        ImageView imagenesFavoritos;

        public ViewHolderImagenesFavoritos(@NonNull View itemView) {
            super(itemView);

            imagenesFavoritos=(ImageView) itemView.findViewById(R.id.contenedorImagenesFavoritos);

        }


    }//class ViewHolderImagenesFavoritos


}//class AdaptadorListaFavoritos
