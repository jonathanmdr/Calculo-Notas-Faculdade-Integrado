package com.example.jonat_000.calculonotasintegrado.modelo;

import java.io.Serializable;

/**
 * Created by jonat_000 on 08/06/2016.
 */
public class Periodo implements Serializable {

    private int codPeriodo;
    private String nomePeriodo;

    public int getCodPeriodo() {
        return codPeriodo;
    }

    public void setCodPeriodo(int codPeriodo) {
        this.codPeriodo = codPeriodo;
    }

    public String getNomePeriodo() {
        return nomePeriodo;
    }

    public void setNomePeriodo(String nomePeriodo) {
        this.nomePeriodo = nomePeriodo;
    }

    @Override
    public String toString() {
        return nomePeriodo;
    }
}
