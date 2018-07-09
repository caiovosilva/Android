package com.example.user.myapplicationvendasnovas;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class NovaVendaActivity extends Activity implements LocationListener {

	//Criando duas variáveis com visibilidade privada para representar a latitude e longitude

	private double la;
	private double lo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nova_venda);

		//Criando um objeto da classe Sppiner para manipular o componente
		Spinner spProdutos = (Spinner) findViewById(R.id.spProdutos);

		//Abrindo uma nova conexão com banco de dados
		SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);


		//Criando um objeto da classe Cursor para manipular as informações resultantes do select
		//https://developer.android.com/reference/android/database/Cursor

		Cursor cursor = db.rawQuery("SELECT * FROM produtos ORDER BY nome ASC", null);

		//Criando um vetor de String para representar os dados do banco
		//Criando um outro vetor de interiros para receber o id dos campo texto que reprenstam ID, NOME e PREÇO do produto
		String[] from = {"_id", "nome", "preco"};
		int[] to = {R.id.txvID, R.id.txvNome, R.id.txvPreco};


        //Criando um objeto da classe SimpleCursorAdapter e passando como paramento o spinner, cursos e os dois vetores.
		SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.spinner, cursor, from, to);

		spProdutos.setAdapter(ad);

		//fechando a conexão com banco
		db.close();
	}

	public void Salvar_Click(View view) {

		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, false);
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		Location location = locationManager.getLastKnownLocation(provider);
		la = location.getLatitude();
		lo = location.getLongitude();
		
		SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);
		
		Spinner spProdutos = (Spinner)findViewById(R.id.spProdutos);
		
		SQLiteCursor dados = (SQLiteCursor)spProdutos.getAdapter().getItem(spProdutos.getSelectedItemPosition());
		
		ContentValues ctv = new ContentValues();
		ctv.put("produto", dados.getInt(0));
		ctv.put("preco", dados.getDouble(2));
		ctv.put("la", la);
		ctv.put("lo", lo);
		
		if(db.insert("vendas", "_id", ctv) > 0){
			Toast.makeText(getBaseContext(), "Sucesso", Toast.LENGTH_LONG).show();
		}
	}

	public void onLocationChanged(Location location) {
		//la = location.getLatitude();
		//lo = location.getLongitude();
		
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}