package com.anioncode.droidcenter;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;


public class SplashScreen extends AppCompatActivity {

    private LoginButton loginButton;
    private ImageButton imageButton;
    private CircleImageView circleImageView;
    private TextView txtName,txtEmail;

    private CallbackManager callbackManager;

    public  static String EMAIL;
    public  static String PHOTO_URL;
    public  static String NAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.anioncode.facebookapi",   // Package name
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//        } catch (NoSuchAlgorithmException e) {
//        }
        loginButton=findViewById(R.id.login_button);
        txtName=findViewById(R.id.name);
        txtEmail=findViewById(R.id.email);
        circleImageView=findViewById(R.id.profile_pic);
        imageButton=findViewById(R.id.imageButtonnext);

        callbackManager= CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));
        checklogin();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentx = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intentx);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                finish();

            }
        });
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                txtName.setText(error.toString());
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker=new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken==null){
                txtEmail.setText("");
                txtName.setText("");
                circleImageView.setImageResource(R.drawable.bad);
                imageButton.setVisibility(View.INVISIBLE);

            }
            else {

                loaduserProfile(currentAccessToken);
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        imageButton.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                imageButton.startAnimation(animation);
            }

        }
    };

    private void loaduserProfile(AccessToken newAccessToken){
        GraphRequest request= GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String firstname=object.getString("first_name");
                    String last_name=object.getString("last_name");
                    String email=object.getString("email");
                    String id=object.getString("id");
                    String image_url="https://graph.facebook.com/"+id+"/picture?width=250&height=250";
                    System.out.println(image_url);
                    txtEmail.setText(email);
                    txtName.setText(firstname+" "+last_name);
                    RequestOptions requestOptions=new RequestOptions();
                    requestOptions.dontAnimate();

                    Glide.with(SplashScreen.this).load(image_url).into(circleImageView);

                    //STATYCZNE ZMIENNE
                    EMAIL=email;
                    PHOTO_URL=image_url;
                    NAME=firstname+" "+last_name;

                    Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            imageButton.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    imageButton.startAnimation(animation);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle parametrs = new Bundle();
        parametrs.putString("fields","first_name,last_name,email,id");
        request.setParameters(parametrs);
        request.executeAsync();

    }
    private  void checklogin(){
        if(AccessToken.getCurrentAccessToken()!=null){
            loaduserProfile(AccessToken.getCurrentAccessToken());
        }
    }
}








//
















//                try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.anioncode.facebookapi",   // Package name
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//        } catch (NoSuchAlgorithmException e) {
//        }