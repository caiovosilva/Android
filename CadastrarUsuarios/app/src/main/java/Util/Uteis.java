package Util;

import android.app.AlertDialog;
import android.content.Context;

public class Uteis {
    public static void Alert(Context context, String mensagem){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Sistema");
        alertDialog.setMessage(mensagem);
        alertDialog.setPositiveButton("OK", null);
        alertDialog.show();
    }
}
