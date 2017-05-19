package com.peppe.popapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Programmazione {

    @SerializedName("titolo")
    @Expose
    private String titolo;
    @SerializedName("orario1")
    @Expose
    private String orario1;
    @SerializedName("orario2")
    @Expose
    private String orario2;
    @SerializedName("orario3")
    @Expose
    private String orario3;
    @SerializedName("sala")
    @Expose
    private String sala;

    public Programmazione(String titolo, String orario1, String orario2, String orario3, String sala) {
        this.titolo = titolo;
        this.orario1 = orario1;
        this.orario2 = orario2;
        this.orario3 = orario3;
        this.sala = sala;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getOrario1() {
        return orario1;
    }

    public void setOrario1(String orario1) {
        this.orario1 = orario1;
    }

    public String getOrario2() {
        return orario2;
    }

    public void setOrario2(String orario2) {
        this.orario2 = orario2;
    }

    public String getOrario3() {
        return orario3;
    }

    public void setOrario3(String orario3) {
        this.orario3 = orario3;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
}
