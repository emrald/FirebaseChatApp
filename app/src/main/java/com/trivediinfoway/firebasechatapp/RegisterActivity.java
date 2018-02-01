package com.trivediinfoway.firebasechatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    EditText edtemail,edtpassword,edtname;
    String email = "",password = "",name = "";
    TextView tvsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_register);

        edtemail = (EditText)findViewById(R.id.edtemail);
        edtpassword = (EditText)findViewById(R.id.edtpassword);
        edtname = (EditText)findViewById(R.id.edtname);
        tvsignup = (TextView)findViewById(R.id.tvsignup);

        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = edtemail.getText().toString();
                password = edtpassword.getText().toString();
                name = edtname.getText().toString();

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.e("message...", task.getResult()+" :message...");
                                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                 //   startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                  //  intent.putExtra("name",name+"");
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });


    }
}
