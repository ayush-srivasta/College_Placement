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

public class signup_screen extends AppCompatActivity {
    private EditText mfullName,memail,mpassword,mconform,mphone;
    private Button msubmit;
    private FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        mfullName=findViewById(R.id.name);
        memail=findViewById(R.id.email);
        mpassword=findViewById(R.id.password);
        mconform=findViewById(R.id.password_conform);
        mphone=findViewById(R.id.phone_number);
        msubmit=findViewById(R.id.submit_button);
        fAuth=FirebaseAuth.getInstance();
        msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mfullName.getText().toString().trim();
                String email=memail.getText().toString().trim();
                String password=mpassword.getText().toString().trim();
                String conform_pass=mconform.getText().toString().trim();
                String phone=mphone.getText().toString().trim();
                if(TextUtils.isEmpty(name))
                {
                    mfullName.setError("Name is Required");
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    mfullName.setError("Email is Required");
                    return;
                }
                if(!password.equals(conform_pass))
                {
                    Toast.makeText(signup_screen.this, "Password did not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6)
                {
                    mpassword.setError("Password must be 6 character long");
                    return;
                }
                if(phone.length()!=10)
                {
                    mphone.setError("Phone number is not valid");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(signup_screen.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(signup_screen.this,MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(signup_screen.this, "Error in the form", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
            }
        });
    }
}