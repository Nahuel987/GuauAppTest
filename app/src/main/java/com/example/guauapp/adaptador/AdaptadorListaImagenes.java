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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdaptadorListaImagenes extends RecyclerView.Adapter<AdaptadorListaImagenes.ViewHolderImagenesRaza> {



    List<String> perriwisImagenesUrl;

    Context context;


    private FirebaseFirestore db;

    public AdaptadorListaImagenes(List<String> perriwisImagenesUrl) {
        this.perriwisImagenesUrl = perriwisImagenesUrl;
    }

    public AdaptadorListaImagenes(List<String> perriwisImagenesUrl, Context context) {
        this.perriwisImagenesUrl = perriwisImagenesUrl;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderImagenesRaza onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_fotos,parent,false);

        return new ViewHolderImagenesRaza(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderImagenesRaza holder, final int position) {

         String fotoUrl=perriwisImagenesUrl.get(position);


        Glide.with(holder.imagenGuauView.getContext())
                .load(fotoUrl)
                .centerCrop()
                .into(holder.imagenGuauView);

        holder.imagenGuauView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                db = FirebaseFirestore.getInstance();


                String fotoBD=perriwisImagenesUrl.get(position);

                Map<String,String> datos=new HashMap<>();


                datos.put("url", fotoBD);

                try {
                    db.collection("fotosGuau").add(datos);
                    Log.e("ENVIO BD OK", String.valueOf(fotoBD));
                    Toast.makeText(context, "GUAPETON ENVIADO A BD", Toast.LENGTH_SHORT).show();
                    return true;
                } catch(Exception e) {
                    Toast.makeText(context, "GUAPETON NO SE FUE A BD", Toast.LENGTH_SHORT).show();
                    Log.e("NO ENVIO BD OK", String.valueOf(fotoBD));
                    return false;
                }

            }
        });

    }

    @Override
    public int getItemCount() {

        return perriwisImagenesUrl.size();
    }


    public class ViewHolderImagenesRaza extends RecyclerView.ViewHolder {

        ImageView imagenGuauView;

        public ViewHolderImagenesRaza(@NonNull View itemView) {
            super(itemView);

            imagenGuauView= (ImageView) itemView.findViewById(R.id.contenedorImagenes);

        }

    }

}
