package com.example.usosensores;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class sensorProximidad extends AppCompatActivity {
    Sensor miSensor;
    SensorManager administradorDeSensores;
    SensorEventListener disparadorEventoSensor;
    TextView lblValor;
    Button btnValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_proximidad);

        lblValor = (TextView) findViewById(R.id.lblValorProximidad);
        btnValor = (Button) findViewById(R.id.btnValor);

        //Inicializar mi sensor
        administradorDeSensores = (SensorManager)
                getSystemService(SENSOR_SERVICE);
        miSensor =
                administradorDeSensores.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(miSensor==null){
            Toast.makeText(this, "Su dispositivo no tiene el sensor de proximidad", Toast.LENGTH_LONG).show();
            finish();
        }else {
            Toast.makeText(this, "Sensor de Proximidad detectado",
                    Toast.LENGTH_LONG).show();
        }

        disparadorEventoSensor = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // Colocar el codigo de lo que queremos que haga nuestra app
                // cuando se acerque o se aleje el objeto del sensor
                lblValor.setText("Valor del sensor: "+
                        sensorEvent.values[0]);
                if(sensorEvent.values[0] < miSensor.getMaximumRange()){
                    //Condicion para determinar cuando se acerque
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                    btnValor.setText("Se ha acercado al sensor!");
                    lblValor.setBackgroundColor(Color.GRAY);
                    lblValor.setTextColor(Color.CYAN);
                }else{
                    //Que es lo que va hacer cuando se aleje
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                    btnValor.setText("Se ha eljado del sensor!");
                    lblValor.setBackgroundColor(Color.WHITE);
                    lblValor.setTextColor(Color.BLACK);
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        iniciarSensor();
    }
    public void iniciarSensor(){
        administradorDeSensores.registerListener(disparadorEventoSensor,miSensor,(2000*1000));
    }
    public void detenerSensor(){
        administradorDeSensores.unregisterListener(disparadorEventoSensor);
    }
    @Override
    protected void onPause() {
        detenerSensor();
        super.onPause();
    }
    @Override
    protected void onResume() {
        iniciarSensor();
        super.onResume();
    }
}