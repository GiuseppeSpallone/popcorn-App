package com.peppe.popapp.results;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.peppe.popapp.models.Programmazione;

public class ResultProgrammazione {

    @SerializedName("films")
    @Expose
    private ArrayList<Programmazione> programmazione;

    public ArrayList<Programmazione> getProgrammazione() {
        return programmazione;
    }

    public void setProgrammazione(ArrayList<Programmazione> films) {
        this.programmazione = programmazione;
    }

}

