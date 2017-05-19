package com.peppe.popapp.results;

import com.peppe.popapp.models.Sala;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ResultSale {

    @SerializedName("sale")
    @Expose
    private ArrayList<Sala> sale;

    public void setSale(ArrayList<Sala> sale) {
        this.sale = sale;
    }

    public ArrayList<Sala> getSale() {
        return sale;
    }

}
