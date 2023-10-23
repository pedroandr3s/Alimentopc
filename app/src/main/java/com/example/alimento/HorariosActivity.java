package com.example.alimento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HorariosActivity extends AppCompatActivity {

    private int cantidadComidas; // Variable para almacenar la cantidad de comidas
    private List<String> alarmas = new ArrayList<>(); // Lista para almacenar las horas de las alarmas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        // Recuperar la cantidad de comidas seleccionadas (pasada como extra desde la actividad anterior)
        cantidadComidas = getIntent().getIntExtra("cantidadComidas", 2);

        // Configurar el TimePicker para permitir la selección de múltiples horas
        configureTimePicker();
    }

    private void configureTimePicker() {
        // Configurar el TimePicker
        final TimePicker timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true); // Configurar el formato de 24 horas

        // Configurar el botón para agregar la hora seleccionada a la lista de alarmas
        Button btnAgregarHora = findViewById(R.id.btnGuardar);
        btnAgregarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la hora seleccionada en el TimePicker y agregarla a la lista de alarmas
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                String horaSeleccionada = String.format("%02d:%02d", hour, minute);
                alarmas.add(horaSeleccionada);

                // Limpiar el TimePicker después de agregar la hora
                timePicker.setHour(0);
                timePicker.setMinute(0);
            }
        });

        // Configurar el botón para guardar todas las alarmas
        Button btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes guardar todas las alarmas almacenadas en la lista 'alarmas'

                // Ejemplo de cómo pasar información de vuelta a DatosPerro
                Intent intent = new Intent();
                intent.putExtra("alarmasGuardadas", true); // Indicar que las alarmas se han guardado
                setResult(RESULT_OK, intent);
                finish(); // Cierra esta actividad y vuelve a DatosPerro
            }
        });
    }
}
