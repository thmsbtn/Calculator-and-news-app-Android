package com.example.calculator

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.java.dev.eval.Expression
import java.math.RoundingMode
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    var ope = ""
    lateinit var exp: Expression
    var c: ColorStateList? = null
    var res = "= "


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onClickMe(v: View) {
        var tHis: TextView
        tHis = findViewById<TextView>(R.id.textViewHistory)

        var tRes: TextView
        tRes = findViewById<TextView>(R.id.txtVRes)


        when {
            v.tag == "=" -> showResult(tHis, tRes)
            v.tag.equals("c") -> reset(tHis, tRes)
            v.tag == "s" -> suppr(tHis)
            else -> check(v, tHis)
        }
    }

    fun add(t: TextView, v: View) {
        ope += v.tag
        refresh(t, ope)
    }


    fun refresh(t: TextView, str: String) {
        t.setText(str)
    }

    fun check(v: View, t: TextView) {
        val tab = setOf<String>("-", "+", "/", ".", "*", "%")
        changeColor(v,t)
        when {
            ope.isEmpty() && (v.tag == "/" || v.tag == "*") -> refresh(t, "ttt")
            ope.isNotEmpty() && tab.contains(ope.last().toString()) && tab.contains(v.tag) -> refresh(t,ope.replaceLast(v.tag.toString()))
            else -> add(t, v)
        }
    }

    fun String.replaceLast(s:String):String{
        var st:String = this.dropLast(1)
        st += s
        return st
    }

    fun changeColor(v: View, t: TextView) {

        v.backgroundTintList?.let {
            t.setTextColor(it)
        }

    }


    fun showResult(t: TextView, tR: TextView) {
        res = "= "
        if (ope.last() == '+' || ope.last() == '-' || ope.last() == '/' || ope.last() == '*' || ope.last() == '.') {
            suppr(t)
            showResult(t, tR)
        } else if (ope.isEmpty()) {
            refresh(t, "0")
        } else {
            when (ope) {
                "42" -> Toast.makeText(baseContext,"La réponse de la vie",Toast.LENGTH_LONG).show()
                "69" -> Toast.makeText(baseContext, "T'es petit cochon", Toast.LENGTH_LONG).show()
                "51" -> Toast.makeText(baseContext, "Pastis ou aliens ?", Toast.LENGTH_LONG).show()
                "0+0" -> Toast.makeText(baseContext, "La tête à Toto", Toast.LENGTH_LONG).show()
                "666" -> Toast.makeText(baseContext,"Mode satanique activé",Toast.LENGTH_LONG).show()
                "404" -> Toast.makeText(baseContext, "J'ai pas trouvé", Toast.LENGTH_LONG).show()
                else -> control(ope, tR)
            }
        }
    }

    fun makeFun(s:String, t:TextView, st:String){
        Toast.makeText(baseContext,s,Toast.LENGTH_LONG).show()
        refresh(t, st)
    }

    fun control(f: String, t: TextView) {
        try {
            val df: DecimalFormat = DecimalFormat("###.####")
            exp = Expression(f)
            res += df.format(exp.eval())

            when (res) {
                "= 42" -> makeFun("La réponse de la vie",t,res)
                "= 69" -> makeFun("Petit cochon",t,res)
                "= 51" ->  makeFun("Alien ou Pastaga",t,res)
                "= 0+0" ->  makeFun("La Tête à toto",t,"0+0")
                "= 666" -> makeFun("Mode satanique activé",t,res)
                "= 404" -> makeFun("J'ai pas trouvé",t, "404 : not found")
                else -> refresh(t, res)
            }
        } catch (e: ArithmeticException) {
            Toast.makeText(baseContext, "Erreur", Toast.LENGTH_SHORT).show()
        }

    }

    fun reset(t: TextView, tH: TextView) {
        ope = ""
        refresh(t, ope)
        res = "= "
        refresh(tH, res)
    }

    fun suppr(t: TextView) {

        ope = ope.dropLast(1)
        refresh(t, ope)

    }


}
