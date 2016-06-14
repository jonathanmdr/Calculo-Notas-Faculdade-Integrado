package com.example.jonat_000.calculonotasintegrado.visao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jonat_000.calculonotasintegrado.R;
import com.example.jonat_000.calculonotasintegrado.controle.DaoDisciplina;
import com.example.jonat_000.calculonotasintegrado.controle.DaoNotas;
import com.example.jonat_000.calculonotasintegrado.controle.DaoPeriodo;
import com.example.jonat_000.calculonotasintegrado.modelo.Disciplina;
import com.example.jonat_000.calculonotasintegrado.modelo.Notas;
import com.example.jonat_000.calculonotasintegrado.modelo.Periodo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@OptionsMenu(R.menu.cadastro)
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    public static final int LIST_REQUEST = 600;

    @ViewById
    Spinner spnPeriodos;
    @ViewById
    Spinner spnDisciplinas;
    @ViewById
    EditText edtCodNota;
    @ViewById
    EditText edtAV1;
    @ViewById
    EditText edtAV12;
    @ViewById
    EditText edtAV2;
    @ViewById
    EditText edtAV3;
    @ViewById
    EditText edtAV1Peso;
    @ViewById
    EditText edtAV12Peso;
    @ViewById
    EditText edtAV2Peso;
    @ViewById
    EditText edtAV3Peso;
    @ViewById
    EditText edtMedia;
    @ViewById
    Button btnCalcular;
    @ViewById
    ListView lvNotas;

    private Periodo periodo;
    private DaoPeriodo daoPeriodo;
    private Disciplina disciplina;
    private DaoDisciplina daoDisciplina;
    private Notas notas;
    private DaoNotas daoNotas;
    private ArrayAdapter<Periodo> aaPeriodo;
    private List<Periodo> periodoList;
    private ArrayAdapter<Disciplina> aaDisciplina;
    private List<Disciplina> disciplinaList;

    @AfterViews
    protected void init(){

        periodo = new Periodo();
        daoPeriodo = new DaoPeriodo(this);

        carregaSpinnerPeriodo();

        spnDisciplinas.setEnabled(true);
        spnPeriodos.setEnabled(true);
    }

    private void carregaSpinnerPeriodo() {
        daoNotas = new DaoNotas(this);
        daoPeriodo = new DaoPeriodo(this);
        periodoList = daoPeriodo.list();
        aaPeriodo = new ArrayAdapter<Periodo>(this, android.R.layout.simple_dropdown_item_1line, periodoList);
        spnPeriodos.setAdapter(aaPeriodo);

        int codPeriodo =  aaPeriodo.getItem(spnPeriodos.getSelectedItemPosition()).getCodPeriodo();
        carregaSpinnerDisciplina(codPeriodo);

        spnPeriodos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                atualizaSpinerDiscilplina();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void atualizaSpinerDiscilplina() {
        int codPeriodo = aaPeriodo.getItem(spnPeriodos.getSelectedItemPosition()).getCodPeriodo();
        carregaSpinnerDisciplina(codPeriodo);
    }

    private void carregaSpinnerDisciplina(int codPeriodo) {
        daoDisciplina = new DaoDisciplina(MainActivity.this);
        disciplinaList = daoDisciplina.list(codPeriodo);
        aaDisciplina = new ArrayAdapter<Disciplina>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, disciplinaList);
        spnDisciplinas.setAdapter(aaDisciplina);
    }

    public void calcularMedia(){
        double media = 0;
        double av1 = 0;
        double av12 = 0;
        double av2 = 0;
        double av3 = 0;

        try {
            av1 = Double.parseDouble(edtAV1.getText().toString());
            av12 = Double.parseDouble(edtAV12.getText().toString());
            av2 = Double.parseDouble(edtAV2.getText().toString());
            av3 = Double.parseDouble(edtAV3.getText().toString());

            media = ((av1 + av12 + av2 + av3) / 4);

        } catch(NumberFormatException ex){
            Toast.makeText(this, "Para realizar o calculo todos os campos devem ser preenchidos !", Toast.LENGTH_SHORT).show();
        }

        if(media >= 7){
            Toast.makeText(this, "Parabéns, você está APROVADO =D !", Toast.LENGTH_SHORT).show();
        } else if (media >= 4 && media < 7){
            Toast.makeText(this, "Você está habilitado a fazer o EXAME =/ !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Você está REPROVADO =( !", Toast.LENGTH_SHORT).show();
        }

        edtMedia.setText(Double.toString(media));
    }

    @Click
    public void btnCalcular(){
        calcularMedia();
        setEdtAV1Peso();
        setEdtAV12Peso();
        setEdtAV2Peso();
        setEdtAV3Peso();
    }

    public Notas getNotas(){
        Notas notas = new Notas();
        notas.setAv1(Double.parseDouble(edtAV1.getText().toString()));
        notas.setAv12(Double.parseDouble(edtAV12.getText().toString()));
        notas.setAv2(Double.parseDouble(edtAV2.getText().toString()));
        notas.setAv3(Double.parseDouble(edtAV3.getText().toString()));
        notas.setMedia(Double.parseDouble(edtMedia.getText().toString()));
        notas.setDisciplina((Disciplina) spnDisciplinas.getSelectedItem());
        notas.setPeriodo((Periodo) spnPeriodos.getSelectedItem());

        try {
            notas.setCodNota(Integer.parseInt(edtCodNota.getText().toString()));
        } catch (NumberFormatException e) {}

        return notas;
    }

    public void setNotas(Notas n){
        edtCodNota.setText(String.valueOf(n.getCodNota()));
        edtAV1.setText(String.valueOf(n.getAv1()));
        edtAV12.setText(String.valueOf(n.getAv12()));
        edtAV2.setText(String.valueOf(n.getAv2()));
        edtAV3.setText(String.valueOf(n.getAv3()));
        edtMedia.setText(String.valueOf(n.getMedia()));
        setPeriodo(n.getPeriodo());
        setDisciplina(n.getDisciplina());
        setEdtAV1Peso();
        setEdtAV12Peso();
        setEdtAV2Peso();
        setEdtAV3Peso();
    }

    private void setPeriodo(Periodo p) {
        for (int i = 0; i < spnPeriodos.getCount(); i++) {
            Periodo vSpiner = (Periodo) spnPeriodos.getItemAtPosition(i);
            if (p.getCodPeriodo() == vSpiner.getCodPeriodo()) {
                spnPeriodos.setSelection(i);
                break;
            }
        }
    }

    private void setDisciplina(Disciplina d) {
        for (int i = 0; i < spnDisciplinas.getCount(); i++) {
            Disciplina vSpiner = (Disciplina) spnDisciplinas.getItemAtPosition(i);
            if (d.getCodDiscip() == vSpiner.getCodDiscip()) {
                spnDisciplinas.setSelection(i);
                break;
            }
        }
    }

    public void limpar () {
        edtCodNota.setText("");
        edtAV1.setText("");
        edtAV12.setText("");
        edtAV2.setText("");
        edtAV3.setText("");
        edtAV1Peso.setText("");
        edtAV12Peso.setText("");
        edtAV2Peso.setText("");
        edtAV3Peso.setText("");
        edtMedia.setText("");
        spnPeriodos.setSelection(0);
        spnDisciplinas.setSelection(0);
    }

    public void setEdtAV1Peso(){
        String nP = "";
        double notaPeso = Double.parseDouble(edtAV1.getText().toString());
        nP = String.valueOf((notaPeso * 25)/100);
        edtAV1Peso.setText(nP);
    }

    public void setEdtAV12Peso(){
        String nP = "";
        double notaPeso = Double.parseDouble(edtAV12.getText().toString());
        nP = String.valueOf((notaPeso * 25)/100);
        edtAV12Peso.setText(nP);
    }

    public void setEdtAV2Peso(){
        String nP = "";
        double notaPeso = Double.parseDouble(edtAV2.getText().toString());
        nP = String.valueOf((notaPeso * 20)/100);
        edtAV2Peso.setText(nP);
    }

    public void setEdtAV3Peso(){
        String nP = "";
        double notaPeso = Double.parseDouble(edtAV3.getText().toString());
        nP = String.valueOf((notaPeso * 30)/100);
        edtAV3Peso.setText(nP);
    }

    @OptionsItem(R.id.mnSalvar)
    public void salvar () {
        Notas n = getNotas();

        if (n.getCodNota() == 0) {
            daoNotas.add(n);
            limpar();
        } else {
            daoNotas.update(n);
            limpar();
        }
    }

    @OptionsItem(R.id.mnListar)
    public void listar () {
        startActivityForResult(new Intent(this, ListNotas_.class), LIST_REQUEST);
    }

    @OnActivityResult(LIST_REQUEST)
    public void listResult (int resultCode, Intent data, @OnActivityResult.Extra String value) {
        try {
            Notas n = (Notas) data.getExtras().getSerializable("Notas");
            setNotas(n);
        } catch (NullPointerException ex) {
            Log.e("MSG", "Não voltou Nota!");
        }
    }
}
