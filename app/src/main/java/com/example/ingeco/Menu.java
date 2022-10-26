package com.example.ingeco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void frances(View view) {
        Intent intent1 = new Intent(Menu.this,MainActivity.class);
        startActivity(intent1);

    }
    public void aleman(View view) {
        Intent intent1 = new Intent(Menu.this,SistemaAleman.class);
        startActivity(intent1);
    }
    public void ingles(View view) {
        Intent intent1 = new Intent(Menu.this,SistemaIngles.class);
        startActivity(intent1);
    }
    public void flat(View view) {
        Intent intent1 = new Intent(Menu.this,SistemaFlat.class);
        startActivity(intent1);
    }
}