package com.example.guauapp.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guauapp.R;

import java.util.List;

public class AdaptadorListaDeRazas extends RecyclerView.Adapter<AdaptadorListaDeRazas.ViewHolderDatosRazas>{

    //PASO 1 SE CREA LA REFERENCIA DE LA LISTA A MOSTRAR
    List<String> perriwis;//VERIFICAR SI LA LISTA A MOSTRAR ES STRING O DE UN OBJETO LISTA DE RAZAS RESPUESTA

    //PASO 2 SE CREA CONSTRUCTOR DEL ADAPTADOR QUE TENDRA UNA LISTA COMO SU ATRIBUTO
    public AdaptadorListaDeRazas(List<String> perriwis) {
        this.perriwis = perriwis;
    }



    @NonNull
    @Override//METODO QUE ENLAZA EL ADAPTADOR CON EL ARCHIVO ITEM LISTA RAZAS XML
    public ViewHolderDatosRazas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //PASO 5 INFLAR EL VIEW Y RETORNARLO COMO UNA INSTANCIA
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_razas,null,false);

        return new ViewHolderDatosRazas(view);
    }

    @Override//ESTABLECE LA COMUNICACION ENTRE LA CLASE ADAPTADOR Y LA CLASE INTERNA VIEW HOLDER
    public void onBindViewHolder(@NonNull ViewHolderDatosRazas holder, int position) {

        //PASO 7 SE ASIGNA UN OBJETO HOLDER CON UN METODO DE LA CLASE VIEW HOLDER Y LA LISTA A MOSTRAR
        holder.asignarDatos(perriwis.get(position));

    }

    @Override//RETORNA EL TAMAÑO DE LA LISTA
    public int getItemCount() {

        //PASO 6
        return perriwis.size();
    }




    public class ViewHolderDatosRazas extends RecyclerView.ViewHolder {

        //PASO 3 SE HACE LA REFERENCIA A TEXT VIEW DEL XML ITEM LISTA RAZAS
        TextView dato;

        //CONSTRUCTOR CLASE VIEW HOLDER DATOS RAZAS
        public ViewHolderDatosRazas(@NonNull View itemView) {
            super(itemView);

        //PASO 4 SE HACE LA REFERENCIA A TEXTVIEW dato CON UN FIND VIEW BY ID
        dato= (TextView) itemView.findViewById(R.id.idDato);
        }

        //PASO 8 SE CREA EL METODO ASIGNAR DATOS
        public void asignarDatos(String datos) {

            //PASO 9 SE ASIGNA A VARIABLE DATO LA INFROMACION QUE VA A LLEGAR EN ESTE CASO STRING
            dato.setText(datos);
        }




    }//class View Holder Datos Razas




}//class Adaptador
