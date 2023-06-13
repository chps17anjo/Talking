package com.starkmobiletalking.talkingv8.Activity.TelasPrincipais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.starkmobiletalking.talkingv8.Activity.TelasFrame.Config_Fragment;
import com.starkmobiletalking.talkingv8.Activity.TelasFrame.HomeFragment;
import com.starkmobiletalking.talkingv8.Activity.TelasFrame.Perfil_Fragment;
import com.starkmobiletalking.talkingv8.R;

public class TelaPrincipal_activity extends AppCompatActivity {
    private Button bt_Home,bt_Perfil,bt_Config;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        bt_Home= findViewById(R.id.bt_Home);
        bt_Perfil= findViewById(R.id.bt_Perfil);
        bt_Config= findViewById(R.id.bt_Config);

        bt_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {usarHome();}
        });
        bt_Perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {usarPerfil();}
        });
        bt_Config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {usarConfig();}
        });
    }
    private void usarHome() {
        HomeFragment fragment= new HomeFragment();
        FragmentManager fragmentManager= getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_Principal,fragment).commit();
    }
    private void usarPerfil() {
        Perfil_Fragment fragment= new Perfil_Fragment();
        FragmentManager fragmentManager= getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_Principal,fragment).commit();
    }
    private void usarConfig() {
        Config_Fragment fragment= new Config_Fragment();
        FragmentManager fragmentManager= getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_Principal,fragment).commit();
    }
    @Override
    protected void onStart(){
        super.onStart();
        HomeFragment fragment= new HomeFragment();
        FragmentManager fragmentManager= getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_Principal,fragment).commit();

    }
    private void abrirTelaPrincipal() {
        Intent intent= new Intent(TelaPrincipal_activity.this, Register_activity.class);
        startActivity(intent);

    }
}

