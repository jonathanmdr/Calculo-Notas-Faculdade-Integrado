package com.example.jonat_000.calculonotasintegrado.controle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.jonat_000.calculonotasintegrado.conexaoDB.DAO;
import com.example.jonat_000.calculonotasintegrado.modelo.Disciplina;
import com.example.jonat_000.calculonotasintegrado.modelo.Notas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonat_000 on 13/06/2016.
 */
public class DaoNotas extends DAO {

    private static String TB_NOME = "Notas";

    public DaoNotas(Context context) {
        super(context);
    }

    public void add (Notas notas) {
        ContentValues cv = new ContentValues();
        cv.put("av1", notas.getAv1());
        cv.put("av12", notas.getAv12());
        cv.put("av2", notas.getAv2());
        cv.put("av3", notas.getAv3());
        cv.put("disciplina", notas.getDisciplina().getCodDiscip());

        super.getWritableDatabase().insert(TB_NOME, null, cv);
    }

    public void update (Notas notas) {
        ContentValues cv = new ContentValues();
        cv.put("codNota", notas.getCodNota());
        cv.put("av1", notas.getAv1());
        cv.put("av12", notas.getAv12());
        cv.put("av2", notas.getAv2());
        cv.put("av3", notas.getAv3());
        cv.put("disciplina", notas.getDisciplina().getCodDiscip());

        super.getWritableDatabase().update(TB_NOME, cv, "codNota = ?",
                new String[]{String.valueOf(notas.getCodNota())});
    }

    public void delete (Notas notas) {
        super.getWritableDatabase().delete(TB_NOME, "codNota = ?",
                new String[] {String.valueOf(notas.getCodNota())});
    }

    public List<Notas> list (int codPediodo) {
        List<Notas> notas = new ArrayList<>();
        String SQL = "SELECT n.codNota, d.codDiscip, d.nomeDiscip, d.periodoDiscip, d.piDiscip, " +
                "n.av1, n.av12, n.av2, n.av3 " +
                "FROM Disciplina d " +
                "JOIN Notas n " +
                "ON(d.codDiscip = n.disciplina) " +
                "WHERE d.periodoDiscip = " + codPediodo;
        Cursor c = super.getReadableDatabase().rawQuery(SQL, null);

        while (c.moveToNext()) {

            Notas n = new Notas();
            n.setCodNota(c.getInt(c.getColumnIndex("codNota")));
            n.setAv1(c.getDouble(c.getColumnIndex("av1")));
            n.setAv12(c.getDouble(c.getColumnIndex("av12")));
            n.setAv2(c.getDouble(c.getColumnIndex("av2")));
            n.setAv3(c.getDouble(c.getColumnIndex("av3")));
            n.setMedia(c.getDouble(0));

            Disciplina d = new Disciplina();
            d.setCodDiscip(c.getInt(c.getColumnIndex("codDiscip")));
            d.setNomeDiscip(c.getString(c.getColumnIndex("nomeDiscip")));
            d.setPiDiscip(c.getInt(c.getColumnIndex("piDiscip")));
            d.setPeriodoDiscip(c.getInt(c.getColumnIndex("periodoDiscip")));
            n.setDisciplina(d);

            notas.add(n);
        }
        c.close();

        return notas;
    }
}
