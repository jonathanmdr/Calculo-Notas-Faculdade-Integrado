package com.example.jonat_000.calculonotasintegrado.controle;

import android.content.Context;
import android.database.Cursor;

import com.example.jonat_000.calculonotasintegrado.conexaoDB.DAO;
import com.example.jonat_000.calculonotasintegrado.modelo.Disciplina;
import com.example.jonat_000.calculonotasintegrado.modelo.Periodo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonat_000 on 10/06/2016.
 */
public class DaoDisciplina extends DAO {

    private static String TB_NOME = "Disciplina";

    public DaoDisciplina(Context context) {
        super(context);
    }

    public List<Disciplina> list(int codPeriodo) {
        List<Disciplina> disciplinas = new ArrayList<>();
        Cursor c = super.getReadableDatabase().rawQuery("SELECT * FROM Disciplina WHERE periodoDiscip = " + codPeriodo, null);

        while (c.moveToNext()) {
            Disciplina d = new Disciplina();
            d.setCodDiscip(c.getInt(c.getColumnIndex("codDiscip")));
            d.setNomeDiscip(c.getString(c.getColumnIndex("nomeDiscip")));
            d.setPeriodoDiscip(c.getInt(c.getColumnIndex("periodoDiscip")));
            d.setPiDiscip(c.getInt(c.getColumnIndex("piDiscip")));

            disciplinas.add(d);
        }
        c.close();

        return disciplinas;
    }

}
