package com.example.root.dailynews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by ${Harpal} on 4/12/17.
 */

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 101;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private Button mRegisterButton;
    private Button mLoginWithGplus;
    private ProgressBar mProgressBar;
    private FirebaseAuth mFAuth;
    private Context mContext;
    private String email;
    private String pwd;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        mContext = this;

        mEmailField = (EditText)findViewById(R.id.email_text);
        mPasswordField = (EditText)findViewById(R.id.password_text);
        mLoginButton = (Button)findViewById(R.id.login_btn);
        mRegisterButton = (Button)findViewById(R.id.register_btn);
        mProgressBar = (ProgressBar)findViewById(R.id.progressbar);
        mLoginWithGplus = (Button)findViewById(R.id.login_gplus);

        mFAuth = FirebaseAuth.getInstance();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("538483662229-9gd56sk8m7lh8lffunvamksdi0rv2lch.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by googleSignInOptions.
        mGoogleSignInClient = GoogleSignIn.getClient(mContext, googleSignInOptions);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,RegistrationActivity.class));
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmailField.getText().toString().trim();
                pwd = mPasswordField.getText().toString().trim();
                signIn(email,pwd);
            }
        });

        mLoginWithGplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               signInWithGplus();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mFAuth.getCurrentUser();
        if(user!=null)
        {
            startActivity(new Intent(mContext,DashboardActivity.class));
        }
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);
    }

    private boolean validateForm(String email, String password) {
        boolean valid = true;
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    private void signIn(String email,String pwd)
    {


        if(!validateForm(email,pwd))
        {
            return;
        }
        mProgressBar.setVisibility(View.VISIBLE);

        mFAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mProgressBar.setVisibility(View.GONE);
                    startActivity(new Intent(mContext,DashboardActivity.class));
                    finish();
                }else{
                    Toast.makeText(mContext, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void signInWithGplus() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        mProgressBar.setVisibility(View.VISIBLE);


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mFAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mFAuth.getCurrentUser();
                            startActivity(new Intent(mContext,DashboardActivity.class));
                            finish();
                       //     updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(mContext, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        //    updateUI(null);
                        }
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
    }
}
