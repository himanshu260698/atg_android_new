package com.ATG.World.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.models.SignUpResponse;
import com.ATG.World.models.User_details;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class SignupActivity extends AppCompatActivity {
    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mFirstview;
    private EditText mLastView;
    private EditText mCnfPasswordView;
    private View mProgressView;
    private View mSignupFormView;
    private AtgService retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        retrofit= AtgClient.getClient().create(AtgService.class);
        //setupActionBar();
        mEmailView = findViewById(R.id.email);
        mPasswordView =findViewById(R.id.password);
        mCnfPasswordView=findViewById(R.id.cnf_password);
        mFirstview=findViewById(R.id.first_name);
        mLastView=findViewById(R.id.last_name);

        mCnfPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptSignup();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignUpButton = (Button) findViewById(R.id.email_sign_up_button);
        mEmailSignUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignup();
            }
        });

        mSignupFormView = findViewById(R.id.signup_form);
        mProgressView = findViewById(R.id.signup_progress);
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void attemptSignup() {

        // Reset errors.
        mFirstview.setError(null);
        mLastView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mCnfPasswordView.setError(null);

        String fname=mFirstview.getText().toString();
        String lname=mLastView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String cnfpassword= mCnfPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if(TextUtils.isEmpty(fname)){
            mFirstview.setError(getString(R.string.error_field_required));
            focusView= mFirstview;
            cancel=true;
        }

        if(TextUtils.isEmpty(lname)){
            mLastView.setError(getString(R.string.error_field_required));
            focusView= mLastView;
            cancel=true;
        }
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (!TextUtils.isEmpty(cnfpassword) && !isPasswordValid(cnfpassword)) {
            mCnfPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mCnfPasswordView;
            cancel = true;
        }
        if(!password.equals(cnfpassword)){
            mCnfPasswordView.setError(getString(R.string.error_field_password_same));
            focusView = mCnfPasswordView;
            cancel = true;
        }
        else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            Call<SignUpResponse> call=retrofit.getEmailSignUp(fname,lname,email,password,"0");
            call.enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                    SignUpResponse signUpResponse=response.body();
                    if (signUpResponse.getError_code().equals("0")) {

                        User_details userDetails=signUpResponse.getUser_details();

                        UserPreferenceManager.login(SignupActivity.this,
                                userDetails.getId(),
                                userDetails.getFirst_name(),
                                userDetails.getLast_name(),
                                userDetails.getUser_type(),
                                "",
                                userDetails.getProfile_picture(),
                                "",
                                userDetails.getLocation(),
                                userDetails.getFb_login(),
                                userDetails.getGoogle_login(),
                                userDetails.getLinkdin_login(),
                                userDetails.getTwitter_login(),
                                userDetails.getEmail(),
                                userDetails.getMob_no()
                        );

                        Intent intent = new Intent(SignupActivity.this, GroupSelectionActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        finish();
                        startActivity(intent);
                    } else if (signUpResponse.getError_code().equals("1")) {
                        Toast.makeText(SignupActivity.this, "Email already exist.", Toast.LENGTH_SHORT).show();

                    } else if (signUpResponse.getError_code().equals("2")) {
                        Toast.makeText(SignupActivity.this, "User email already exist.", Toast.LENGTH_SHORT).show();

                    } else if (signUpResponse.getError_code().equals("3")) {
                        Toast.makeText(SignupActivity.this, "This error i don't know", Toast.LENGTH_SHORT).show();
                    }

                    showProgress(false);


                }

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {
                    Toast.makeText(SignupActivity.this,"Not connected to server",Toast.LENGTH_SHORT).show();
                    showProgress(false);

                }
            });


        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mSignupFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mSignupFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mSignupFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mSignupFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}

