package com.peppe.popapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Biglietto {

    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("prezzo")
    @Expose
    private String prezzo;

    public Biglietto(String tipo, String prezzo) {
        this.tipo = tipo;
        this.prezzo = prezzo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

}
