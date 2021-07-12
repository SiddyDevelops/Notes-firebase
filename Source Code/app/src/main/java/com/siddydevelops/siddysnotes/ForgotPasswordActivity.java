package com.siddydevelops.siddysnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText forgotPassword;
    private Button passwordRecoverButton;
    private TextView goBackToLogin;
    
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        getSupportActionBar().hide();

        forgotPassword = findViewById(R.id.forgotPassword);
        passwordRecoverButton = findViewById(R.id.passwordRecoverButton);
        goBackToLogin = findViewById(R.id.gotoLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        goBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        passwordRecoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = forgotPassword.getText().toString().trim();
                if(mail.isEmpty())
                {
                    Toast.makeText(ForgotPasswordActivity.this, "Enter your Email!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //We send password recover mail
                    firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ForgotPasswordActivity.this, "Mail sent, Recover your password!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(ForgotPasswordActivity.this, "Email is wrong or Account doles not exists!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });



    }
}