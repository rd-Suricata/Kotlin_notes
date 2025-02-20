package com.example.klotin_notes.simpleCalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.klotin_notes.R

class calculator2App : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator2_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val n1 = findViewById<AppCompatEditText>(R.id.etN1)
        val n2 = findViewById<AppCompatEditText>(R.id.etN2)
        val chA = findViewById<AppCompatCheckBox>(R.id.chA)
        val chR = findViewById<AppCompatCheckBox>(R.id.chR)
        val chM = findViewById<AppCompatCheckBox>(R.id.chM)
        val chD = findViewById<AppCompatCheckBox>(R.id.chD)
        val btnCal = findViewById<AppCompatButton>(R.id.btnCal)
        val btnCle = findViewById<AppCompatButton>(R.id.btnClear)
        val btnExit = findViewById<AppCompatButton>(R.id.btnEx)
        val tvRA = findViewById<TextView>(R.id.tvRA)
        val tvRR = findViewById<TextView>(R.id.tvRR)
        val tvRM = findViewById<TextView>(R.id.tvRM)
        val tvRD = findViewById<TextView>(R.id.tvRD)

        btnCal.setOnClickListener {
            val number1 = n1.text.toString().toIntOrNull()
            val number2 = n2.text.toString().toIntOrNull()
            if (number1 != null && number2 != null) {
                fun operate(number: Int) {
                    if (number == 1) {
                        tvRA.text = "Suma: " + (number1 + number2).toString()
                    } else if (number == 2) {
                        tvRR.text = "Resta: " + (number1 - number2).toString()
                    } else if (number == 3) {
                        tvRM.text = "Mult: " + (number1 * number2).toString()
                    } else if (number == 4) {
                        if (number2 != 0) {
                            tvRD.text = "Div: " + (number1 / number2).toString()
                        } else {
                            tvRD.text = "Error : 0"
                        }
                    }

                }
                if (chA.isChecked) operate(1)
                if (chR.isChecked) operate(2)
                if (chM.isChecked) operate(3)
                if (chD.isChecked) operate(4)

            }
        }
        btnCle.setOnClickListener {
            n1.text?.clear()
            n2.text?.clear()
            chA.isChecked = false
            chR.isChecked = false
            chM.isChecked = false
            chD.isChecked = false
            tvRA.text = ""
            tvRR.text = ""
            tvRM.text = ""
            tvRD.text = ""
        }
        btnExit.setOnClickListener {
            finish()
        }
    }
}