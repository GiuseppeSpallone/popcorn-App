package com.peppe.popapp.results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultAccesso {

    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("message")
    @Expose
    private String message = null;
    @SerializedName("accesso")
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
