package com.peppe.popapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Film {

    @SerializedName("titolo")
    @Expose
    private String titolo;
    @SerializedName("nazione")
    @Expose
    private String nazione;
    @SerializedName("anno")
    @Expose
    private String anno;
    @SerializedName("genere")
    @Expose
    private String genere;
    @SerializedName("durata")
    @Expose
    private String durata;
    @SerializedName("regia")
    @Expose
    private String regia;
    @SerializedName("cast")
    @Expose
    private String cast;
    @SerializedName("produzione")
    @Expose
    private String produzione;
    @SerializedName("distribuzione")
    @Expose
    private String distribuzione;
    @SerializedName("data_uscita")
    @Expose
    private String dataUscita;
    @SerializedName("trama")
    @Expose
    private String trama;

    public Film(String titolo, String nazione, String anno, String genere, String durata, String regia, String cast, String produzione, String distribuzione, String dataUscita, String trama) {
        this.titolo = titolo;
        this.nazione = nazione;
        this.anno = anno;
        this.genere = genere;
        this.durata = durata;
        this.regia = regia;
        this.cast = cast;
        this.produzione = produzione;
        this.distribuzione = distribuzione;
        this.dataUscita = dataUscita;
        this.trama = trama;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public String getRegia() {
        return regia;
    }

    public void setRegia(String regia) {
        this.regia = regia;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getProduzione() {
        return produzione;
    }

    public void setProduzione(String produzione) {
        this.produzione = produzione;
    }

    public String getDistribuzione() {
        return distribuzione;
    }

    public void setDistribuzione(String distribuzione) {
        this.distribuzione = distribuzione;
    }

    public String getDataUscita() {
        return dataUscita;
    }

    public void setDataUscita(String dataUscita) {
        this.dataUscita = dataUscita;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

}
