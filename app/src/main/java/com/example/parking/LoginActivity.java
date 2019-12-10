package com.example.parking;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    ImageView backButton;
    TextView  textView;
    Button Login;

    private EditText Email;
    private EditText Password;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        final EditText Email=(EditText)findViewById(R.id.editText);
        final EditText password=(EditText)findViewById(R.id.editText2);

        backButton=findViewById(R.id.backBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textView=(TextView)findViewById(R.id.regview1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Register.class);
                startActivity(intent);
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();

        progressDialog= new ProgressDialog(this);

        FirebaseUser user=firebaseAuth.getCurrentUser();

        /*if(user !=null)
        {
            finish();
            startActivity(new Intent(LoginActivity.this,mapActivity.class));
        }*/

        Login=(Button)findViewById(R.id.button);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Email.getText().toString(),password.getText().toString());

            }
        });

    }

    private void validate(String userName,String userPassword)
    {
        progressDialog.setMessage("Blink Your Eye! We are Login");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,"Login Sucessful!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,mapActivity.class));
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Login Failed!",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }


}
