package com.ATG.World.activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.models.WsLoginResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
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

    private LoginButton loginFacebookButton;
    private TwitterLoginButton twitterLoginButton;
    private GoogleSignInClient mGoogleSignInClient;
    private static int RC_FB_SIGN_IN;
    private static int RC_SIGN_IN=100;
    private Button loginEmailButton;
    private Button signupButton;
    private AtgService retrofit;

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
        loginEmailButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);

    }

    private void initTwitter() {
        twitterLoginButton = findViewById(R.id.login_button_twitter);
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
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
                if(wsLoginResponse.getUser_details()==null)
                    Toast.makeText(SocialLoginActivity.this, wsLoginResponse.getError_code()+ wsLoginResponse.getMsg(),Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SocialLoginActivity.this, wsLoginResponse.getError_code()+"",Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<WsLoginResponse> call, Throwable t) {

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


