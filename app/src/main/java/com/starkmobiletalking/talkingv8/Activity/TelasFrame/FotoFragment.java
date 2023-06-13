package com.starkmobiletalking.talkingv8.Activity.TelasFrame;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.squareup.picasso.Picasso;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Target;
import com.starkmobiletalking.talkingv8.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FotoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button bt_sair_foto,bt_fotoZum;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FotoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FotoFragment newInstance(String param1, String param2) {
        FotoFragment fragment = new FotoFragment();
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_foto, container, false);
        bt_sair_foto=view.findViewById(R.id.bt_sair_foto);
        bt_fotoZum=view.findViewById(R.id.bt_fotoZum);
        bt_sair_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sairPerfil();
            }
        });
    return view;}

    private void sairPerfil() {
        Perfil_Fragment fragment= new Perfil_Fragment();
        FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_Principal,fragment).commit();
    }
    @Override
    public void onStart(){
        super.onStart();

         String emailFotoZu= FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString();


         Picasso.get().load(emailFotoZu).into(new Target() {
             @Override
             public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                 bt_fotoZum.setBackground(new BitmapDrawable(getResources(),bitmap));
             }

             @Override
             public void onBitmapFailed(Exception e, Drawable errorDrawable) {

             }

             @Override
             public void onPrepareLoad(Drawable placeHolderDrawable) {

             }
         });

    }
}
