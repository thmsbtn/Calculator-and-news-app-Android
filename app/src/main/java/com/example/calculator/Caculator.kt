package com.example.calculator

import android.view.View
import android.widget.TextView
import android.widget.Toast
import net.java.dev.eval.Expression
import java.text.DecimalFormat

class  Caculator {

    var calcul :String = ""
    var resultat :String = ""
    lateinit var exp: Expression

    val all = setOf<String>("-", "+", "/", ".", "*", "%")
    val tabPlusMoins = setOf<String>("+","-")
    val tabNum = setOf<String>("0","1","2","3","4","5","6","7","8","9")
    val tabEx = setOf<String>("%",".")
    val tabE = setOf<String>("/", "*")

    fun add(tS: TextView, tR: TextView, v: View) {

        calcul += v.tag
        when{
            all.contains(v.tag.toString()) -> refresh(tS, calcul)
            else -> afficheResultat(v,tS,tR)
        }
        changeColor(v,tS,tR)

    }


    fun refresh(t: TextView, str: String) {
        t.setText(str)
    }

    fun checkBtn(v: View, tS: TextView, tR: TextView) {

        if(calcul.isNotEmpty()) {

            // Si le précédent est un tabNuméro, on peut mettre n'importe quel signe
            if(tabNum.contains(calcul.last().toString()) && (tabPlusMoins.contains(v.tag) || tabE.contains(v.tag) || tabEx.contains(v.tag))) {
                add(tS, tR, v)
            }
            // Si le précédent est un tabNuméro et l'actuel un tabNuméro, tout est ok
            else if(tabNum.contains(calcul.last().toString()) && tabNum.contains(v.tag)) {
                add(tS, tR, v)
            }
            // Si le précédent est un signe et que l'actuel est un tabNuméro
            else if(tabPlusMoins.contains(calcul.last().toString()) && tabNum.contains(v.tag)) {
                add(tS, tR, v)
            } else if (tabE.contains(calcul.last().toString()) && tabNum.contains(v.tag)) {
                add(tS, tR, v)
            } else if (tabEx.contains(calcul.last().toString()) && tabNum.contains(v.tag)) {
                add(tS, tR, v)
            }
            // Si le précédent est dans tabE et que le suivant est un + ou un -
            else if (tabE.contains(calcul.last().toString()) && tabPlusMoins.contains(v.tag)) {
                add(tS, tR, v)
            }
            // Si le précédent est un % ou un ., le suivant est obligatoirement un chiffre
            else if(tabEx.contains(calcul.last().toString()) && tabNum.contains(v.tag)) {
                add(tS, tR, v)
            }

        } else {
            if(!tabEx.contains(v.tag) && !tabE.contains(v.tag)){
                add(tS, tR, v)
            }

        }


    }


    fun changeColor(v: View, tS: TextView,tR: TextView) {

        v.backgroundTintList?.let {
            tS.setTextColor(it)
        }
        v.backgroundTintList?.let {
            tR.setTextColor(it)
        }

    }


    fun calcul(str: String,v: View): String {
        val df: DecimalFormat = DecimalFormat("###.####")
        var resultat :String = ""
        try {
            exp = Expression(str)
            resultat += df.format(exp.eval())
        } catch (e: ArithmeticException) {
            Toast.makeText(v.context, "Erreur", Toast.LENGTH_SHORT).show()
        }

        return resultat
    }

    fun afficheResultat(v: View, tS: TextView, tR: TextView) {

        if (all.contains(calcul.last().toString())) {
            suppr(tS)
            refresh(tS, calcul)
        } else if (calcul.isEmpty()) {
            refresh(tR, "0")
        }else{
            resultat = "= " + calcul(calcul,v)
            when (calcul) {
                "42" -> Toast.makeText(v.context, "La réponse de la vie", Toast.LENGTH_LONG).show()
                "69" -> Toast.makeText(v.context, "T'es un petit cochon", Toast.LENGTH_LONG).show()
                "51" -> Toast.makeText(v.context, "Pastis ou aliens ?", Toast.LENGTH_LONG).show()
                "0+0" -> Toast.makeText(v.context, "La tête à Toto", Toast.LENGTH_LONG).show()
                "666" -> Toast.makeText(v.context, "Mode satanique activé", Toast.LENGTH_LONG).show()
                "404" -> Toast.makeText(v.context, "J'ai pas trouvé", Toast.LENGTH_LONG).show()
                else -> refresh(tS, calcul)
            }
            when (resultat) {
                "= 42" -> makeFun("La réponse de la vie", tR, resultat,v)
                "= 69" -> makeFun("Petit cochon", tR, resultat,v)
                "= 51" -> makeFun("Alien ou Pastaga",tR, resultat,v)
                "= 666" -> makeFun("Mode satanique activé", tR, resultat,v)
                "= 404" -> makeFun("J'ai pas trouvé", tR, "404 : not found",v)
                else -> refresh(tR, resultat)
            }
            refresh(tS, calcul)
            refresh(tR, resultat)
        }


    }

    fun makeFun(s: String, t: TextView, st: String,v: View) {
        Toast.makeText(v.context, s, Toast.LENGTH_LONG).show()
        refresh(t, st)
    }


    fun reset(t: TextView, tH: TextView) {
        calcul = ""
        refresh(t, calcul)
        resultat = "= "
        refresh(tH, resultat)
    }

    fun suppr(t: TextView) {

        calcul = calcul.dropLast(1)
        refresh(t, calcul)

    }


}