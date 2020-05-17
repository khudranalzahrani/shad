package com.example.lap_shop.shahad_3asal.tools;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;


/**
 * Created by eng_m on 12/7/2016.
 */

public class FacebookLoginManager {
    String email, birthday;

    private static final String TAG = "FacebookManager";
    private Profile currentFacebookProfile;
    private CallbackManager callbackManager;
    private LoginButton facebookLoginButton;
    private FacebookResponseListener facebookResponseListener;
    private Context context;

    public FacebookLoginManager(Context context) {
        this.context = context;
    }

    public FacebookLoginManager init() {
        FacebookSdk.sdkInitialize(context);
        AppEventsLogger.activateApp(context);
        return this;
    }

    public void setup(View facebookButton, final FacebookResponseListener facebookResponseListener) {
        this.facebookResponseListener = facebookResponseListener;
        System.out.println(TAG);
        //this class for receive notification for profile changes
        ProfileTracker profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (oldProfile != null) currentFacebookProfile = oldProfile;
                if (currentProfile != null) currentFacebookProfile = currentProfile;
            }
        };
        callbackManager = CallbackManager.Factory.create();
        facebookLoginButton = (LoginButton) facebookButton;
        facebookLoginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Log.i("LoginActivity", response.toString());
                        // Get facebook data from login
                        Bundle bFacebookData = getFacebookData(object);
//                        Log.i(TAG, bFacebookData.getString("email"));
                        handleFacebookAccessToken(bFacebookData);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                Log.e(TAG, "facebook:onCancel");
                facebookResponseListener.onFailed();
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "facebook:onError", error);
                facebookResponseListener.onFailed();
            }
        });
    }

    private void handleFacebookAccessToken(final Bundle bundle) {
        if (currentFacebookProfile == null) {
            if (isLoggedIn()) logout();
            facebookLoginButton.performClick();
        } else {
            facebookResponseListener.onSuccess(bundle);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
        }
    }

    public void setCustomButton(View customButton) {
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "is logged in:" + isLoggedIn());
                if (isLoggedIn()) logout();
                facebookLoginButton.performClick();
            }
        });
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    public void logout() {
        LoginManager.getInstance().logOut();
    }

    public interface FacebookResponseListener {
        void onSuccess(Bundle bundle);

        void onFailed();
    }



    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));


            return bundle;
        } catch (JSONException e) {
            Log.d(TAG, "Error parsing JSON");
            return null;
        }
    }
}