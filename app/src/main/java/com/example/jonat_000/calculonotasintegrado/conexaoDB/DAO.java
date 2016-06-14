package com.example.jonat_000.calculonotasintegrado.conexaoDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jonat_000 on 08/06/2016.
 */
public abstract class DAO extends SQLiteOpenHelper {

    private static final String NAME = "BANCO_NOTAS";
    private static final int VERSION = 2;

    public DAO(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_PERIODO = "CREATE TABLE Periodo(codPeriodo INTEGER PRIMARY KEY AUTOINCREMENT, nomePeriodo TEXT)";

        String SQL_DISCIPLINA = "CREATE TABLE Disciplina(codDiscip INTEGER PRIMARY KEY AUTOINCREMENT, nomeDiscip TEXT, " +
                                "periodoDiscip INTEGER, piDiscip INTEGER, FOREIGN KEY(periodoDiscip) REFERENCES Periodo(codPeriodo))";

        String SQL_NOTAS = "CREATE TABLE Notas (codNota INTEGER PRIMARY KEY AUTOINCREMENT, av1 NUMERIC, av12 NUMERIC, av2 NUMERIC, av3 NUMERIC, " +
                           "disciplina INTEGER, FOREIGN KEY(disciplina) REFERENCES Disciplina(codDiscip))";

        db.execSQL(SQL_PERIODO);
        db.execSQL(SQL_DISCIPLINA);
        db.execSQL(SQL_NOTAS);

        String SQL_PERIODO1 = "INSERT INTO Periodo(codPeriodo, nomePeriodo) values(1,'1º PERIODO')";
        String SQL_PERIODO2 = "INSERT INTO Periodo(codPeriodo, nomePeriodo) values(2,'2º PERIODO')";
        String SQL_PERIODO3 = "INSERT INTO Periodo(codPeriodo, nomePeriodo) values(3,'3º PERIODO')";
        String SQL_PERIODO4 = "INSERT INTO Periodo(codPeriodo, nomePeriodo) values(4,'4º PERIODO')";
        String SQL_PERIODO5 = "INSERT INTO Periodo(codPeriodo, nomePeriodo) values(5,'5º PERIODO')";
        String SQL_PERIODO6 = "INSERT INTO Periodo(codPeriodo, nomePeriodo) values(6,'6º PERIODO')";

        db.execSQL(SQL_PERIODO1);
        db.execSQL(SQL_PERIODO2);
        db.execSQL(SQL_PERIODO3);
        db.execSQL(SQL_PERIODO4);
        db.execSQL(SQL_PERIODO5);
        db.execSQL(SQL_PERIODO6);

        String SQL_MATERIA1 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(1,'ALGORITMOS',1,0)";
        String SQL_MATERIA2 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(2,'FORMACAO GERAL',1,0)";
        String SQL_MATERIA3 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(3,'FUND E ARQUITETURA DE COMPUTADORES',1,0)";
        String SQL_MATERIA4 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(4,'MATEMATICA APLICADA A COMPUTADORES',1,0)";
        String SQL_MATERIA5 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(5,'PROJETO INTEGRADOR I',1,1)";

        db.execSQL(SQL_MATERIA1);
        db.execSQL(SQL_MATERIA2);
        db.execSQL(SQL_MATERIA3);
        db.execSQL(SQL_MATERIA4);
        db.execSQL(SQL_MATERIA5);

        String SQL_MATERIA6 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(6,'ENGENHARIA DE SOFTWARE',2,0)";
        String SQL_MATERIA7 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(7,'FUND DE REDES DE COMPUTADORES',2,0)";
        String SQL_MATERIA8 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(8,'LEITURA E INTERP. DE TEXTOS',2,0)";
        String SQL_MATERIA9 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(9,'MODELAGEM DE DADOS',2,0)";
        String SQL_MATERIA10 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(10,'PROGRAMACAO ESTRUTURADA',2,0)";
        String SQL_MATERIA11 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(11,'PROJETO INTEGRADOR II',2,1)";

        db.execSQL(SQL_MATERIA6);
        db.execSQL(SQL_MATERIA7);
        db.execSQL(SQL_MATERIA8);
        db.execSQL(SQL_MATERIA9);
        db.execSQL(SQL_MATERIA10);
        db.execSQL(SQL_MATERIA11);

        String SQL_MATERIA12 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(12,'ANALISE DE SISTEMAS O. O.',3,0)";
        String SQL_MATERIA13 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(13,'BANCO DE DADOS',3,0)";
        String SQL_MATERIA14 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(14,'PROGRAMACAO O. OBJETOS',3,0)";
        String SQL_MATERIA15 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(15,'PROJETO INTEGRADOR III',3,1)";

        db.execSQL(SQL_MATERIA12);
        db.execSQL(SQL_MATERIA13);
        db.execSQL(SQL_MATERIA14);
        db.execSQL(SQL_MATERIA15);

        String SQL_MATERIA16 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(16,'ETICA LEGISLACAO PROFISS.',4,0)";
        String SQL_MATERIA17 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(17,'FUND. SISTEMAS OPERACIONAIS',4,0)";
        String SQL_MATERIA18 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(18,'MOD. PROCESSO DE NEGOCIO',4,0)";
        String SQL_MATERIA19 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(19,'PROGRAMACAO WEB',4,0)";
        String SQL_MATERIA20 = "INSERT INTO Disciplina(codDiscip, nomeDiscip, periodoDiscip, piDiscip) values(20,'PROJETO INTEGRADOR IV',4,1)";

        db.execSQL(SQL_MATERIA16);
        db.execSQL(SQL_MATERIA17);
        db.execSQL(SQL_MATERIA18);
        db.execSQL(SQL_MATERIA19);
        db.execSQL(SQL_MATERIA20);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
