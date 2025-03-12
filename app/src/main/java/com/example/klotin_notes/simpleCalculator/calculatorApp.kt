package com.example.klotin_notes.simpleCalculator

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.klotin_notes.R
import com.example.klotin_notes.firstapp.ResultActivity

class calculatorApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val firstNumber = findViewById<AppCompatEditText>(R.id.etNumber1)
        val secondNumber = findViewById<AppCompatEditText>(R.id.etNumber2)
        val btnAdd = findViewById<AppCompatButton>(R.id.btnAdd)
        val btnClean = findViewById<AppCompatButton>(R.id.btnClean)
        val btnExit = findViewById<AppCompatButton>(R.id.btnExit)
        val tvResult = findViewById<TextView>(R.id.tvResult1)

        btnAdd.setOnClickListener {
            val n1 = firstNumber.text.toString().toIntOrNull()
            val n2 = secondNumber.text.toString().toIntOrNull()

            if (n1 != null && n2 != null) {
                var result = n1 + n2
                tvResult.text = "El resultado es: ${result}"
            }
        }

        // btnClean.setOnClickListener {
        //    firstNumber.text.clear()
        //    secondNumber.text.clear()
        // }
    }
}