package com.starkmobiletalking.talkingv8.Activity.TelasPrincipais;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.starkmobiletalking.talkingv8.Activity.Main.MainActivity;
import com.starkmobiletalking.talkingv8.Model.UserModel;
import com.starkmobiletalking.talkingv8.R;

public class Register_activity extends AppCompatActivity {
    private EditText edt_Register_email,edt_Register_senha,edt_Register_senha_confirma,
    edt_Register_nome,edt_Register_sobreNome;
    private CheckBox ckb_mostrar_senha_register;
    private Button bt_Login_Register,bt_Register_cadastrar;
    private ProgressBar pgb_Register;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();
        edt_Register_nome= findViewById(R.id.edt_Register_nome);
        edt_Register_sobreNome= findViewById(R.id.edt_Register_sobreNome);
        edt_Register_email= findViewById(R.id.edt_Register_email);
        edt_Register_senha= findViewById(R.id.edt_Register_senha);
        edt_Register_senha_confirma= findViewById(R.id.edt_Register_senha_confirma);
        bt_Login_Register= findViewById(R.id.bt_Login_Register);
        bt_Register_cadastrar= findViewById(R.id.bt_Register_cadastrar);
        ckb_mostrar_senha_register= findViewById(R.id.ckb_mostrar_senha_register);
        pgb_Register= findViewById(R.id.pgb_Register);

        ckb_mostrar_senha_register.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {/*monstrar senha e não mosntrar*/
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    edt_Register_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    edt_Register_senha_confirma.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else{
                    edt_Register_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edt_Register_senha_confirma.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        bt_Login_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel userModel= new UserModel();

                userModel.setNome(edt_Register_nome.getText().toString());
                userModel.setSobreNome(edt_Register_sobreNome.getText().toString());
                userModel.setEmail(edt_Register_email.getText().toString());

                String senha= edt_Register_senha.getText().toString();
                String senhaconfirma= edt_Register_senha_confirma.getText().toString();

                if(!TextUtils.isEmpty(userModel.getEmail()) && !TextUtils.isEmpty(senha)&
                   !TextUtils.isEmpty(senhaconfirma)&& !TextUtils.isEmpty(userModel.getNome())&& !TextUtils.isEmpty(userModel.getSobreNome())){
                    if(senha.equals(senhaconfirma)){
                        pgb_Register.setVisibility(View.VISIBLE);
                        mAuth.createUserWithEmailAndPassword(userModel.getEmail(),senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    userModel.setId(mAuth.getUid());
                                    userModel.salvar();

                                    abrirTelaPrincipal();
                                }else{
                                    String error;
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthWeakPasswordException e) {
                                        error= "A senha deve conter no mínimo 6 caracteres ";
                                    }catch (FirebaseAuthInvalidCredentialsException e){
                                        error= "Email invalido";
                                    }catch (FirebaseAuthUserCollisionException e){
                                        error= "Email já existe";
                                    }catch (Exception e){
                                        error= "Erro ao efeituar o cadastro";
                                        e.printStackTrace();
                                    }
                                    Toast.makeText(Register_activity.this, error, Toast.LENGTH_SHORT).show();

                                    pgb_Register.setVisibility(View.INVISIBLE);
                                }
                            }
                        });
                    }else{
                        Toast.makeText(Register_activity.this, "A senha deve ser a mesma em ambos os campos!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(Register_activity.this, "preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_Register_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Register_activity.this, Login_activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void abrirTelaPrincipal() {
        Intent intent= new Intent(Register_activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}