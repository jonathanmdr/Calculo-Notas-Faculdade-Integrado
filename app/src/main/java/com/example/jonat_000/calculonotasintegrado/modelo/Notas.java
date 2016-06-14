package com.example.jonat_000.calculonotasintegrado.modelo;

import java.io.Serializable;

/**
 * Created by jonat_000 on 08/06/2016.
 */
public class Notas implements Serializable {

    private int codNota;
    private double av1;
    private double av12;
    private double av2;
    private double av3;
    private double media;
    private Disciplina disciplina;
    private Periodo periodo;

    public int getCodNota() {
        return codNota;
    }

    public void setCodNota(int codNota) {
        this.codNota = codNota;
    }

    public double getAv1() {
        return av1;
    }

    public void setAv1(double av1) {
        this.av1 = av1;
    }

    public double getAv12() {
        return av12;
    }

    public void setAv12(double av12) {
        this.av12 = av12;
    }

    public double getAv2() {
        return av2;
    }

    public void setAv2(double av2) {
        this.av2 = av2;
    }

    public double getAv3() {
        return av3;
    }

    public void setAv3(double av3) {
        this.av3 = av3;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public void media(){
        double media = ((av1 + av12 + av2 + av3)/4);
        setMedia(media);
    }

    @Override
    public String toString() {
        media();
        return disciplina.getNomeDiscip() + "\n" +
               "AV 1:  " + av1 + "                                  AV 1-2º:  " + av12 +"\n" +
               "AV 2:  " + av2 + "                                  AV 3.....:  " + av3 + "\n" +
               "\n" +
               "MÉDIA: " + media;
    }
}
