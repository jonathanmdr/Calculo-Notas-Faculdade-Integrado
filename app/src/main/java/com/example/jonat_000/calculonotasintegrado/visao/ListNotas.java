package com.example.jonat_000.calculonotasintegrado.visao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jonat_000.calculonotasintegrado.R;
import com.example.jonat_000.calculonotasintegrado.controle.DaoNotas;
import com.example.jonat_000.calculonotasintegrado.controle.DaoPeriodo;
import com.example.jonat_000.calculonotasintegrado.modelo.Notas;
import com.example.jonat_000.calculonotasintegrado.modelo.Periodo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_list_notas)
public class ListNotas extends AppCompatActivity {

    @ViewById
    ListView lvNotas;

    @ViewById
    Spinner spnPeriodoList;

    private DaoNotas daoNotas;
    private DaoPeriodo daoPeriodo;
    private Periodo periodo;
    private Notas notas;

    private ArrayAdapter<Periodo> aaPeriodo;
    private List<Periodo> periodoList;

    private int codPeriodo;

    @AfterViews
    public void init () {

        daoNotas = new DaoNotas(this);
        codPeriodo = 0;
        setAdapterNotas(codPeriodo);
        carregaSpinnerPeriodo();
    }

    public void setAdapterNotas (int codPeriodo) {
        ArrayAdapter<Notas> aaNotas =
                new ArrayAdapter<Notas>(this, android.R.layout.simple_list_item_1, daoNotas.list(codPeriodo));

        lvNotas.setAdapter(aaNotas);
    }

    @ItemClick(R.id.lvNotas)
    public void notaClick(Notas notas) {

        this.notas = notas;

        lvNotas.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                getMenuInflater().inflate(R.menu.list, contextMenu);
                contextMenu.setHeaderTitle("Lista de Notas");
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mnAlterar:

                Intent i = new Intent();
                i.putExtra("Notas", notas);
                setResult(MainActivity_.LIST_REQUEST, i);
                finish();

                break;
            case R.id.mnDel:

                mostraDialogDelete();

                break;
        }
        return super.onContextItemSelected(item);
    }

    public void mostraDialogDelete () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exclusão !");
        builder.setMessage("Deseja realmente deletar ?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {
                daoNotas.delete(notas);
                setAdapterNotas(codPeriodo);
                dialog.dismiss();
            }

        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private void carregaSpinnerPeriodo() {
        daoNotas = new DaoNotas(this);
        daoPeriodo = new DaoPeriodo(this);
        periodoList = daoPeriodo.list();
        aaPeriodo = new ArrayAdapter<Periodo>(this, android.R.layout.simple_dropdown_item_1line, periodoList);
        spnPeriodoList.setAdapter(aaPeriodo);

        int codPeriodo =  aaPeriodo.getItem(spnPeriodoList.getSelectedItemPosition()).getCodPeriodo();
        daoNotas.list(codPeriodo);

        spnPeriodoList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                atualizaListNotas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void atualizaListNotas() {
        int codPeriodo = aaPeriodo.getItem(spnPeriodoList.getSelectedItemPosition()).getCodPeriodo();
        setAdapterNotas(codPeriodo);
    }
}
