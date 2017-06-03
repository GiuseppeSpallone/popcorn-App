package com.peppe.popapp.results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultRegistrazione {

    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("message")
    @Expose
    private String message = null;
    @SerializedName("registrazione")
    @Expose
    private Boolean registrazione;

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

    public Boolean getRegistrazione() {
        return registrazione;
    }

    public void setRegistrazione(Boolean registrazione) {
        this.registrazione = registrazione;
    }

}
