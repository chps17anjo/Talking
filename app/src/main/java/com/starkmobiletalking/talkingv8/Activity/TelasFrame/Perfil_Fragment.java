package com.starkmobiletalking.talkingv8.Activity.TelasFrame;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.starkmobiletalking.talkingv8.R;


public class Perfil_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button bt_voltar_perfil,bt_img_Perffil;
     String usuarioNome,usuarioEmail;
    // TODO: Rename and change types of parameters
    private ImageView img_perfil;
    private TextView edt_Perfil_nome,edt_Perfil_email;
    private String mParam1;
    private String mParam2;
    FirebaseDatabase db= FirebaseDatabase.getInstance();

    public Perfil_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Perfil1_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Perfil_Fragment newInstance(String param1, String param2) {
        Perfil_Fragment fragment = new Perfil_Fragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil_, container, false);
        bt_img_Perffil= view.findViewById(R.id.bt_img_Perffil);
        bt_voltar_perfil=view.findViewById(R.id.bt_voltar_perfil);
        img_perfil= view.findViewById(R.id.img_perfil);
        edt_Perfil_nome= view.findViewById(R.id.edt_Perfil_nome);
        edt_Perfil_email= view.findViewById(R.id.edt_Perfil_email);
        bt_img_Perffil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //img_perfil.setImageResource(R.drawable.ft);
                chamarFoto();
                  }
        });

        bt_voltar_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBt_Voltar();
            }
        });
        return view;
    }

    private void chamarFoto() {
        FotoFragment fragment= new FotoFragment();
        FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_Principal,fragment).commit();

    }

    private void setBt_Voltar() {
        /*Intent intent = new Intent(getContext(), TelaPrincipal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);/*esta e outra forma que da certo *///*/
        HomeFragment fragment= new HomeFragment();
        FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_Principal,fragment).commit();

    }
    @Override
    public void onStart(){
        super.onStart();
        usuarioNome= FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        edt_Perfil_nome.setText(usuarioNome);
        usuarioEmail= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        edt_Perfil_email.setText(usuarioEmail);



        Log.d("TAG", usuarioNome+"|"+usuarioEmail);
    }
}