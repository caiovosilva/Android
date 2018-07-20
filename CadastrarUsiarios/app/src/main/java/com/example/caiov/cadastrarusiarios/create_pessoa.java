package com.example.caiov.cadastrarusiarios;

import android.app.DatePickerDialog;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import Util.DatabaseUtil;
import Util.Uteis;
import model.EstadoCivilModel;
import model.PessoaModel;
import repository.PessoaRepository;

public class create_pessoa extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextEndereco;
    private EditText editTextDataNascimento;
    private RadioButton radioButtonMasculino;
    private RadioButton radioButtonFeminino;
    private RadioGroup radioGroupSexo;
    private Spinner spinnerEstadoCivil;
    private CheckBox checkBoxRegistroAtivo;
    private Button buttonCadastrar;

    private DatePickerDialog datePickerDialogDataNascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pessoa);

        this.criarComponentes();
        this.carregarEstadosCivis();
        // this.criarEventos();
        //this.localizacao();
    }

    public void btnCadastar_OnClick(View view){

        if(editTextNome.getText().toString().trim().equals("")){
            Uteis.Alert(this,this.getString(R.string.nome_obrigatorio));
            editTextNome.requestFocus();
        }
        else if(editTextEndereco.getText().toString().trim().equals("")){
            Uteis.Alert(this,this.getString(R.string.nome_obrigatorio));
            editTextEndereco.requestFocus();
        }
        else if(!radioButtonMasculino.isChecked() && !radioButtonFeminino.isChecked()){
            Uteis.Alert(this,this.getString(R.string.sexo_obrigatorio));
            radioGroupSexo.requestFocus();
        }
        else if(editTextDataNascimento.getText().toString().trim().equals("")){
            Uteis.Alert(this, this.getString(R.string.data_nascimento_obrigatorio));
            editTextDataNascimento.requestFocus();
        }
        else{
            PessoaModel pessoa = new PessoaModel();
            pessoa.setNome(editTextNome.getText().toString().trim());
            pessoa.setEndereco(editTextEndereco.getText().toString().trim());
            pessoa.setDataNascimento(editTextDataNascimento.getText().toString().trim());
            if(radioButtonMasculino.isChecked())
                pessoa.setSexo("M");
            else
                pessoa.setSexo("F");
            EstadoCivilModel estadoCivilModel = (EstadoCivilModel)spinnerEstadoCivil.getSelectedItem();
            pessoa.setEstadoCivil(estadoCivilModel.getCodigo());
            if(checkBoxRegistroAtivo.isChecked())
                pessoa.setRegistroAtivo((byte)1);
            else
                pessoa.setRegistroAtivo((byte)0);

            new PessoaRepository(this).Salvar(pessoa);
            Uteis.Alert(this,this.getString(R.string.registro_salvo_sucesso));
            limparCampos();
        }
        //String nome = ((EditText)findViewById(R.id.txtNome)).getText().toString();
        //String endereco = ((EditText)findViewById(R.id.txtEndereco)).getText().toString();
        //String sexo = ((EditText)findViewById(R.id.txtSexo)).getText().toString();
        //String dataNascimento = ((EditText)findViewById(R.id.txtDataNascimento)).getText().toString();
        //String estadoCivil = ((EditText)findViewById(R.id.txtEstadoCivil)).getText().toString();
        //byte registroAtivo = 0;
        // if(((EditText)findViewById(R.id.txtRegistroAtivo)).getText().toString()=="Sim");
        //    registroAtivo = 1;

        // PessoaModel pessoa = new PessoaModel(nome, endereco, sexo, dataNascimento, estadoCivil, registroAtivo);

        //PessoaRepository pessoaRepository = new PessoaRepository(this);
        // pessoaRepository.Salvar(pessoa);
    }

    private void criarComponentes(){
        editTextNome = ((EditText)findViewById(R.id.txtNome));
        editTextEndereco = ((EditText)findViewById(R.id.txtEndereco));
        radioButtonMasculino =  ((RadioButton)findViewById(R.id.radioButtonMasculino));
        radioButtonFeminino = ((RadioButton)findViewById(R.id.radioButtonFeminino));
        radioGroupSexo = ((RadioGroup)findViewById(R.id.radioGroupSexo));
        editTextDataNascimento = ((EditText)findViewById(R.id.txtDataNascimento));
        spinnerEstadoCivil = ((Spinner)findViewById(R.id.spinnerEstadoCivil));
        checkBoxRegistroAtivo =((CheckBox)findViewById(R.id.checkBoxRegistroAtivo));
    }

    private void carregarEstadosCivis(){
        ArrayAdapter<EstadoCivilModel> arrayAdapter;
        List<EstadoCivilModel> itens = new ArrayList<EstadoCivilModel>();

        itens.add(new EstadoCivilModel("S","Solteiro(a)"));
        itens.add(new EstadoCivilModel("C","Casado(a)"));
        itens.add(new EstadoCivilModel("V","Viuvo(a)"));
        itens.add(new EstadoCivilModel("D","Divorciado(a)"));

        arrayAdapter = new ArrayAdapter<EstadoCivilModel>(this, android.R.layout.simple_spinner_item,itens);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstadoCivil.setAdapter(arrayAdapter);
    }
    private void localizacao(){
        Locale locale = new Locale("pt","BR");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getApplicationContext().getResources().updateConfiguration(config, null);
    }

    private void limparCampos(){
        editTextDataNascimento.setText(null);
        editTextNome.setText(null);
        editTextEndereco.setText(null);
        radioGroupSexo.clearCheck();
        checkBoxRegistroAtivo.setChecked(false);
    }

//    private void criarEventos(){
//        final Calendar calendarDataAtual = Calendar.getInstance();
//        int anoAtual = calendarDataAtual.get(Calendar.YEAR);
//        int mesAtual = calendarDataAtual.get(Calendar.MONTH);
//        int diaAtual = calendarDataAtual.get(Calendar.DAY_OF_MONTH);
//
//        datePickerDialogDataNascimento = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                @Override
//                        public void onDateSet(DatePicker view, int anoSelecionado, int mesSelecionado, int diaSelecionado){
//
//                }
//            }
//        })
//    }
}