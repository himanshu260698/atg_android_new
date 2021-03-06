package com.ATG.World.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.models.User_details;
import com.ATG.World.models.WsLoginResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText mEmailView;
    private EditText mPasswordView;
 //   private View mProgressView;
    private View mLoginFormView;
    private AtgService retrofit;
    private Button sign_up_button;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Make Sign Up Green And Bold
        sign_up_button = findViewById(R.id.sign_up_button);
        SpannableString text = new SpannableString("Don't have an account? Sign Up");

        text.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 22, 0);

        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.social_notification_bar)), 23, 30, 0);

        text.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 23, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        sign_up_button.setText(text, TextView.BufferType.SPANNABLE);
        progressBar=findViewById(R.id.login_progress_bar);
        progressBar.setVisibility(View.GONE);
        // To make notification bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        retrofit= AtgClient.getClient().create(AtgService.class);
       //setupActionBar();
        mEmailView = findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        //Sign Up Button Action
        sign_up_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SocialLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
     //   mProgressView = findViewById(R.id.login_progress);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            progressBar.setVisibility(View.VISIBLE);
            Call<WsLoginResponse> call = retrofit.getEmailLogin(email, password, "0", "");
            call.enqueue(new Callback<WsLoginResponse>() {
                @Override
                public void onResponse(Call<WsLoginResponse> call, Response<WsLoginResponse> response) {
                    WsLoginResponse wsLoginResponse = response.body();
                    if (wsLoginResponse != null) {

                        if (wsLoginResponse.getError_code() == 0) {
                            User_details userDetails = wsLoginResponse.getUser_details();

                            UserPreferenceManager.login(LoginActivity.this,
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
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            finish();
                            startActivity(intent);
                            progressBar.setVisibility(View.GONE);
                        } else if (wsLoginResponse.getError_code() == 1) {
                            Toast.makeText(LoginActivity.this, "Please check your credentials.", Toast.LENGTH_SHORT).show();
                            showProgress(false);
                            progressBar.setVisibility(View.GONE);

                        } else if (wsLoginResponse.getError_code() == 2) {
                            Toast.makeText(LoginActivity.this, "This email is not registered with this site.", Toast.LENGTH_SHORT).show();
                            showProgress(false);
                            progressBar.setVisibility(View.GONE);
                        } else if (wsLoginResponse.getError_code() == 3) {
                            Toast.makeText(LoginActivity.this, "Your account has been blocked by administrator!.Please contact administrator to activate your account.", Toast.LENGTH_SHORT).show();
                            showProgress(false);
                            progressBar.setVisibility(View.GONE);
                        } else if (wsLoginResponse.getError_code() == 4) {
                            Toast.makeText(LoginActivity.this, "Please activate your account.", Toast.LENGTH_SHORT).show();
                            showProgress(false);
                            progressBar.setVisibility(View.GONE);
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                    showProgress(false);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<WsLoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "FAil", Toast.LENGTH_LONG).show();
                    showProgress(false);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Please try again", Toast.LENGTH_SHORT).show();

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

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

        //    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
         //   mProgressView.animate().setDuration(shortAnimTime).alpha(
          //          show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
          //      @Override
           //     public void onAnimationEnd(Animator animation) {
          //          mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
          //      }
         //   });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
          //  mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}

