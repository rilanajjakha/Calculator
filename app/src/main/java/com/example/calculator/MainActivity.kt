package com.example.kalkulator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.kalkulator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val buttonIds = arrayOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
            R.id.buttonKurang, R.id.buttonTambah, R.id.buttonKali, R.id.buttonBagi,
            R.id.backspace, R.id.buttonDot, R.id.buttonSamadengan
        )

        for (buttonId in buttonIds) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener { onButtonClick(it) }
        }
    }

    private fun onButtonClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()
        val currentText = binding.editText.text.toString()

        when (buttonText) {
            "C" -> {
                binding.editText.setText("")
                binding.resultTextView.text = ""
            }
            "⌫" -> {
                if (currentText.isNotEmpty()) {
                    binding.editText.setText(currentText.dropLast(1))
                }
            }
            "=" -> {
                try {
                    val expression = ExpressionBuilder(currentText).build()
                    val result = expression.evaluate()

                    if (result == result.toLong().toDouble()) {
                        binding.resultTextView.text = result.toLong().toString()
                    } else {
                        binding.resultTextView.text = result.toString()
                    }
                } catch (e: Exception) {
                    binding.resultTextView.text = getString(R.string.error_message)
                }
            }
            else -> {
                binding.editText.setText(currentText + buttonText)
            }
        }
    }
}