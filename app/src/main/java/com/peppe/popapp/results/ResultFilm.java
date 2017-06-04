package com.peppe.popapp.results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.peppe.popapp.models.Film;

public class ResultFilm {

    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("film")
    @Expose
    private Film film;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
