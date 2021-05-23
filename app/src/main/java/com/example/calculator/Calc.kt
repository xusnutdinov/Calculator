package com.example.calculator

import net.objecthunter.exp4j.ExpressionBuilder

class Calc {

    fun getResult(item: String): Double {
        val expres = ExpressionBuilder(item).build()
        return expres.evaluate()
    }
}