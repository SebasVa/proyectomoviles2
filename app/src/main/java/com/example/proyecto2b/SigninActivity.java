package com.example.proyecto2b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailNuevoUsuario;
    private EditText passwordNuevoUsuario;
    private EditText confirPassNuevoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();

        emailNuevoUsuario=findViewById(R.id.emailNuevoUsuario);
        passwordNuevoUsuario=findViewById(R.id.passwordNuevoUsuario);
        confirPassNuevoUsuario=findViewById(R.id.confirPassNuevoUsuario);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void signinUsuario (View view){

        if (passwordNuevoUsuario.getText().toString().equals(confirPassNuevoUsuario.getText().toString())){

            mAuth.createUserWithEmailAndPassword(emailNuevoUsuario.getText().toString(),passwordNuevoUsuario.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                //Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getApplicationContext(), "Usuario registrado.", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                //udpateUI(user);
                            } else {
                                //If sign in falls, display a message to hte user.
                                Toast.makeText(getApplicationContext(), "Usuario no registrado.", Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });
        }else{
            Toast.makeText(this, "Las contraseñas no coiciden", Toast.LENGTH_SHORT).show();
        }


    }
}