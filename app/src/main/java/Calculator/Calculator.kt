package Calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.klotin_notes.R

class Calculator : AppCompatActivity() {
    private val displayList = ArrayList<String>()
    private var currentInput = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val display = findViewById<TextView>(R.id.display)
        val btnAc = findViewById<AppCompatButton>(R.id.btnAc)
        val btnDiv = findViewById<AppCompatButton>(R.id.btnD)
        val btnSum = findViewById<AppCompatButton>(R.id.btnMa)
        val btnMul = findViewById<AppCompatButton>(R.id.btnMu)
        val btnRes = findViewById<AppCompatButton>(R.id.btnRe)
        val btnPorc = findViewById<AppCompatButton>(R.id.btnP)
        val btnDot = findViewById<AppCompatButton>(R.id.btnDot)
        val btn1 = findViewById<AppCompatButton>(R.id.btn1)
        val btn2 = findViewById<AppCompatButton>(R.id.btn2)
        val btn3 = findViewById<AppCompatButton>(R.id.btn3)
        val btn4 = findViewById<AppCompatButton>(R.id.btn4)
        val btn5 = findViewById<AppCompatButton>(R.id.btn5)
        val btn6 = findViewById<AppCompatButton>(R.id.btn6)
        val btn7 = findViewById<AppCompatButton>(R.id.btn7)
        val btn8 = findViewById<AppCompatButton>(R.id.btn8)
        val btn9 = findViewById<AppCompatButton>(R.id.btn9)
        val btn0 = findViewById<AppCompatButton>(R.id.btn0)
        val btnEc = findViewById<AppCompatButton>(R.id.btnE)

        fun evaluateExpression(): Double {
            if (displayList.isEmpty()) return 0.0
            var result = displayList[0].toDouble()
            var operator = ""

            for (i in 1 until displayList.size step 2) {
                operator = displayList[i]
                val nextNumber = displayList[i + 1].toDouble()

                when (operator) {
                    "+" -> result += nextNumber
                    "-" -> result -= nextNumber
                    "*" -> result *= nextNumber
                    "/" -> result /= nextNumber
                    "%" -> result = (result * nextNumber) / 100
                }
            }
            return result
        }

        fun updateDisplay() {
            display.text = displayList.joinToString("") + currentInput
        }

        fun handleNumberInput(value: String) {
            if (value == "." && currentInput.contains(".")) return
            currentInput += value
            updateDisplay()
        }

        fun handleOperatorInput(operator: String) {
            if (currentInput.isNotEmpty()) {
                displayList.add(currentInput)
                currentInput = ""
            }
            if (displayList.isNotEmpty()) {
                if (displayList.last() in listOf("+", "-", "*", "/", "%")) {
                    displayList[displayList.size - 1] = operator
                } else {
                    displayList.add(operator)
                }
                updateDisplay()
            }
        }

        btn1.setOnClickListener { handleNumberInput("1") }
        btn2.setOnClickListener { handleNumberInput("2") }
        btn3.setOnClickListener { handleNumberInput("3") }
        btn4.setOnClickListener { handleNumberInput("4") }
        btn5.setOnClickListener { handleNumberInput("5") }
        btn6.setOnClickListener { handleNumberInput("6") }
        btn7.setOnClickListener { handleNumberInput("7") }
        btn8.setOnClickListener { handleNumberInput("8") }
        btn9.setOnClickListener { handleNumberInput("9") }
        btn0.setOnClickListener { handleNumberInput("0") }
        btnDot.setOnClickListener { handleNumberInput(".") }

        btnSum.setOnClickListener { handleOperatorInput("+") }
        btnRes.setOnClickListener { handleOperatorInput("-") }
        btnMul.setOnClickListener { handleOperatorInput("*") }
        btnDiv.setOnClickListener { handleOperatorInput("/") }
        btnPorc.setOnClickListener { handleOperatorInput("%") }

        btnAc.setOnClickListener {
            display.text = ""
            displayList.clear()
            currentInput = ""
        }

        btnEc.setOnClickListener {
            if (currentInput.isNotEmpty()) {
                displayList.add(currentInput)
                currentInput = ""
            }
            if (displayList.size >= 3) {
                val result = evaluateExpression()
                // Mostrar sin decimales si el resultado es entero
                display.text = if (result % 1.0 == 0.0) result.toInt().toString() else result.toString()
                displayList.clear()
            }
        }
    }
}
