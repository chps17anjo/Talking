package com.starkmobiletalking.talkingv8.Activity.TelasFrame;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Target;
import com.starkmobiletalking.talkingv8.R;


public class Perfil_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button bt_voltar_perfil,bt_img_Perfil,bt_fotoZum;
    private static final int RC_SIGN_IN = 123;
     String usuarioNome,usuarioEmail,emailFoto;
    ProgressBar progressBar;
    // TODO: Rename and change types of parameters
    private ImageView img_perfil;
    private TextView edt_Perfil_nome,edt_Perfil_email;
    private String mParam1;
    private String mParam2;
    private GoogleSignInClient googleSignInClient;
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
        bt_img_Perfil= view.findViewById(R.id.bt_img_Perfil);
        bt_fotoZum= view.findViewById(R.id.bt_fotoZum);
        progressBar= view.findViewById(R.id.progressBar);
        bt_voltar_perfil=view.findViewById(R.id.bt_voltar_perfil);
        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();*/
        edt_Perfil_nome= view.findViewById(R.id.edt_Perfil_nome);
        edt_Perfil_email= view.findViewById(R.id.edt_Perfil_email);

        bt_img_Perfil.setOnClickListener(new View.OnClickListener() {
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
        emailFoto= FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString();

        Picasso.get()
                .load(emailFoto).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        progressBar.setVisibility(View.GONE);
                        Bitmap imagemRedonda = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(imagemRedonda);
                        Paint paint = new Paint();
                        paint.setAntiAlias(true);
                        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                        float radius = bitmap.getWidth() > bitmap.getHeight() ? ((float)bitmap.getHeight()) / 2f : ((float)bitmap.getWidth()) / 2f;
                        canvas.drawCircle(bitmap.getWidth() / 2f, bitmap.getHeight() / 2f, radius, paint);
                        bt_img_Perfil.setBackground(new BitmapDrawable(getResources(), imagemRedonda));
                    }
                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        progressBar.setVisibility(View.GONE);
                    }
                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        progressBar.setVisibility(View.VISIBLE);
                    }


                });





        Log.d("TAG", usuarioNome+"|"+usuarioEmail+"|"+emailFoto);
    }
}