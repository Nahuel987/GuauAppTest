package com.example.guauapp.vista;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.guauapp.R;
import com.example.guauapp.adaptador.AdaptadorListaFavoritos;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImagenesFavoritasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImagenesFavoritasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    List<String> listaImagenesFavoritos= new ArrayList<>();

    List<String> test=new ArrayList<>();

    private RecyclerView reciclaImagenesFavoritos;

    AdaptadorListaFavoritos adaptadorListaFavoritos;

    private FirebaseFirestore db;

    public ImagenesFavoritasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImagenesFavoritasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImagenesFavoritasFragment newInstance(String param1, String param2) {
        ImagenesFavoritasFragment fragment = new ImagenesFavoritasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_imagenes_favoritas, container, false);

        reciclaImagenesFavoritos = vista.findViewById(R.id.RecyclerListaFavoritos);

        reciclaImagenesFavoritos.setAdapter(adaptadorListaFavoritos);

        reciclaImagenesFavoritos.setLayoutManager(new LinearLayoutManager(getContext()));

        db = FirebaseFirestore.getInstance();



          db.collection("fotosGuau")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    Toast.makeText(getContext(), "LLEGARON LOS GUAPOS", Toast.LENGTH_SHORT).show();
                                    Log.d("GATOPALO OK", document.getId() + " => " + document.getData());

                                    listaImagenesFavoritos.add(document.getData().get("url").toString());

                                    Log.e("XXXXX", listaImagenesFavoritos.toString());

                                    adaptadorListaFavoritos = new AdaptadorListaFavoritos(listaImagenesFavoritos, getContext());

                                    reciclaImagenesFavoritos.setAdapter(adaptadorListaFavoritos);

                                    reciclaImagenesFavoritos.setLayoutManager(new LinearLayoutManager(getContext()));
                                }
                            } else {

                                Toast.makeText(getContext(), "NO HAY GUAPETONES PARA MOSTRAR", Toast.LENGTH_SHORT).show();
                                Log.d("GATOPALO NO OK", "Error getting documents: ", task.getException());

                            }
                        }
                    });

        return vista;
    }
}



