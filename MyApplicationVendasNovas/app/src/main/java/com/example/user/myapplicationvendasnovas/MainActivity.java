package com.example.user.myapplicationvendasnovas;

import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements LocationListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Aqui chamamos um layout padrão (TIPO UM MENU)....
        setContentView(R.layout.main);

        //Instanciando um objeto da classe SQLiteDatabase
        //Após isso, criamos uma instância do banco com o nome vendas
        SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);

        //Instanciando um objeto da classe StringBuilder
        //Dentro do objeto da String adicionamos o comando para criar a tabela PRODUTOS e suas colunas
        //Após isso, executamos o comando SQL

        StringBuilder sqlProdutos = new StringBuilder();
        sqlProdutos.append("CREATE TABLE IF NOT EXISTS [produtos](");
        sqlProdutos.append("[_id] INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlProdutos.append("nome varchar(100), ");
        sqlProdutos.append("preco DOUBLE(10,2));");
        db.execSQL(sqlProdutos.toString());

        //Inserindo alguns produtos manualmente
        //Como os códigos são auto incrementável não é necessário especificar

        db.execSQL("INSERT INTO produtos(nome, preco) VALUES('Coca Cola', '2.50')");
        db.execSQL("INSERT INTO produtos(nome, preco) VALUES('Kuat', '3.50')");
        db.execSQL("INSERT INTO produtos(nome, preco) VALUES('Agua Mineral', '2.50')");
        db.execSQL("INSERT INTO produtos(nome, preco) VALUES('Redd Bull', '6.50')");

        //Instanciando um objeto da classe StringBuilder
        //Dentro do objeto da String adicionamos o comando para criar a tabela PRODUTOS e suas colunas
        //Após isso, executamos o comando SQL

        StringBuilder sqlVendas = new StringBuilder();
        sqlVendas.append("CREATE TABLE IF NOT EXISTS [vendas](");
        sqlVendas.append("[_id] INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlVendas.append("produto INTEGER, ");
        sqlVendas.append("preco DOUBLE(10,2), ");
        sqlVendas.append("la DOUBLE(10,9), ");
        sqlVendas.append("lo DOUBLE(10,9)); ");
        db.execSQL(sqlVendas.toString());

        //Fechando a conexão do banco

        db.close();
    }


    //Método Nova Venda - Invocando a Nova Activity

    public void NovaVenda_Click(View v){
       startActivity(new Intent(getBaseContext(), NovaVendaActivity.class));
    }

    public void ListarVendas_Click(View v){
       // startActivity(new Intent(getBaseContext(), ListarVendasActivity.class));
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
