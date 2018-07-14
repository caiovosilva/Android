package com.ifba.caiovosilva.calculadoraimc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnCalcularOnClick(View view){
        EditText peso =(EditText) findViewById (R.id.txtPeso);
        double valorPeso = Double.parseDouble(peso.getText().toString());
        EditText altura = (EditText) findViewById(R.id.txtAltura);
        double valorAltura = Double.parseDouble(altura.getText().toString());
        showPopUp(calculoIMC(valorPeso,valorAltura));
    }

    private String calculoIMC(double peso, double altura){
        double result = peso/(altura*altura);
        if(result<19)
            return "Magro(a) demais!";
        if(result>32)
            return "Gordo(a) demais!";
        return "Você tá de boa.";
    }

    private void showPopUp(String msg){
        AlertDialog alerta;
         //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Resultado");
        //define a mensagem
        builder.setMessage(msg);
        //define o botao OK
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(MainActivity.this, "OK!", Toast.LENGTH_SHORT).show();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();

    }

   /* public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    */

}
