package com.peppe.popapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.iid.FirebaseInstanceId;
import com.peppe.popapp.api.APIService;
import com.peppe.popapp.api.APIUrl;
import com.peppe.popapp.models.Utente;
import com.peppe.popapp.results.ResultRegistrazione;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrazioneActivity extends AppCompatActivity {

    EditText editTextUsername, editTextEmail, editTextPassword, editTextConfermaPassword;
    Button buttonRegistrazione;

    private String username, email, password, confermaPassword, token;
    private String regularExpressionPassword, regularExpressionFieldNotEmpty;

    private AwesomeValidation regularExpressionValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfermaPassword = (EditText) findViewById(R.id.editTextConfermaPassword);
        buttonRegistrazione = (Button) findViewById(R.id.buttonRegistrazione);

        regularExpressionValidation = new AwesomeValidation(ValidationStyle.BASIC);

        regularExpressionPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        regularExpressionFieldNotEmpty = "^(?=\\s*\\S).*$";

        regularExpressionValidation.addValidation(this, R.id.editTextUsername, regularExpressionFieldNotEmpty, R.string.emptyError);

        regularExpressionValidation.addValidation(this, R.id.editTextEmail, Patterns.EMAIL_ADDRESS, R.string.emailError);
        regularExpressionValidation.addValidation(this, R.id.editTextPassword, regularExpressionPassword, R.string.passwordError);

        buttonRegistrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controlloPassword() == true && controlloRegularExpression() == true) {
                    loadRegistrazioneUtente();
                }
            }
        });
    }

    private boolean controlloRegularExpression() {
        if (regularExpressionValidation.validate()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean controlloPassword() {
        password = editTextPassword.getText().toString();
        confermaPassword = editTextConfermaPassword.getText().toString();

        if (!password.equals(confermaPassword)) {
            Toast.makeText(this, R.string.passwordsDiverseError, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void resetField() {
        editTextUsername.setText(null);
        editTextEmail.setText(null);
        editTextPassword.setText(null);
        editTextConfermaPassword.setText(null);

        username = null;
        email = null;
        password = null;
        confermaPassword = null;
        token = null;
    }

    private void loadRegistrazioneUtente() {
        username = editTextUsername.getText().toString();
        email = editTextEmail.getText().toString();
        token = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCM", token);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Utente utente = new Utente(username, email, password, token);

        Call<ResultRegistrazione> call = service.registrazioneUtente(
                utente.getUsername(),
                utente.getEmail(),
                utente.getPassword(),
                utente.getToken()
        );

        call.enqueue(new Callback<ResultRegistrazione>() {
            @Override
            public void onResponse(Call<ResultRegistrazione> call, Response<ResultRegistrazione> response) {
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                if (response.body().getResult() == true) {
                    resetField();

                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main);

                }
            }

            @Override
            public void onFailure(Call<ResultRegistrazione> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
