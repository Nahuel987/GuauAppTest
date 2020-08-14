package com.example.guauapp.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guauapp.R;

import java.util.List;

public class AdaptadorListaDeRazas extends RecyclerView.Adapter<AdaptadorListaDeRazas.ViewHolderDatosRazas> implements View.OnClickListener {


    List<String> perriwis;



    private View.OnClickListener listener;


    public AdaptadorListaDeRazas(List<String> perriwis) {
        this.perriwis = perriwis;
    }



    @NonNull
    @Override
    public ViewHolderDatosRazas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_razas,null,false);

        view.setOnClickListener(this);

        return new ViewHolderDatosRazas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatosRazas holder, int position) {

        holder.asignarDatos(perriwis.get(position));

    }

    @Override
    public int getItemCount() {

        return perriwis.size();
    }


    public void setOnClickListener(View.OnClickListener listener){

        this.listener=listener;

    }


    @Override
    public void onClick(View view) {

        if(listener!=null){
            listener.onClick(view);
        }

    }


    public class ViewHolderDatosRazas extends RecyclerView.ViewHolder {

        TextView dato;

        public ViewHolderDatosRazas(@NonNull View itemView) {
            super(itemView);

        dato= (TextView) itemView.findViewById(R.id.idDato);
        }

        public void asignarDatos(String datos) {

            dato.setText(datos);
        }

    }

}
