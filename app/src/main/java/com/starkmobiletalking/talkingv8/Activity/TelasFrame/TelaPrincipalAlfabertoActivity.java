package com.starkmobiletalking.talkingv8.Activity.TelasFrame;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.starkmobiletalking.talkingv8.Activity.TelasPrincipais.TelaPrincipal_activity;
import com.starkmobiletalking.talkingv8.R;

public class TelaPrincipalAlfabertoActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION_MICROPHONE = 1 ;
    private MediaRecorder mediaRecorder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tela_principal_alfaberto);
        Button bt_sair_tela_alfabeto = findViewById(R.id.bt_sair_tela_alfabeto);
        TextView textView = findViewById(R.id.textView2);
        ImageView imageView3 = findViewById(R.id.imageView3);
        ImageView imageView2 = findViewById(R.id.imageView2);
        Button bt_micro = findViewById(R.id.bt_micro);
        int desiredWidth = 500,desiredWidth2=500;
        imageView3.getLayoutParams().width = desiredWidth;
        imageView3.requestLayout();
        imageView2.getLayoutParams().width = desiredWidth2;
        imageView2.requestLayout();
        String urlLetrasLibras="https://firebasestorage.googleapis.com/v0/b/talkingv8.appspot.com/o/Letra%20Alfabeto%2FA.gif?alt=media&token=d82f371b-9b25-4e0e-b4be-5b3ac5b69549&_gl=1*1whn86w*_ga*MjExNjQ4MTE3NS4xNjgwOTExOTQ2*_ga_CW55HF8NVT*MTY4NjY4ODQ2OC4yNS4xLjE2ODY2OTIyNTguMC4wLjA.";
        String urlLetra="https://firebasestorage.googleapis.com/v0/b/talkingv8.appspot.com/o/Letra%20Alfabeto%2Fletra-A-unscreen.gif?alt=media&token=5517476a-02f7-487a-bcef-0e470dd1e441&_gl=1*163ge3e*_ga*MjExNjQ4MTE3NS4xNjgwOTExOTQ2*_ga_CW55HF8NVT*MTY4NjY4ODQ2OC4yNS4xLjE2ODY2OTI3MzQuMC4wLjA.";
        Glide.with(this).asGif().load(urlLetra).into(imageView3);
        Glide.with(this).asGif().load(urlLetrasLibras).into(imageView2);

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TelaPrincipalAlfabertoActivity.this, "Gif que representa a falar", Toast.LENGTH_SHORT).show();
            }
        });



        bt_micro.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            private boolean mLongClickPerformed;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mHandler = new Handler();
                        mHandler.postDelayed(mLongClickRunnable, 700); // Tempo em milissegundos para considerar como clique longo (700ms)
                        break;
                    case MotionEvent.ACTION_UP:
                        if (!mLongClickPerformed) {
                            if (checkMicrophonePermission()) {
                               // realizarAcaoComMicrofone();// A permissão do microfone já foi concedida
                            } else {
                                requestMicrophonePermission();// Solicitar permissão do microfone
                            }
                            Toast.makeText(TelaPrincipalAlfabertoActivity.this, "Clique normal", Toast.LENGTH_SHORT).show();

                        }else{
                            bt_micro.setBackgroundResource(R.drawable.microfone);

                        }
                        // Reinicia o estado do clique longo
                        mLongClickPerformed = false;
                        if (mHandler != null) {
                            mHandler.removeCallbacks(mLongClickRunnable);
                        }
                        break;
                }
                return false;
            }

            private Runnable mLongClickRunnable = new Runnable() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public void run() {
                    // Ação do clique longo aqui
                    Toast.makeText(TelaPrincipalAlfabertoActivity.this, "gravando audio", Toast.LENGTH_SHORT).show();
                    bt_micro.setBackgroundResource(R.drawable.micro_2);
                    realizarAcaoComMicrofone();
                    mLongClickPerformed = true;
                }
            };
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TelaPrincipalAlfabertoActivity.this, "Gif que representa a  letra em libras", Toast.LENGTH_SHORT).show();
            }
        });

        bt_sair_tela_alfabeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getBaseContext(),"asasdddd",Toast.LENGTH_SHORT).show();
                sairTelaAlfabeto();

            }
        });


    }

    private void captureAudio() {

    }
    private boolean checkMicrophonePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED;
    }
    private void requestMicrophonePermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_PERMISSION_MICROPHONE);
    }
    private void realizarAcaoComMicrofone() {
        // Ação que requer acesso ao microfone aqui
        Toast.makeText(TelaPrincipalAlfabertoActivity.this, "Ação com o microfone", Toast.LENGTH_SHORT).show();
    }

    private void sairTelaAlfabeto() {

        //Toast.makeText(getBaseContext(),"asasdddd",Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(TelaPrincipalAlfabertoActivity.this, TelaPrincipal_activity.class);
        startActivity(intent);

           }
    public void onStart(){
        super.onStart();
    }



}