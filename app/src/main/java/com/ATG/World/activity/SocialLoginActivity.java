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
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.models.User_details;
import com.ATG.World.models.WsLoginResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Response;

public class SocialLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout social_login_id;
    private CallbackManager callbackManager;
    private TwitterAuthClient mTwitterAuthClient;
    private int socialFlag;
    private String mStrLastNAME = "";
    private String mStrFBLogin = "0", mStrGoogleLogin = "0", mStrTwitterLogin = "0";

    private Button loginFacebookButton;
    private Button twitterLoginButton;
    private GoogleSignInClient mGoogleSignInClient;
    private static int RC_FB_SIGN_IN;
    private static int RC_SIGN_IN = 100;
    private Button loginEmailButton;
    private Button signupButton;
    private Button more_options_button;
    private AtgService retrofit;
    private View mLoginView;

    //Facebook Details
    com.facebook.login.LoginManager fbLoginManager;
    private String fbEmail;
    private String fbFirstName;
    private String fbLastLame;
    private String fbId;

    //Google Details
    private String googleFirstName;
    private String googleLastLame;
    private String googleEmail;
    private String googleId;

    //Twitter Details
    private String twitterId;
    private String twitterUsername;

    // Progress Bar
    private ProgressBar mProgressView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_social_login);
        setUI();
        initTwitter();
        initGoogleLogin();
        initFacebookLogin();


        signupButton = findViewById(R.id.sign_up_email);
        social_login_id = findViewById(R.id.social_login_id);

        loginFacebookButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.sign_up_email);
        loginEmailButton = findViewById(R.id.login_email);
        more_options_button = findViewById(R.id.more_options);

        mLoginView = findViewById(R.id.login_view);
        mProgressView = findViewById(R.id.login_progress_home);
        loginEmailButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);

        // More options button action

        more_options_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (more_options_button.getText().equals("More options")) {
                    loginFacebookButton.setVisibility(View.VISIBLE);
                    twitterLoginButton.setVisibility(View.VISIBLE);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                    params.setMargins(0, 110, 0, 0);
                    loginEmailButton.setLayoutParams(params);
                    loginEmailButton.requestLayout();
                    more_options_button.setVisibility(View.GONE);
                }


            }
        });

        // Make Sign In Green And Bold

        SpannableString text = new SpannableString("Already have an account? Sign In");

        text.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 24, 0);

        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.social_notification_bar)), 25, 32, 0);

        text.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 25, 32, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        loginEmailButton.setText(text, TextView.BufferType.SPANNABLE);

        // To make notification bar transparent

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        // Facebook login button action
        loginFacebookButton.setOnClickListener(new View.OnClickListener() {

            public void failure(TwitterException exception) {
                Toast.makeText(SocialLoginActivity.this, "Network problem", Toast.LENGTH_SHORT).show();
            }

            public void onClick(View v) {

                //Progress Bar
                progressbaraction();

                fbLoginManager.logInWithReadPermissions(SocialLoginActivity.this, Arrays.asList("public_profile", "email"));


            }
        });


        retrofit = AtgClient.getClient().create(AtgService.class);


    }

    private void setUI() {
        loginEmailButton = findViewById(R.id.login_email);
        signupButton = findViewById(R.id.sign_up_email);
        loginFacebookButton = findViewById(R.id.login_button);
        loginEmailButton = findViewById(R.id.login_email);
        signupButton = findViewById(R.id.sign_up_email);
        more_options_button = findViewById(R.id.more_options);
        mLoginView = findViewById(R.id.login_view);
        mProgressView = findViewById(R.id.login_progress_home);
        loginEmailButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);

        initTwitter();
        initGoogleLogin();
        initFacebookLogin();


    }

    private void initTwitter() {


        mTwitterAuthClient = new TwitterAuthClient();

        twitterLoginButton = findViewById(R.id.login_button_twitter);
        mTwitterAuthClient= new TwitterAuthClient();
        twitterLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Progress Bar
                progressbaraction();

                mTwitterAuthClient.authorize(SocialLoginActivity.this, new com.twitter.sdk.android.core.Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                        showProgress(true);
                        TwitterSession user = result.data;
                        twitterId = String.valueOf(user.getUserId());
                        twitterUsername = user.getUserName();
                        Log.e("twitter", twitterId + twitterUsername);
                        socialFlag = 3;
                        socialLogin();
                    }

                    @Override
                    public void failure(TwitterException e) {
                        Toast.makeText(SocialLoginActivity.this, "Network problem", Toast.LENGTH_SHORT).show();
                    }


                });


            }
        });
    }

    private void initGoogleLogin() {
        Button signInButton = findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void initFacebookLogin() {

        fbLoginManager = com.facebook.login.LoginManager.getInstance();

        callbackManager = CallbackManager.Factory.create();
        fbLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        LoginManager.getInstance().logOut();
                        try {
                            showProgress(true);
                            fbEmail = object.getString("email");
                            fbId = object.getString("id");
                            String mName = object.getString("name");
                            String[] name = mName.split(" ");
                            fbFirstName = name[0];
                            fbLastLame = name[1];

                            socialFlag = 1;

                            socialFlag = 1;

                            socialLogin();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,email,picture");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override

//            public void onError(FacebookException exception) {
//                Toast.makeText(SocialLoginActivity.this, "Network prob", Toast.LENGTH_SHORT).show();
//                // App code

            public void onError(FacebookException e) {
                Toast.makeText(SocialLoginActivity.this, "Network problem", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void signInGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            showProgress(true);
            String personName = acct.getDisplayName();
            String[] userName = personName.split(" ");
            googleFirstName = userName[0];
            googleLastLame = userName[1];
            googleEmail = acct.getEmail();
            googleId = acct.getId();

            socialFlag = 2;
            socialLogin();
        }
        mGoogleSignInClient.signOut();
    }

    private void socialLogin() {
        retrofit2.Callback<WsLoginResponse> callback = new retrofit2.Callback<WsLoginResponse>() {
            @Override
            public void onResponse(Call<WsLoginResponse> call, Response<WsLoginResponse> response) {
                Log.e("CHECK", response.message());
                Log.e("CHECK", call.request().url() + "");
                WsLoginResponse wsLoginResponse = response.body();

                if (wsLoginResponse != null) {

                    if (wsLoginResponse.getError_code() == 0) {
                        Toast.makeText(SocialLoginActivity.this, wsLoginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        User_details userDetails = wsLoginResponse.getUser_details();

                        if (userDetails.getLast_name() != null)
                            mStrLastNAME = userDetails.getLast_name();

                        if (socialFlag == 1) {
                            mStrFBLogin = "1";
                        } else {
                            mStrFBLogin = "0";
                        }
                        if (socialFlag == 2) {
                            mStrGoogleLogin = "1";
                        } else {
                            mStrGoogleLogin = "0";
                        }
                        if (socialFlag == 3) {
                            mStrTwitterLogin = "1";
                        } else {
                            mStrTwitterLogin = "0";
                        }

                        UserPreferenceManager.login(SocialLoginActivity.this,
                                userDetails.getId(),
                                userDetails.getFirst_name(),
                                mStrLastNAME,
                                userDetails.getUser_type(),
                                "",
                                userDetails.getProfile_picture(),
                                "",
                                "",
                                mStrFBLogin,
                                mStrGoogleLogin,
                                "",
                                mStrTwitterLogin,
                                userDetails.getEmail(),
                                ""
                        );
                        Intent intent = new Intent(SocialLoginActivity.this, GroupSelectionActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        finish();
                        startActivity(intent);
                    } else if (wsLoginResponse.getError_code() == 1) {
                        Toast.makeText(SocialLoginActivity.this, wsLoginResponse.getMsg(), Toast.LENGTH_SHORT).show();

                        User_details userDetails = wsLoginResponse.getUser_details();

                        if (userDetails.getLast_name() != null)
                            mStrLastNAME = userDetails.getLast_name();

                        if (socialFlag == 1) {
                            mStrFBLogin = "1";
                        } else {
                            mStrFBLogin = "0";
                        }
                        if (socialFlag == 2) {
                            mStrGoogleLogin = "1";
                        } else {
                            mStrGoogleLogin = "0";
                        }
                        if (socialFlag == 3) {
                            mStrTwitterLogin = "1";
                        } else {
                            mStrTwitterLogin = "0";
                        }

                        UserPreferenceManager.login(SocialLoginActivity.this,
                                userDetails.getId(),
                                userDetails.getFirst_name(),
                                userDetails.getLast_name(),
                                userDetails.getUser_type(),
                                "",
                                userDetails.getProfile_picture(),
                                "",
                                userDetails.getLocation(),
                                mStrFBLogin,
                                mStrGoogleLogin,
                                userDetails.getLinkdin_login(),
                                mStrTwitterLogin,
                                userDetails.getEmail(),
                                userDetails.getMob_no()
                        );
                        Intent intent = new Intent(SocialLoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        finish();
                        startActivity(intent);
                    } else if (wsLoginResponse.getError_code() == 2) {

                        User_details userDetails = wsLoginResponse.getUser_details();

                        if (userDetails.getLast_name() != null)
                            mStrLastNAME = userDetails.getLast_name();

                        if (socialFlag == 1) {
                            mStrFBLogin = "1";
                        } else {
                            mStrFBLogin = "0";
                        }
                        if (socialFlag == 2) {
                            mStrGoogleLogin = "1";
                        } else {
                            mStrGoogleLogin = "0";
                        }
                        if (socialFlag == 3) {
                            mStrTwitterLogin = "1";
                        } else {
                            mStrTwitterLogin = "0";
                        }

                        UserPreferenceManager.login(SocialLoginActivity.this,
                                userDetails.getId(),
                                userDetails.getFirst_name(),
                                userDetails.getLast_name(),
                                userDetails.getUser_type(),
                                "",
                                userDetails.getProfile_picture(),
                                "",
                                userDetails.getLocation(),
                                mStrFBLogin,
                                mStrGoogleLogin,
                                userDetails.getLinkdin_login(),
                                mStrTwitterLogin,
                                userDetails.getEmail(),
                                userDetails.getMob_no()
                        );
                        Toast.makeText(SocialLoginActivity.this, wsLoginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SocialLoginActivity.this, GroupSelectionActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        finish();
                        startActivity(intent);


                    } else if (wsLoginResponse.getError_code() == 2) {
                        Toast.makeText(SocialLoginActivity.this, "Junk data recived", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(SocialLoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                showProgress(false);


            }

            @Override
            public void onFailure(Call<WsLoginResponse> call, Throwable t) {
                showProgress(false);
                Toast.makeText(SocialLoginActivity.this, "Network issue", Toast.LENGTH_SHORT).show();

            }
        };
        if (socialFlag == 1) {
            Call<WsLoginResponse> call = retrofit.getFacebookLogin(fbId, fbFirstName, fbLastLame, fbEmail);
            call.enqueue(callback);
        }
        if (socialFlag == 2) {
            Call<WsLoginResponse> call = retrofit.getGoogleLogin(googleId, googleFirstName, googleLastLame, googleEmail);
            call.enqueue(callback);
        }
        if (socialFlag == 3) {
            Call<WsLoginResponse> call = retrofit.getTwitterLogin(twitterId, twitterUsername);
            call.enqueue(callback);

        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginView.setVisibility(show ? View.GONE : View.VISIBLE);
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
            mLoginView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else if (requestCode == RC_FB_SIGN_IN) {
            callbackManager.onActivityResult(requestCode, resultCode, data);

        } else if (requestCode == TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE) {
            mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);

        } else if (requestCode == TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE) {
            mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);

        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:

                //Progress Bar
                progressbaraction();

                signInGoogle();

                break;
            case R.id.sign_up_email:

                //Progress Bar
                progressbaraction();

                Intent intent = new Intent(SocialLoginActivity.this, SignupActivity.class);
                startActivity(intent);

                break;
            case R.id.login_email:

                //Progress Bar
                progressbaraction();

                Intent intent1 = new Intent(SocialLoginActivity.this, LoginActivity.class);
                startActivity(intent1);

                break;
        }
    }

    public void progressbaraction() {

            mProgressView.getIndeterminateDrawable().

                    setColorFilter(
                            getResources().

                                    getColor(R.color.social_notification_bar),

                            android.graphics.PorterDuff.Mode.SRC_IN);
            mLoginView.setVisibility(View.GONE);
            mProgressView.setVisibility(View.VISIBLE);


            final Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        mProgressView.setVisibility(View.VISIBLE);
                        sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mProgressView.setVisibility(View.GONE);
                                mLoginView.setVisibility(View.VISIBLE);
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }

}








