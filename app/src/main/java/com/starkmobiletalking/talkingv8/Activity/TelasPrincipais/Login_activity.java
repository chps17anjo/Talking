package com.starkmobiletalking.talkingv8.Activity.TelasPrincipais;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.starkmobiletalking.talkingv8.R;


public class Login_activity extends AppCompatActivity {
    private EditText edt_Login_email,edt_Login_senha,Cadastre_se;
    private TextView cadastre_se;
    private Button bt_Login,bt_Login_cadastrar,bt_Google,bt_Fac;
    private CheckBox ckb_mostrar_senha;
    private FirebaseAuth mAuth;
    private GoogleSignInClient client;
    private ProgressBar pgb_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();
        GoogleSignInOptions options= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();
        edt_Login_email= findViewById(R.id.edt_Login_email);
        edt_Login_senha= findViewById(R.id.edt_Login_senha);
        cadastre_se= findViewById(R.id.cadastre_se);
        client= GoogleSignIn.getClient(this,options);


        bt_Google= findViewById(R.id.bt_Google);
        bt_Fac= findViewById(R.id.bt_Fac);
        bt_Login= findViewById(R.id.bt_Login);
        bt_Login_cadastrar= findViewById(R.id.bt_Login_cadastrar);
        ckb_mostrar_senha= findViewById(R.id.ckb_mostrar_senha);
        pgb_login= findViewById(R.id.pgb_login);

        ckb_mostrar_senha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {/*monstrar senha e não mosntrar*/
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    edt_Login_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else{
                    edt_Login_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginEmail= edt_Login_email.getText().toString();
                String loginSenha= edt_Login_senha.getText().toString();

                if(!TextUtils.isEmpty(loginEmail)&& !TextUtils.isEmpty(loginSenha)){
                    pgb_login.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(loginEmail,loginSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                abrirTelaPrincipal();
                            }else{
                                String error = task.getException().getMessage();
                                Toast.makeText(Login_activity.this,""+error,Toast.LENGTH_SHORT).show();
                                pgb_login.setVisibility(View.INVISIBLE);
                                
                            }
                        }
                    });
                }else{
                    Toast.makeText(Login_activity.this, "preencha todos os campos", Toast.LENGTH_SHORT).show();

                }
            }
        });
        bt_Login_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Login_activity.this, Register_activity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_Google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= client.getSignInIntent();
                startActivityForResult(intent,1234);
            }
        });
        bt_Fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent= new Intent(Login.this,Configuracao.class);
               // startActivity(intent);
               // finish();
            }
        });


       // return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1234){
            Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account= task.getResult(ApiException.class);
                AuthCredential credential= GoogleAuthProvider.getCredential(account.getIdToken(),null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    abrirTelaPrincipal();
                                }else{
                                    Toast.makeText(Login_activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            abrirTelaPrincipal();
        }
    }

    private void abrirTelaPrincipal() {
        Intent intent= new Intent(Login_activity.this, TelaPrincipal_activity.class);
        startActivity(intent);

    }}