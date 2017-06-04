package com.peppe.popapp.results;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.peppe.popapp.models.Programmazione;

public class ResultProgrammazione {

    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("message")
    @Expose
    private String message = null;
    @SerializedName("films")
    @Expose
    private ArrayList<Programmazione> programmazione;

    public ArrayList<Programmazione> getProgrammazione() {
        return programmazione;
    }

    public void setProgrammazione(ArrayList<Programmazione> films) {
        this.programmazione = programmazione;
    }

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
}

