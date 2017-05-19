package com.peppe.popapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sala {

    @SerializedName("numero")
    @Expose
    private String numero;
    @SerializedName("posti")
    @Expose
    private String posti;

    public Sala(String numero, String posti) {
        this.numero = numero;
        this.posti = posti;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPosti() {
        return posti;
    }

    public void setPosti(String posti) {
        this.posti = posti;
    }
}
