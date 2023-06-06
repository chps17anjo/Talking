package com.starkmobiletalking.talkingv8.Activity.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.starkmobiletalking.talkingv8.Activity.TelasPrincipais.Login;
import com.starkmobiletalking.talkingv8.Activity.TelasPrincipais.TelaPrincipal;
import com.starkmobiletalking.talkingv8.R;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser= FirebaseAuth.getInstance().getCurrentUser();

        if(currentuser== null){
            Intent intent= new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent= new Intent(MainActivity.this, TelaPrincipal.class);
            startActivity(intent);
            finish();
        }
    }
}