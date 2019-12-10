package com.example.parking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    ImageView Backbtn;

    private EditText userName,userPassword,userEmail,userPhone;
    private Button SignUp;

    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Backbtn=findViewById(R.id.backBtn1);
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();

                if(validate()) {

                    String user_email = userEmail.getText().toString().trim();
                    String user_password= userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(Register.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(Register.this,"Registration Sucessful!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this,LoginActivity.class));
                            }
                            else {
                                Toast.makeText(Register.this,"Registration Failed!",Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                }

            }
        });
    }

    private void setupUIViews(){

        userName=(EditText)findViewById(R.id.editText3);
        userPassword=(EditText)findViewById(R.id.editText6);
        userEmail=(EditText)findViewById(R.id.editText4);
        userPhone=(EditText)findViewById(R.id.editText5);
        SignUp=(Button) findViewById(R.id.button2);
    }

    private Boolean validate(){

        Boolean result =false;

        String name=userName.getText().toString();
        String password=userPassword.getText().toString();
        String email=userEmail.getText().toString();
        String phone=userPhone.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty())
        {
            Toast.makeText(this,"Please! Enter All Details",Toast.LENGTH_SHORT).show();
        }
        else {
            result=true;
        }
        return result;
    }


}
