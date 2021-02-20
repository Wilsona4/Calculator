package com.funcrib.practice_calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Making tvExpression Scrollable
//        tvExpression.isSelected = true

//        Numeric Buttons
        tvOne.setOnClickListener { append("1", true) }
        tvTwo.setOnClickListener { append("2", true) }
        tvThree.setOnClickListener { append("3", true) }
        tvFour.setOnClickListener { append("4", true) }
        tvFive.setOnClickListener { append("5", true) }
        tvSix.setOnClickListener { append("6", true) }
        tvSeven.setOnClickListener { append("7", true) }
        tvEight.setOnClickListener { append("8", true) }
        tvNine.setOnClickListener { append("9", true) }
        tvZero.setOnClickListener { append("0", true) }
        tvDot.setOnClickListener { append(".", true) }

//        Operations Buttons
        tvAdd.setOnClickListener { append("+", false) }
        tvSubtract.setOnClickListener { append("-", false) }
        tvMultiply.setOnClickListener { append("*", false) }
        tvDivide.setOnClickListener { append("/", false) }
        tvOpenBracket.setOnClickListener { append("(", false) }
        tvCloseBracket.setOnClickListener { append(")", false) }

        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }

        ivBack.setOnClickListener {
            val expression: String = tvExpression.text.toString()
            if (expression.isNotEmpty()) {
                tvExpression.text = expression.substring(0, expression.length - 1)
            }
            tvResult.text = ""
        }
        tvEquals.setOnClickListener {
            try {
                val expression =
                    ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                tvResult.text = result.toString()
            } catch (e: Exception) {
                Log.e("Error", "${e.message}")
            }
        }
    }

    private fun append(button: String, clear: Boolean) {
        if (tvResult.text.isNotEmpty()) {
            tvExpression.text = ""
        }
        if (clear) {
            tvResult.text = ""
            tvExpression.append(button)
        } else {
            tvExpression.append(tvResult.text)
            tvExpression.append(button)
            tvResult.text = ""
        }

    }
}