package com.peppe.popapp.results;

import com.peppe.popapp.models.Sala;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ResultSale {

    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("message")
    @Expose
    private String message = null;
    @SerializedName("sale")
    @Expose
    private ArrayList<Sala> sale;

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

    public ArrayList<Sala> getSale() {
        return sale;
    }

    public void setSale(ArrayList<Sala> sale) {
        this.sale = sale;
    }
}
