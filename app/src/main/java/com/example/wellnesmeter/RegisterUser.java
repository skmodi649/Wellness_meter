package com.example.wellnesmeter;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {
    private TextView registerUser;
    private EditText fullname , age , email , password;
    private CheckBox checkBox;
    private FirebaseAuth mAuth;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        checkBox = (CheckBox)findViewById(R.id.agree);
        registerUser = (Button) findViewById(R.id.reg);
        registerUser.setOnClickListener(this);
        fullname = (EditText) findViewById(R.id.user);
        age = (EditText) findViewById(R.id.age);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.reg:
                checkBox = (CheckBox)view;
                registerUser();
                break;
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

        if(Patterns.EMAIL_ADDRESS.matcher(elecmail).matches() == false){
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
                            if(task.isSuccessful() && checkBox.isChecked()) {
                                    Toast.makeText(RegisterUser.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                            }
                                // redirect ot Login Layout is registered successfully

                            else{
                                Toast.makeText(RegisterUser.this,"Failed to register! Try Again!",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterUser.this,"Failed to register! Try Again!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

