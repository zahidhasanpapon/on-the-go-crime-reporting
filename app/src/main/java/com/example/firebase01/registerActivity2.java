package com.example.firebase01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerActivity2 extends AppCompatActivity {

    public EditText emailId, passwd;
    Button btnSignUp;
    TextView signIn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        final Spinner mySpinner= (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter= new ArrayAdapter<String>(registerActivity2.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        firebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email1);
        passwd = findViewById(R.id.password1);
        btnSignUp = findViewById(R.id.registerBtn);
        signIn= findViewById(R.id.loginTV);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= emailId.getText().toString();
                String pass= passwd.getText().toString();
                String userType= mySpinner.getSelectedItem().toString();
                String c= "Citizen";
                String a= "Admin";
                String p= "Police";

                if(email.isEmpty()){
                    emailId.setError("Provide your Email first!");
                    emailId.requestFocus();
                } else if(pass.isEmpty()){
                    passwd.setError("Set your password");
                    passwd.requestFocus();
                } else if(email.isEmpty() && pass.isEmpty()){
                    Toast.makeText(registerActivity2.this,"Fields Empty!",Toast.LENGTH_SHORT).show();
                } else if(!(email.isEmpty() && pass.isEmpty()) && userType.equals(c))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(registerActivity2.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(registerActivity2.this.getApplicationContext(),"SignUp unsuccessful:" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(registerActivity2.this, CitizenActivity.class));
                            }
                        }
                    });
                }

                else if(!(email.isEmpty() && pass.isEmpty()) && userType.equals(p))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(registerActivity2.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(registerActivity2.this.getApplicationContext(),"SignUp unsuccessful:" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(registerActivity2.this, PoliceActivity.class));
                            }
                        }
                    });
                }
                else if(!(email.isEmpty() && pass.isEmpty()) && userType.equals(a))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(registerActivity2.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(registerActivity2.this.getApplicationContext(),"SignUp unsuccessful:" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(registerActivity2.this, AdminActivity.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(registerActivity2.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(registerActivity2.this, MainActivity.class);
                startActivity(I);
            }
        });



    }
    }


