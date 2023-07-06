package com.example.wellnesmeter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {
    private EditText fullname , age , email , password;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private TextView signin;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        TextView registerUser = findViewById(R.id.reg);
        registerUser.setOnClickListener(this);
        fullname = findViewById(R.id.user);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        signin = findViewById(R.id.signin);
        progressBar = findViewById(R.id.progressBar);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterUser.this, MainActivity.class));
            }
        });
    }


    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.reg) {
            registerUser();
        }
    }

    private void registerUser()
    {
        String elecmail = email.getText().toString().trim();
        String elecpass = password.getText().toString().trim();
        String elecfullname = fullname.getText().toString().trim();
        String elecage = age.getText().toString().trim();

        if(elecfullname.isEmpty()){
            fullname.setError("Full name is required");
            fullname.requestFocus();
            return;
        }

        if(elecage.isEmpty()){
            age.setError("Age is required");
            age.requestFocus();
            return;
        }

        if(elecmail.isEmpty()){
            email.setError("Email required");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(elecmail).matches()){
            email.setError("Please provide a valid email id");
            email.requestFocus();
            return;
        }

        if(elecpass.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        if(elecpass.length() < 6){
            password.setError("Password length should be more than 6 characters");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(elecmail , elecpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(elecfullname , elecage , elecmail);

                    FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                    Toast.makeText(RegisterUser.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                    startActivity(new Intent(RegisterUser.this , MainActivity.class));
                            }
                                // redirect ot Login Layout is registered successfully

                            else{
                                Toast.makeText(RegisterUser.this,"Failed to register! Try Again!",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    });
                }
                else{
                    Toast.makeText(RegisterUser.this,"Failed to register! Try Again!",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}

