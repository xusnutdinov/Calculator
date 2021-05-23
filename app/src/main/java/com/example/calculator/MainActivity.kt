package com.example.calculator

//import android.
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    companion object {
        const val BRACKET = "BRACKET"
        const val TEXTVIEW = "TEXTVIEW"
        const val LAST_SYMBOL = "LAST_SYMBOL"
    }

    private var bracketBool: Int = 0
    private var lastSymbol: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textView = findViewById<TextView>(R.id.textView2)

        var btnClear = findViewById<Button>(R.id.button1)
        var btnEqual = findViewById<Button>(R.id.button20)


        var btnOparate = arrayOf(
            findViewById(R.id.button),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9),
            findViewById(R.id.button10),
            findViewById(R.id.button11),
            findViewById(R.id.button12),
            findViewById(R.id.button13),
            findViewById(R.id.button14),
            findViewById(R.id.button15),
            findViewById(R.id.button16),
            findViewById(R.id.button18),
            findViewById<Button>(R.id.button19),
        )



        btnClear.setOnClickListener {
            textView.text = ""
        }

        btnEqual.setOnClickListener {
            try {
                val calc = Calc()
                val res = calc.getResult(textView.text.toString())
                val longRes = res.toLong()
                if (res == longRes.toDouble()){
                    textView.text = longRes.toString()
                } else {
                    textView.text = res.toString()
                }
            } catch (e:Exception) {
                Log.d("Exception", " message: " + e.message)
            }
        }

        val BtnClick: View.OnClickListener = View.OnClickListener { v ->
            if ((v as Button).text.toString() == "()") {
                if (bracketBool != 0) {
                    if (lastSymbol == "*" || lastSymbol == "/" || lastSymbol == "+" || lastSymbol == "^" || lastSymbol == "-" || lastSymbol == "sqrt") {
                        textView.append("(")
                        bracketBool += 1
                    } else {
                        textView.append(")")
                        bracketBool -= 1
                    }
                } else if(bracketBool == 0){
                    textView.append("(")
                    bracketBool += 1
                }
            } else if (v.text.toString() == "sqrt") {
                textView.append("sqrt(")
                bracketBool += 1
            } else {
                textView.append(v.text.toString())
            }
            lastSymbol = v.text.toString()
        }

        for (btn in btnOparate) {
            btn.setOnClickListener(BtnClick)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var textView = findViewById<TextView>(R.id.textView2)
        outState.putString(TEXTVIEW, textView.text.toString())
        outState.putInt(BRACKET, bracketBool)
        outState.putString(LAST_SYMBOL, lastSymbol)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        var textView = findViewById<TextView>(R.id.textView2)
        textView.text = savedInstanceState.getString(TEXTVIEW).toString()
        bracketBool = savedInstanceState.getInt(BRACKET)
        lastSymbol = savedInstanceState.getString(LAST_SYMBOL).toString()
    }
}
