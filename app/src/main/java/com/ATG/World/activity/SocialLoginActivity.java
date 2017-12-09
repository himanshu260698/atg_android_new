package com.ATG.World.activity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;
import retrofit2.Call;
import retrofit2.Response;

public class SocialLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private CallbackManager callbackManager;
    private int socialFlag;
    private String mStrLastNAME = "";
    private String mStrFBLogin = "0", mStrGoogleLogin = "0", mStrTwitterLogin = "0";

    private LoginButton loginFacebookButton;
    private TwitterLoginButton twitterLoginButton;
    private GoogleSignInClient mGoogleSignInClient;
    private static int RC_FB_SIGN_IN;
    private static int RC_SIGN_IN=100;
    private Button loginEmailButton;
    private Button signupButton;
    private AtgService retrofit;
    private View mProgressView;
    private View mLoginView;

    //Facebook Details
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_social_login);
        retrofit= AtgClient.getClient().create(AtgService.class);
        initTwitter();
        initGoogleLogin();
        initFacebookLogin();
        setUI();
    }

    private void setUI() {
        loginEmailButton=findViewById(R.id.login_email);
        signupButton=findViewById(R.id.sign_up_email);
        mLoginView = findViewById(R.id.login_view);
        mProgressView = findViewById(R.id.login_progress_home);
        loginEmailButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);

    }

    private void initTwitter() {
        twitterLoginButton = findViewById(R.id.login_button_twitter);
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                showProgress(true);
                TwitterSession user = result.data;
                twitterId = String.valueOf(user.getUserId());
                twitterUsername = user.getUserName();
                Log.e("twitter",twitterId+twitterUsername);
                socialFlag=3;
                socialLogin();
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(SocialLoginActivity.this,"Network prob",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initGoogleLogin() {
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void initFacebookLogin() {
        loginFacebookButton = findViewById(R.id.login_button);
        RC_FB_SIGN_IN=loginFacebookButton.getRequestCode();
        callbackManager = CallbackManager.Factory.create();
        loginFacebookButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginFacebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
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
                            socialFlag=1;
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
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(SocialLoginActivity.this,"Network prob",Toast.LENGTH_SHORT).show();
                // App code
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

            socialFlag=2;
            socialLogin();
        }
        mGoogleSignInClient.signOut();
    }

    private void socialLogin() {
        retrofit2.Callback<WsLoginResponse> callback=new retrofit2.Callback<WsLoginResponse>() {
            @Override
            public void onResponse(Call<WsLoginResponse> call, Response<WsLoginResponse> response) {
                WsLoginResponse wsLoginResponse =response.body();

                         if (wsLoginResponse.getError_code()==0) {
                        Toast.makeText(SocialLoginActivity.this, "Logged in successfully.", Toast.LENGTH_SHORT).show();
                        User_details userDetails=wsLoginResponse.getUser_details();

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
                        Intent intent = new Intent(SocialLoginActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("flg", "1");
                        intent.putExtras(bundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        finish();
                        startActivity(intent);
                    }
                    else if (wsLoginResponse.getError_code()==1) {
                        Toast.makeText(SocialLoginActivity.this, "Logged in successfully.", Toast.LENGTH_SHORT).show();

                        User_details userDetails=wsLoginResponse.getUser_details();

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
                        Bundle bundle = new Bundle();
                        bundle.putString("flg", "1");
                        intent.putExtras(bundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        finish();
                        startActivity(intent);
                    } else if (wsLoginResponse.getError_code()==2) {
                        Toast.makeText(SocialLoginActivity.this, "Please select groups.", Toast.LENGTH_SHORT).show();
                       /* Intent intent = new Intent(SocialLoginActivity.this, SelectGroupActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        finish();
                        startActivity(intent);
                        */

                    }


                showProgress(false);


            }

            @Override
            public void onFailure(Call<WsLoginResponse> call, Throwable t) {
                showProgress(false);
                Toast.makeText(SocialLoginActivity.this,"Network issue",Toast.LENGTH_SHORT).show();

            }
        };
        if(socialFlag==1){
            Call<WsLoginResponse> call=retrofit.getFacebookLogin(fbId,fbFirstName,fbLastLame,fbEmail,"",1);
            call.enqueue(callback);
        }if (socialFlag==2){
            Call<WsLoginResponse> call=retrofit.getGoogleLogin(googleId,googleFirstName,googleLastLame,googleEmail,"",1);
            call.enqueue(callback);
        }if(socialFlag==3){
            Call<WsLoginResponse> call=retrofit.getTwitterLogin(twitterId,twitterUsername,"",1);
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
        }else if(requestCode==RC_FB_SIGN_IN){
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }else if (requestCode==TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE){
            twitterLoginButton.onActivityResult(requestCode, resultCode, data);
        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                signInGoogle();
                break;
            case R.id.sign_up_email:
                break;
            case R.id.login_email:
                Intent intent=new Intent(SocialLoginActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}


