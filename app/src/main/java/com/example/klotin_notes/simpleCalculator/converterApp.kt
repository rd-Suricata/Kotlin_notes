package com.example.klotin_notes.simpleCalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.klotin_notes.R

class converterApp : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_converter_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val n1 = findViewById<AppCompatEditText>(R.id.etNn)
        val tvRr = findViewById<TextView>(R.id.tvRc)
        val btnCal = findViewById<AppCompatButton>(R.id.btnCal)
        val btnCle = findViewById<AppCompatButton>(R.id.btnClear)
        val btnExit = findViewById<AppCompatButton>(R.id.btnEx)
        val spinner = findViewById<Spinner>(R.id.spn3)
        val spinner2 = findViewById<Spinner>(R.id.spn4)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.options_cal,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner2.adapter = adapter

        var selectedOption = ""
        var selectedOption2 = ""

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                selectedOption = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Aquí puedes dejar vacío o mostrar un mensaje de error
                selectedOption = ""
            }
        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                selectedOption2 = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Aquí puedes dejar vacío o mostrar un mensaje de error
                selectedOption2 = ""
            }
        }

        val conversionesAMetros = mapOf(
            "km" to 1000.0,
            "hm" to 100.0,
            "dam" to 10.0,
            "m" to 1.0,
            "dm" to 0.1,
            "cm" to 0.01,
            "mm" to 0.001,
            "pulgada" to 0.0254,
            "pie" to 0.3048,
            "yarda" to 0.9144
        )
        val conversionesDeMetros = conversionesAMetros.mapValues { 1 / it.value }

        fun convertirUnidades(quantity: Int?, unidadOrigen: String, unidadDestino: String): Double? {
            if (!conversionesAMetros.containsKey(unidadOrigen)) {
                throw IllegalArgumentException("Unidad origen desconocida: $unidadOrigen")
            }
            if (!conversionesAMetros.containsKey(unidadDestino)) {
                throw IllegalArgumentException("Unidad destino desconocida: $unidadDestino")
            }

            val cantidadEnMetros = quantity?.times(conversionesAMetros[unidadOrigen]!!)

            return cantidadEnMetros?.times(conversionesDeMetros[unidadDestino]!!)
        }

        btnCal.setOnClickListener {
            // Verificar que ambos Spinners tengan una opción válida seleccionada
            if (selectedOption.isEmpty() || selectedOption2.isEmpty()) {
                tvRr.text = "Por favor selecciona una unidad de origen y una unidad de destino."
                return@setOnClickListener
            }

            // Verificar que el número ingresado sea válido
            val number1 = n1.text.toString().toIntOrNull()
            if (number1 == null) {
                tvRr.text = "Por favor ingresa un valor válido."
                return@setOnClickListener
            }

            try {
                // Realizar la conversión
                val result = convertirUnidades(number1, selectedOption, selectedOption2)
                tvRr.text = result?.toString() ?: "Error en la conversión."
            } catch (e: Exception) {
                tvRr.text = "Error: ${e.message}"
            }
        }
    }
}