package com.example.caiov.cadastrarusiarios;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewOpcoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.criarComponentes();
        this.carregaOpcoesLista();
    }

    private void criarComponentes(){
        listViewOpcoes = this.findViewById(R.id.listViewOpcoes);
    }

    private void carregaOpcoesLista(){
        String[] itens = new String[2];
        itens[0] = "Cadastrar";
        itens[1] = "Consultar";

        ArrayAdapter<String> arrayItens = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, itens);
        listViewOpcoes.setAdapter(arrayItens);

        listViewOpcoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicaoDaLinha, long id) {
                if(posicaoDaLinha == 0) {
                    Intent createPessoaActivity = new Intent(MainActivity.this, create_pessoa.class);
                    startActivity(createPessoaActivity);
                }
                else if(posicaoDaLinha == 1) {
                    Intent createPessoaActivity = new Intent(MainActivity.this, ListarPessoas.class);
                    startActivity(createPessoaActivity);
                }
            }
        });
    }
}
