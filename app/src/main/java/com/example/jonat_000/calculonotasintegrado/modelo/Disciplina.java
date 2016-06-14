package com.example.jonat_000.calculonotasintegrado.modelo;

import java.io.Serializable;

/**
 * Created by jonat_000 on 08/06/2016.
 */
public class Disciplina implements Serializable {

    private int codDiscip;
    private String nomeDiscip;
    private int piDiscip;
    private int periodoDiscip;


    public int getCodDiscip() {
        return codDiscip;
    }

    public void setCodDiscip(int codDiscip) {
        this.codDiscip = codDiscip;
    }

    public int getPiDiscip() {
        return piDiscip;
    }

    public void setPiDiscip(int piDiscip) {
        this.piDiscip = piDiscip;
    }

    public String getNomeDiscip() {
        return nomeDiscip;
    }

    public void setNomeDiscip(String nomeDiscip) {
        this.nomeDiscip = nomeDiscip;
    }

    public int getPeriodoDiscip() {
        return periodoDiscip;
    }

    public void setPeriodoDiscip(int periodoDiscip) {
        this.periodoDiscip = periodoDiscip;
    }

    @Override
    public String toString() {
        return nomeDiscip;
    }
}
