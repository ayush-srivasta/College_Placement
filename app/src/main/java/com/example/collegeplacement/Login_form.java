package com.example.collegeplacement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_form extends AppCompatActivity {
    private Button signup,login;
    private EditText mname,mpassword;
    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        login=findViewById(R.id.login_button);
        mname=findViewById(R.id.username);
        mpassword=findViewById(R.id.password_login);
        mauth=FirebaseAuth.getInstance();
        signup=findViewById(R.id.signup_buttton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_form.this,signup_screen.class);
                startActivity(intent);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mname.getText().toString().trim();
                String password=mpassword.getText().toString().trim();
                if(TextUtils.isEmpty(name))
                {
                    mname.setError("This field is required");
                    return;
                }
                if(password.length()<6)
                {
                    mpassword.setError("Password should be more than 6 character");
                    return;
                }
                mauth.signInWithEmailAndPassword(name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login_form.this, "Welcome again", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login_form.this,MainActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(Login_form.this, "Error in the information provided", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
            }
        });
    }
}