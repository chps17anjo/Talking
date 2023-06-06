package com.starkmobiletalking.talkingv8.Activity.TelasFrame;


import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;

import android.os.Bundle;
import androidx.fragment.app.*;
import com.google.firebase.auth.FirebaseAuth;
import android.view.*;
import android.widget.*;


import com.starkmobiletalking.talkingv8.Activity.TelasPrincipais.Login;
import com.starkmobiletalking.talkingv8.Activity.TelasPrincipais.Register;
import com.starkmobiletalking.talkingv8.Activity.TelasPrincipais.Spash;
import com.starkmobiletalking.talkingv8.Activity.TelasPrincipais.TelaPrincipal;
import com.starkmobiletalking.talkingv8.R;

public class Config_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Button bt_voltar_config,bt_Sair,bt_Bandeira;
    private String mParam2;

    public Config_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment config_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Config_Fragment newInstance(String param1, String param2) {
        Config_Fragment fragment = new Config_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_config_, container, false);
        bt_voltar_config=view.findViewById(R.id.bt_voltar_config);
        bt_Sair= view.findViewById(R.id.bt_Sair);
        bt_Bandeira= view.findViewById(R.id.bt_Bandeira);

        bt_voltar_config.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {setBt_Voltar();}});
         //Toast.makeText(view.getContext(), "sdffsd", Toast.LENGTH_SHORT).show();
        bt_Sair.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {setBt_Sair();}});
        bt_Bandeira.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {setbt_Bandeira();
            Toast.makeText(view.getContext(), "para futuras atualizações", Toast.LENGTH_SHORT).show();}});

        return view;
    }
    private void setBt_Voltar(){
        HomeFragment fragment= new HomeFragment();
        FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_Principal,fragment).commit();

    }
    private void setBt_Sair(){
        FirebaseAuth.getInstance().signOut();
        Context context = getContext();
        String packageName = context.getPackageName();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.clearApplicationUserData();
        /*Intent intent = new Intent(getContext(), Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }
    public void setbt_Bandeira(){

    }


}