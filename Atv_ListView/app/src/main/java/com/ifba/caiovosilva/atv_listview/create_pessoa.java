package com.ifba.caiovosilva.atv_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import Util.DatabaseUtil;
import model.PessoaModel;
import repository.PessoaRepository;

public class create_pessoa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pessoa);
    }

    public void btnCadastar_OnClick(View view){
        String nome = ((EditText)findViewById(R.id.txtNome)).getText().toString();
        String endereco = ((EditText)findViewById(R.id.txtEndereco)).getText().toString();
        String sexo = ((EditText)findViewById(R.id.txtSexo)).getText().toString();
        String dataNascimento = ((EditText)findViewById(R.id.txtDataNascimento)).getText().toString();
        String estadoCivil = ((EditText)findViewById(R.id.txtEstadoCivil)).getText().toString();
        byte registroAtivo = 0;
        if(((EditText)findViewById(R.id.txtRegistroAtivo)).getText().toString()=="Sim");
            registroAtivo = 1;

        PessoaModel pessoa = new PessoaModel(nome, endereco, sexo, dataNascimento, estadoCivil, registroAtivo);

        PessoaRepository pessoaRepository = new PessoaRepository(this);
        pessoaRepository.Salvar(pessoa);
    }
}
