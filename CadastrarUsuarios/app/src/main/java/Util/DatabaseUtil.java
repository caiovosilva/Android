package Util;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseUtil extends SQLiteOpenHelper {
    private static final String nomeBaseDeDados = "SISTEMA.db";
    private  static final int versaoBaseDeDados = 1;
    private Context context;

    public DatabaseUtil(Context context){
        super(context, nomeBaseDeDados, null, versaoBaseDeDados);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //StringBuilder stringBuilderCreateTable = new StringBuilder();

//        stringBuilderCreateTable.append("CREATE TABLE tb_pessoa(");
//        stringBuilderCreateTable.append("id_pessoa INTEGER PRIMARY KEY AUTOINCREMENT, ");
//        stringBuilderCreateTable.append("ds_nome TEXT NOT NULL, ");
//        stringBuilderCreateTable.append("ds_endereco TEXT NOT NULL, ");
//        stringBuilderCreateTable.append("fl_sexo TEXT NOT NULL, ");
//        stringBuilderCreateTable.append("dt_nascimento TEXT NOT NULL, ");
//        stringBuilderCreateTable.append("fl_estadoCivil TEXT NOT NULL, ");
//        stringBuilderCreateTable.append("fl_ativo INT NOT NULL, ");
//        db.execSQL(stringBuilderCreateTable.toString());

        try {
            StringBuilder sqlProdutos = new StringBuilder();
            sqlProdutos.append("CREATE TABLE IF NOT EXISTS [tb_pessoa](");
            sqlProdutos.append("[id_pessoa] INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sqlProdutos.append("ds_nome varchar(100), ");
            sqlProdutos.append("ds_endereco varchar(100), ");
            sqlProdutos.append("fl_sexo varchar(1), ");
            sqlProdutos.append("dt_nascimento varchar(100), ");
            sqlProdutos.append("fl_estadoCivil varchar(100), ");
            sqlProdutos.append("fl_ativo int);");
            db.execSQL(sqlProdutos.toString());

//            String pessoas = "CREATE TABLE IF NOT EXISTS tb_pessoa (_id INTEGER PRIMARY KEY autoincrement, " + "nome VARCHAR(50), email VARCHAR(50))";
//
//            db.execSQL(clientes);
        }
        catch (SQLException EX){

            Toast toast = Toast.makeText(context, "Falha ao criar banco de dados: "+EX.getStackTrace(), Toast.LENGTH_LONG);
            toast.show();
        }
        finally{
            db.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS tb_pessoa");
        this.onCreate(db);
    }

    public SQLiteDatabase getConexaoDataBase() {
        return this.getWritableDatabase();
    }
}