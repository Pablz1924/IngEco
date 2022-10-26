package com.example.ingeco;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;

public class SistemaAleman extends AppCompatActivity {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    TableLayout tableLayout;
    EditText etxtPrestamo, etxtTiempo, etxtTEM;
    Double P, n, TEM, A;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sistema_aleman);

        tableLayout = (TableLayout) findViewById(R.id.table);
        etxtPrestamo = (EditText) findViewById(R.id.etxtPrestamo);
        etxtTiempo = (EditText) findViewById(R.id.etxtTiempo);
        etxtTEM = (EditText) findViewById(R.id.etxtTEM);
    }

    public void calcular(View view){
        tableLayout.removeAllViews();

        if(TextUtils.isEmpty(etxtPrestamo.getText().toString())) {
            etxtPrestamo.setError("Introduzca un numero");
            return;
        }
        if(TextUtils.isEmpty(etxtTiempo.getText().toString())) {
            etxtTiempo.setError("Introduzca un numero");
            return;
        }
        if(TextUtils.isEmpty(etxtTEM.getText().toString())) {
            etxtTEM.setError("Introduzca un numero");
            return;
        }

        P = Double.valueOf(etxtPrestamo.getText().toString());
        n = Double.valueOf(etxtTiempo.getText().toString());
        TEM = Double.valueOf(etxtTEM.getText().toString());

        if (P < 1000 || P > 50000){
            etxtPrestamo.setError("Valores fuera del intervalo");
            return;
        }
        if (n < 6 || n > 24){
            etxtTiempo.setError("Valores fuera del intervalo");
            return;
        }
        if (TEM < 0.01 || TEM > 0.04){
            etxtTEM.setError("Valores fuera del intervalo");
            return;
        }

        A = P*(TEM*Math.pow((1+TEM),n))/(Math.pow((1+TEM),n)-1);


        TableRow Row0 = new TableRow(this);
        TextView pago = newTextView("  Periodos de Pago  ", "#FF000000");
        Row0.addView(pago);
        TextView Mrenta = newTextView("  Mensualidad Renta  ", "#FF000000");
        Row0.addView(Mrenta);
        TextView interes1 = newTextView("  Interes  ", "#FF000000");
        Row0.addView(interes1);
        TextView Amort = newTextView("  Amortizacion  ", "#FF000000");
        Row0.addView(Amort);
        TextView sald = newTextView("  Saldo  ", "#FF000000");
        Row0.addView(sald);
        tableLayout.addView(Row0);

        TableRow Row1 = new TableRow(this);
        TextView r1_col1 = newTextView("0", "#FF000000");
        Row1.addView(r1_col1);
        TextView r1_col2 = newTextView("", "#FF000000");
        Row1.addView(r1_col2);
        TextView r1_col3 = newTextView("", "#FF000000");
        Row1.addView(r1_col3);
        TextView r1_col4 = newTextView("", "#FF000000");
        Row1.addView(r1_col4);
        TextView r1_col5 = newTextView(Double.toString(P), "#FF000000");
        Row1.addView(r1_col5);
        tableLayout.addView(Row1);

        Double MensualidadRenta = 0.0;
        Double Interes = 0.0;
        Double Amortizacion = P/n;
        Double Saldo = 0.0;
        TextView new_col1, MensualidadRenta2, Interes3, new_col4, Saldo5;

        for (int i = 0; i < n; i++){
            TableRow newRow = new TableRow(this);
            new_col1 = newTextView(Integer.toString(i+1),"#FF000000");
            new_col4 = newTextView(df.format(Amortizacion),"#FF000000");

            if (i == 0) {
                Interes = TEM * P;
                Saldo = P - Amortizacion;
                MensualidadRenta = Interes + Amortizacion;

                MensualidadRenta2 = newTextView(df.format(MensualidadRenta),"#FF000000");
                Interes3 = newTextView(df.format(Interes),"#FF000000");
                Saldo5 = newTextView(df.format(Saldo),"#FF000000");
            }
            else {
                Interes = Saldo * TEM;
                Saldo = Saldo - Amortizacion;
                MensualidadRenta = Interes + Amortizacion;

                MensualidadRenta2 = newTextView(df.format(MensualidadRenta),"#FF000000");
                Interes3 = newTextView(df.format(Interes),"#FF000000");
                Saldo5 = newTextView(df.format(Saldo),"#FF000000");
            }
            newRow.addView(new_col1);
            newRow.addView(MensualidadRenta2);
            newRow.addView(Interes3);
            newRow.addView(new_col4);
            newRow.addView(Saldo5);
            tableLayout.addView(newRow);
        }
    }


    public TextView newTextView(String text, String color){
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextColor(Color.parseColor(color));
        tv.setTextSize(20);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }
}
