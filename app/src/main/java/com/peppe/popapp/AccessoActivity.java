package com.peppe.popapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.peppe.popapp.api.APIService;
import com.peppe.popapp.api.APIUrl;
import com.peppe.popapp.results.ResultAccesso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccessoActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button buttonAccesso;

    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesso);

        editTextUsername = (EditText) findViewById(R.id.editTextUsernameAccesso);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordAccesso);
        buttonAccesso = (Button) findViewById(R.id.buttonAccesso);

        buttonAccesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAccessoUtente();
            }
        });
    }

    private void loadAccessoUtente() {
        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<ResultAccesso> call = service.accessoUtente(username, password);

        call.enqueue(new Callback<ResultAccesso>() {
            @Override
            public void onResponse(Call<ResultAccesso> call, Response<ResultAccesso> response) {
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                if (response.body().getResult() == true) {
                    resetField();

                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main);

                }
            }

            @Override
            public void onFailure(Call<ResultAccesso> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void resetField() {
        editTextUsername.setText(null);
        editTextPassword.setText(null);

        username = null;
        password = null;
    }
}
