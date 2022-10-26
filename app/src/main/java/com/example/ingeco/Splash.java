package com.example.ingeco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    public static final long SPLASH_SCREEN_DELAY = 3000;

    Animation topAnim, buttomAnim;
    ImageView logo;

    TextView economia, nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        buttomAnim = AnimationUtils.loadAnimation(this, R.anim.button_animation);

        economia = findViewById(R.id.economia);

        nombre = findViewById(R.id.nombre);

        logo = findViewById(R.id.logo);

        economia.setAnimation((topAnim));

        nombre.setAnimation((topAnim));

        logo.setAnimation(buttomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, Menu.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_DELAY);
    }
}