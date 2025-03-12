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

class spinnerCalculator : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_spinner_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val n1 = findViewById<AppCompatEditText>(R.id.etN1)
        val n2 = findViewById<AppCompatEditText>(R.id.etN2)
        val tvRr = findViewById<TextView>(R.id.tvRr)
        val btnCal = findViewById<AppCompatButton>(R.id.btnCal)
        val btnCle = findViewById<AppCompatButton>(R.id.btnClear)
        val btnExit = findViewById<AppCompatButton>(R.id.btnEx)
        val spinner = findViewById<Spinner>(R.id.spn1)

        // Cargar el ArrayAdapter para el Spinner
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.opciones_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        var selectedOption = ""

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                selectedOption = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        // Lógica cuando el botón Calcular es presionado
        btnCal.setOnClickListener {
            val number1 = n1.text.toString().toIntOrNull()
            val number2 = n2.text.toString().toIntOrNull()

            if (number1 != null && number2 != null) {
                when (selectedOption) {
                    "Suma" -> {
                        tvRr.text = "Suma: " + (number1 + number2).toString()
                    }
                    "Resta" -> {
                        tvRr.text = "Resta: " + (number1 - number2).toString()
                    }
                    "Multiplicacion" -> {
                        tvRr.text = "Mult: " + (number1 * number2).toString()
                    }
                    "Division" -> {
                        if (number2 != 0) {
                            tvRr.text = "Div: " + (number1 / number2).toString()
                        } else {
                            tvRr.text = "Error: División por 0"
                        }
                    }
                    else -> {
                        tvRr.text = "Selecciona una operación"
                    }
                }
            } else {
                tvRr.text = "Por favor ingresa ambos números"
            }
        }

        btnCle.setOnClickListener {
            n1.text?.clear()
            n2.text?.clear()
            tvRr.text = ""
        }

        btnExit.setOnClickListener {
            finish()
        }
    }
}
