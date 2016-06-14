package com.example.jonat_000.calculonotasintegrado.controle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.jonat_000.calculonotasintegrado.conexaoDB.DAO;
import com.example.jonat_000.calculonotasintegrado.modelo.Periodo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonat_000 on 09/06/2016.
 */
public class DaoPeriodo extends DAO {

    private static String TB_NOME = "Periodo";

    public DaoPeriodo(Context context) {
        super(context);
    }

    public List<Periodo> list () {
        List<Periodo> periodos = new ArrayList<>();
        Cursor c = super.getReadableDatabase().rawQuery("SELECT * FROM Periodo", null);

        while (c.moveToNext()) {
            Periodo p = new Periodo();
            p.setCodPeriodo(c.getInt(c.getColumnIndex("codPeriodo")));
            p.setNomePeriodo(c.getString(c.getColumnIndex("nomePeriodo")));

            periodos.add(p);
        }
        c.close();

        return periodos;
    }
}
