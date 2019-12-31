package com.example.firebase01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {




    public EditText loginEmailId, loginPass;
    Button btnLogIn, button , button2;
    TextView signUp;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner mySpinner= (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter= new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        button2 = (Button) findViewById(R.id.button2);

        firebaseAuth= FirebaseAuth.getInstance();
        loginEmailId = findViewById(R.id.loginEmail);
        loginPass= findViewById(R.id.loginPass);
        btnLogIn= findViewById(R.id.btnLogIn);
        signUp= findViewById(R.id.btnSignUp);
        final String c="Citizen";
        final String a="Admin";
        final String p="Police";

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this,PoliceActivity.class);
                startActivity(I);
            }
        });

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });


        authStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if(user !=null)
                {
                    Toast.makeText(MainActivity.this,"User Logging in",Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(MainActivity.this,registerActivity2.class);
                    startActivity(I);
                }
                else{
                    Toast.makeText(MainActivity.this,"Login to continue",Toast.LENGTH_SHORT).show();
                }

            }
        };

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this,registerActivity2.class);
                startActivity(I);
            }
        });





        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail= loginEmailId.getText().toString();
                String userPass= loginPass.getText().toString();
                String userType= mySpinner.getSelectedItem().toString();
                if(userEmail.isEmpty())
                {
                    loginEmailId.setError("Provide your Email first!");
                    loginEmailId.requestFocus();
                } else if(userPass.isEmpty()){
                    loginPass.setError("Enter Password!");
                    loginPass.requestFocus();
                }
                else if(!(userEmail.isEmpty() && userPass.isEmpty()) && userType.equals(c)){
                    firebaseAuth.signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(
                            MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(MainActivity.this, "Not Successful", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        startActivity(new Intent(MainActivity.this, CitizenActivity.class));

                                    }
                                }
                            }
                    );
                }
                else if(!(userEmail.isEmpty() && userPass.isEmpty()) && userType.equals(p)){
                    firebaseAuth.signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(
                            MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(MainActivity.this, "Not Successful", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        startActivity(new Intent(MainActivity.this, PoliceActivity.class));

                                    }
                                }
                            }
                    );
                }
                else if(!(userEmail.isEmpty() && userPass.isEmpty()) && userType.equals(a)){
                    firebaseAuth.signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(
                            MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(MainActivity.this, "Not Successful", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        startActivity(new Intent(MainActivity.this, AdminActivity.class));

                                    }
                                }
                            }
                    );
                }
            }
        });


    }

    private void openActivity() {
        Intent intent = new Intent(this, citizen.class );
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


}