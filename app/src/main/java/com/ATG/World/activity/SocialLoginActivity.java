package com.ATG.World.activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ATG.World.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.converter.gson.GsonConverterFactory;

public class SocialLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private CallbackManager callbackManager;
    private static final String GRAPH_PATH = "me/permissions";
    private static final String SUCCESS = "success";

    private LoginButton loginButton;
    private TwitterLoginButton twitterLoginButton;
    private Gson gson;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Facebook", "here10");
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        //TwitterCore.getInstance();

        setContentView(R.layout.activity_social_login);



        initTwitter();

        initGoogleLogin();


        initFacebookLogin();
    }

    private void initTwitter() {
        Log.e("Twitter","here1");

        twitterLoginButton = findViewById(R.id.login_button_twitter);
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.e("Twitter","here1");
                // Do something with result, which provides a TwitterSession for making API calls
                TwitterSession user = result.data;
                String twitterID = String.valueOf(user.getUserId());
                String userName = user.getUserName();
                Log.e("Twitter",twitterID+userName);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.e("Twitter","here1");
                // Do something on failure
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
        loginButton = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            String fbEmail = object.getString("email");
                            String fbId = object.getString("id");
                            String mName = object.getString("name");
                            String[] name = mName.split(" ");
                            String firstName = name[0];
                            String lastLame = name[1];
                            Log.e("Facebook", fbEmail + fbId + firstName + lastLame);
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
                // App code
            }
        });

    }

    private void signInGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }
    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            //updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google", "signInResult:failed code=" + e.getStatusCode());
        }
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            Log.e("Google",personName+personGivenName+personFamilyName+personEmail);
        }
        mGoogleSignInClient.signOut();
    }

    private void socialLogin() {


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }else {
            Log.e("Twitter","here1");
            twitterLoginButton.onActivityResult(requestCode, resultCode, data);
            //for facebook handle here
            //callbackManager.onActivityResult(requestCode, resultCode, data);
        }



    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                signInGoogle();
                break;
        }
    }
}


