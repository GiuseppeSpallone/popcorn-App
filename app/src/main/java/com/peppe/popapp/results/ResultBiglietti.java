package com.peppe.popapp.results;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.peppe.popapp.models.Biglietto;

public class ResultBiglietti {

    @SerializedName("prezzi")
    @Expose
    private ArrayList<Biglietto> biglietti;

    public ArrayList<Biglietto> getPrezzi() {
        return biglietti;
    }

    public void setPrezzi(ArrayList<Biglietto> prezzi) {
        this.biglietti = prezzi;
    }

}
