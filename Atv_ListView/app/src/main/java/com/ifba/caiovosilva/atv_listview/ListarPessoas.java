package com.ifba.caiovosilva.atv_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import model.PessoaModel;
import repository.PessoaRepository;

public class ListarPessoas extends AppCompatActivity {

    private ListView listViewPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pessoas);
        this.criarComponentes();
        this.carregaOpcoesLista();
    }

    private void criarComponentes(){
        listViewPessoas = this.findViewById(R.id.listViewOpcoes);
    }

    private void carregaOpcoesLista(){

        PessoaRepository pessoaRepository = new PessoaRepository(this);
        List<PessoaModel> pessoas = pessoaRepository.listarPessoas();
        String[] itens = new String[pessoas.size()];
        PessoaModel[] arrayPessoas = pessoas.toArray(new PessoaModel[pessoas.size()]);

        for(int i=0;i<pessoas.size();i++){
            itens[i]=arrayPessoas[i].toString();
        }
        ArrayAdapter<String> arrayItens = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, itens);
        listViewPessoas.setAdapter(arrayItens);

        /*listViewPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicaoDaLinha, long id) {
                if(posicaoDaLinha == 0) {
                    Intent createPessoaActivity = new Intent(MainActivity.this, create_pessoa.class);
                    startActivity(createPessoaActivity);
                }
            }
        });*/
    }
}
