package com.example.guauapp.vista;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.guauapp.R;
import com.example.guauapp.adaptador.AdaptadorListaImagenes;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImagenesPerritosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImagenesPerritosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    List <String> recibeURLS =new ArrayList<>();

    // TODO: Rename and change types of parameters
    private ArrayList<String> mParam1;
    private RecyclerView reciclaImagenesPerritos;
    private AdaptadorListaImagenes adaptadorListaImagenes;


    public ImagenesPerritosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImagenesPerritosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImagenesPerritosFragment newInstance(String param1, String param2) {
        ImagenesPerritosFragment fragment = new ImagenesPerritosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }


    public static ImagenesPerritosFragment newInstance(List<String> param1){

        ImagenesPerritosFragment fragment=new ImagenesPerritosFragment();
        Bundle args=new Bundle();
        args.putStringArrayList(ARG_PARAM1, (ArrayList<String>) param1);
        Log.e("RECIBO LISTA", String.valueOf(param1));
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getStringArrayList(ARG_PARAM1);
            recibeURLS = mParam1;
            Log.e("RECIBO URLSSS", String.valueOf(recibeURLS));

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista = inflater.inflate(R.layout.fragment_imagenes_perritos, container, false);

        reciclaImagenesPerritos= (RecyclerView) vista.findViewById(R.id.RecyclerListaImagenes);

        reciclaImagenesPerritos.setLayoutManager(new LinearLayoutManager(getContext()));

        adaptadorListaImagenes=new AdaptadorListaImagenes(recibeURLS,getContext());

        reciclaImagenesPerritos.setAdapter(adaptadorListaImagenes);

        return vista;
    }
}
