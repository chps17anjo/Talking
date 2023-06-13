package com.starkmobiletalking.talkingv8.Activity.TelasFrame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.starkmobiletalking.talkingv8.Activity.TelasPrincipais.TelaPrincipal_activity;
import com.starkmobiletalking.talkingv8.R;

public class TelaPrincipalAlfabertoActivity extends AppCompatActivity {
    private Button bt_sair_tela_alfabeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal_alfaberto);
        bt_sair_tela_alfabeto=findViewById(R.id.bt_sair_tela_alfabeto);

        bt_sair_tela_alfabeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getBaseContext(),"asasdddd",Toast.LENGTH_SHORT).show();
                sairTelaAlfabeto();
            }
        });

    }
    private void sairTelaAlfabeto() {
        //Toast.makeText(getBaseContext(),"asasdddd",Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(TelaPrincipalAlfabertoActivity.this, TelaPrincipal_activity.class);
        startActivity(intent);




    }
}