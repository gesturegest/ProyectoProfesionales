package com.youtube.sorcjc.proyectoprofesionales.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.youtube.sorcjc.proyectoprofesionales.R;
import com.youtube.sorcjc.proyectoprofesionales.io.HomeSolutionApiAdapter;
import com.youtube.sorcjc.proyectoprofesionales.io.ZonasResponse;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Controls
    private Button btnIniciarSesion;
    private Button btnRegistrarme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The next code was used to get the hash key (for the facebook login)
        /*
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.youtube.sorcjc.proyectoprofesionales", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }*/

        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);
        btnRegistrarme = (Button) findViewById(R.id.btnRegistrarme);

        btnIniciarSesion.setOnClickListener(this);
        btnRegistrarme.setOnClickListener(this);

        // Read from Shared Preferences
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String defaultActivity = getResources().getString(R.string.first_activity_default);
        String firstActivity = sharedPref.getString(getString(R.string.first_activity), defaultActivity);

        Log.d("Test/SharedPreferences", "packageName => " + this.getPackageName());
        Log.d("Test/SharedPreferences", "fullClassName => " + this.getPackageName() + firstActivity);
        Intent intent = new Intent();
        intent.setClassName(this, this.getPackageName() + firstActivity);
        startActivity(intent);
        // finish(); // This prevent the user to use the "back" button
    }

    @Override
    public void onClick(View v) {
        // If we use shared preferences, the next is not necessary
        /*
        Intent intent = null;

        switch (v.getId()) {
            case R.id.btnIniciarSesion:
                intent = new Intent(this, LoginActivity.class);
                break;

            case R.id.btnRegistrarme:
                intent = new Intent(this, RegistroActivity.class);
                break;
        }

        if (intent != null)
            startActivity(intent);*/
    }

}