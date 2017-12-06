package com.example.root.dailynews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by ${Harpal} on 4/12/17.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private Button mRegisterButton;
    private ProgressBar mProgressBar;
    private FirebaseAuth mFAuth;
    private Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        mContext = this;

        mEmailField = (EditText)findViewById(R.id.email_text);
        mPasswordField = (EditText)findViewById(R.id.password_text);
        mLoginButton = (Button)findViewById(R.id.login_btn);
        mRegisterButton = (Button)findViewById(R.id.register_btn);
        mProgressBar = new ProgressBar(mContext);

        mFAuth = FirebaseAuth.getInstance();

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,RegistrationActivity.class));
            }
        });





    }
}
