package com.example.root.dailynews;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mConfirmPasswordField;
    private Button mRegisterButton;
    private ProgressBar mProgressBar;
    private FirebaseAuth mFirebaseAuth;
    private Context mContext;
    private String email, pwd, confirmPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mContext = this;

        mEmailField = (EditText)findViewById(R.id.email_text);
        mPasswordField = (EditText)findViewById(R.id.password_text);
        mConfirmPasswordField = (EditText)findViewById(R.id.confirm_pwd_text);
        mRegisterButton = (Button)findViewById(R.id.register_btn);
        mProgressBar = (ProgressBar)findViewById(R.id.progressbar);




        mFirebaseAuth = FirebaseAuth.getInstance();

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUser();
            }
        });
    }

    private void validateUser()
    {
        email = mEmailField.getText().toString().trim();
        pwd = mPasswordField.getText().toString().trim();
        confirmPwd = mConfirmPasswordField.getText().toString().trim();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(confirmPwd))
        {
           // mEmailField.setError();
            Toast.makeText(mContext,"Fields should not be empty",Toast.LENGTH_SHORT).show();
        }
        else{
            if(!pwd.equalsIgnoreCase(confirmPwd)){
                mConfirmPasswordField.setError("Password should be match");
            }else{
                mProgressBar.setVisibility(View.VISIBLE);
                registerUser();
            }
        }
    }

    private void registerUser(){

        mFirebaseAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //checking if success
                        if(task.isSuccessful()){
                            Toast.makeText(mContext,"User registered successful",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(mContext,LoginActivity.class));
                        }else{
                            Toast.makeText(mContext,"Something Went Wrong.",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
