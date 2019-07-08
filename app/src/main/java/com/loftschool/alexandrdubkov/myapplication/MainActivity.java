package com.loftschool.alexandrdubkov.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textStart = findViewById(R.id.textviewMain);
        textStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BudgetActivity.class));

            }
        });
     LoftApp loftApp = (LoftApp) getApplication();
     Api api = loftApp.getApi();
        String adroidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
       saveToken("$2y$10$tbbXWaoT1/jECNoMPXyguutpUxQyg06s0yIaUdiSeviEkssEu5VgK");
      Call<AuthResponse> authCall = api.auth(adroidId);
      authCall.enqueue(new Callback<AuthResponse>() {
          @Override
          public void onResponse(final Call<AuthResponse> call, final Response<AuthResponse> response) {
              saveToken(response.body().getAuthToken());
          }

          @Override
          public void onFailure(final Call<AuthResponse> call, final Throwable t) {

          }
      });
    }

    private void saveToken(final String token) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("auth_token", token);
        editor.apply();
    }
}
