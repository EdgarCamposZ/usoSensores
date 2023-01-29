package com.example.usosensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button actProximidad, actLuminosidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actProximidad = findViewById(R.id.btnProximidad);
        actLuminosidad = findViewById(R.id.btnLuminosidad);

        actProximidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventana = new Intent(MainActivity.this, sensorProximidad.class);
                startActivity(ventana);
            }
        });

        actLuminosidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventana = new Intent(MainActivity.this, sensorLuminosidad.class);
                startActivity(ventana);
            }
        });
    }
}