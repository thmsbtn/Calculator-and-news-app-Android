package com.example.calculator

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.java.dev.eval.Expression
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    val all = setOf<String>("-", "+", "/", ".", "*", "%")
    var ope = ""
    var res = "= "

    lateinit var exp: Expression
    var c: ColorStateList? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onClickMe(v: View) {
        var tS: TextView
        tS = findViewById<TextView>(R.id.textVSaisie)

        var tR: TextView
        tR = findViewById<TextView>(R.id.txtVRes)

        when {
            v.tag == "=" -> afficheResultat(v,tS, tR)
            v.tag.equals("c") -> reset(tS, tR)
            v.tag == "s" -> suppr(tS)
            else -> checkBtn(v, tS, tR)
        }
    }

    fun add(tS: TextView, tR: TextView, v: View) {

        ope += v.tag
        when{
            all.contains(v.tag.toString()) -> refresh(tS, ope)
            else -> afficheResultat(v,tS,tR)
        }
        changeColor(v,tS,tR)

    }


    fun refresh(t: TextView, str: String) {
        t.setText(str)
    }

    fun checkBtn(v: View, tS: TextView, tR: TextView) {

        val tabA = setOf<String>("+","-")
        val num = setOf<String>("0","1","2","3","4","5","6","7","8","9")
        val ex = setOf<String>("%",".")
        val tabE = setOf<String>("/", "*")



        if(ope.isNotEmpty()) {

            // Si le précédent est un numéro, on peut mettre n'importe quel signe
            if(num.contains(ope.last().toString()) && (tabA.contains(v.tag) || tabE.contains(v.tag) || ex.contains(v.tag))) {
                add(tS, tR, v)
            }
            // Si le précédent est un numéro et l'actuel un numéro, tout est ok
            else if(num.contains(ope.last().toString()) && num.contains(v.tag)) {
                add(tS, tR, v)
            }
            // Si le précédent est un signe et que l'actuel est un numéro
            else if(tabA.contains(ope.last().toString()) && num.contains(v.tag)) {
                add(tS, tR, v)
            } else if (tabE.contains(ope.last().toString()) && num.contains(v.tag)) {
                add(tS, tR, v)
            } else if (ex.contains(ope.last().toString()) && num.contains(v.tag)) {
                add(tS, tR, v)
            }
            // Si le précédent est dans tabE et que le suivant est un + ou un -
            else if (tabE.contains(ope.last().toString()) && tabA.contains(v.tag)) {
                add(tS, tR, v)
            }
            // Si le précédent est un % ou un ., le suivant est obligatoirement un chiffre
            else if(ex.contains(ope.last().toString()) && num.contains(v.tag)) {
                add(tS, tR, v)
            }

        } else {
            add(tS, tR, v)
        }


    }

    fun replaceLast(sSrc:String, s: String){
        var st: String =""
        println(sSrc)
        println(sSrc.substring(0,sSrc.length-1))
        st += sSrc.substring(0,sSrc.length-1)
        st += s

    }

    fun changeColor(v: View, tS: TextView,tR: TextView) {

        v.backgroundTintList?.let {
            tS.setTextColor(it)
        }
        v.backgroundTintList?.let {
            tR.setTextColor(it)
        }

    }


    fun calcul(str: String): String {
        val df: DecimalFormat = DecimalFormat("###.####")
        var res :String = ""
        try {
            exp = Expression(str)
            res += df.format(exp.eval())
        } catch (e: ArithmeticException) {
            Toast.makeText(baseContext, "Erreur", Toast.LENGTH_SHORT).show()
        }

        return res
    }

    fun afficheResultat(v: View, tS: TextView, tR: TextView) {

        if (all.contains(ope.last().toString())) {
            suppr(tS)
            refresh(tS, ope)
        } else if (ope.isEmpty()) {
            refresh(tR, "0")
        }else{
            res = "= " + calcul(ope)
            when (ope) {
                "42" -> Toast.makeText(baseContext, "La réponse de la vie", Toast.LENGTH_LONG).show()
                "69" -> Toast.makeText(baseContext, "T'es un petit cochon", Toast.LENGTH_LONG).show()
                "51" -> Toast.makeText(baseContext, "Pastis ou aliens ?", Toast.LENGTH_LONG).show()
                "0+0" -> Toast.makeText(baseContext, "La tête à Toto", Toast.LENGTH_LONG).show()
                "666" -> Toast.makeText(baseContext, "Mode satanique activé", Toast.LENGTH_LONG).show()
                "404" -> Toast.makeText(baseContext, "J'ai pas trouvé", Toast.LENGTH_LONG).show()
                else -> refresh(tS, ope)
            }
            when (res) {
                "= 42" -> makeFun("La réponse de la vie", tR, res)
                "= 69" -> makeFun("Petit cochon", tR, res)
                "= 51" -> makeFun("Alien ou Pastaga",tR, res)
                "= 666" -> makeFun("Mode satanique activé", tR, res)
                "= 404" -> makeFun("J'ai pas trouvé", tR, "404 : not found")
                else -> refresh(tR, res)
            }
            refresh(tS, ope)
            refresh(tR, res)
        }


    }

fun makeFun(s: String, t: TextView, st: String) {
    Toast.makeText(baseContext, s, Toast.LENGTH_LONG).show()
    refresh(t, st)
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
