package com.peppe.popapp.api;

import com.peppe.popapp.models.Film;
import com.peppe.popapp.results.ResultBiglietti;
import com.peppe.popapp.results.ResultProgrammazione;
import com.peppe.popapp.results.ResultRegistrazione;
import com.peppe.popapp.results.ResultSale;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @FormUrlEncoded
    @POST("utente/registrazione")
    Call<ResultRegistrazione> registrazioneUtente(@Field("username") String username, @Field("email") String email, @Field("password") String password, @Field("token") String token);

    @GET("programmazione")
    Call<ResultProgrammazione> getProgrammazione();

    @GET("programmazione/{titolo_film}")
    Call<Film> getFilm(@Path("titolo_film") String titolo);

    @GET("info/sale")
    Call<ResultSale> getSale();

    @GET("info/prezzi")
    Call<ResultBiglietti> getPrezzi();


}
