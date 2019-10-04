package com.example.calculator

import android.view.View
import android.widget.TextView
import android.widget.Toast
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat


class Caculator {


    lateinit var mode: String
    private var calcul: String = ""
    private var calculA: String = ""
    private var oldCalcul: String = ""
    private var resultat: String = "= "
    private var histo: String = ""
    private lateinit var exp: Expression

    private val all = setOf<String>(
        "-",
        "+",
        "/",
        ".",
        "*",
        "%",
        "sqrt(",
        "(",
        "^",
        ")",
        "log(",
        "log10(",
        "e",
        "pi",
        "abs("
    )
    private val tabPlusMoins = setOf<String>("+", "-")
    private val tabNum = setOf<String>("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    private val tabEx = setOf<String>("%", ".")
    private val tabDivMul = setOf<String>("/", "*")
    private val sc = setOf<String>("sqrt(", "log(", "log10(", "e", "pi")


    fun add(tS: TextView, tR: TextView, v: View, tOc: TextView, sv: TextView) {
        calcul += v.tag
        when {
            all.contains(v.tag.toString()) -> tS.setText(calcul)
            else -> afficheResultat(v, tS, tR, tOc, sv)
        }
        changeColor(v, tS, tR)
    }


    fun checkBtn(v: View, tS: TextView, tR: TextView, tOc: TextView, sv: TextView) {

        var last: String

        if (calcul.isNotEmpty()) {

            last = calcul.last().toString()

            // Si le précédent est un tabNuméro, on peut mettre n'importe quel signe
            if (tabNum.contains(last) && all.contains(v.tag)) {
                add(tS, tR, v, tOc, sv)
            }
            // Si le précédent est un tabNuméro et l'actuel un tabNuméro, tout est ok
            else if (tabNum.contains(last) && tabNum.contains(v.tag)) {
                add(tS, tR, v, tOc, sv)
            }
            // Si le précédent est un signe et que l'actuel est un tabNuméro
            else if (tabPlusMoins.contains(last) && (tabNum.contains(v.tag) || v.tag.equals("("))) {
                add(tS, tR, v, tOc, sv)
            } else if ((tabDivMul.contains(last) || last.equals("^")) && (tabNum.contains(v.tag) || tabPlusMoins.contains(
                    v.tag
                ) || v.tag == "(")
            ) {
                add(tS, tR, v, tOc, sv)
            } else if (tabEx.contains(last) && tabNum.contains(v.tag)) {
                add(tS, tR, v, tOc, sv)
            } else if (last.equals(")") && (v.tag.equals("^") || tabDivMul.contains(v.tag) || tabPlusMoins.contains(
                    v.tag
                ) || v.tag.equals("%"))
            ) {
                add(tS, tR, v, tOc, sv)
            } else if (last.equals("(") && (tabPlusMoins.contains(v.tag) || tabNum.contains(v.tag))) {
                add(tS, tR, v, tOc, sv)
            }
            // Si le précédent est dans tabE et que le suivant est un + ou un -
            else if (tabDivMul.contains(last) && tabPlusMoins.contains(v.tag)) {
                add(tS, tR, v, tOc, sv)
            }
            // Si le précédent est un % ou un ., le suivant est obligatoirement un chiffre
            else if (tabEx.contains(last) && tabNum.contains(v.tag)) {
                add(tS, tR, v, tOc, sv)
            }

        } else {
            if (!tabEx.contains(v.tag) && !tabDivMul.contains(v.tag)) {
                add(tS, tR, v, tOc, sv)
            }

        }

    }


    fun changeColor(v: View, tS: TextView, tR: TextView) {

        v.backgroundTintList?.let {
            tS.setTextColor(it)
        }
        v.backgroundTintList?.let {
            tR.setTextColor(it)
        }

    }


    fun calcul(str: String, v: View, tR: TextView): String {
        val df: DecimalFormat = DecimalFormat("###.####")
        var res: String = ""
        try {
            val ex = ExpressionBuilder(str)
                .build()
                .evaluate()

            res += df.format(ex)
        } catch (e: Exception) {
            Toast.makeText(v.context, "Erreur", Toast.LENGTH_SHORT).show()

        }
        return res
    }

    fun afficheResultat(v: View, tS: TextView, tR: TextView, tOc: TextView, sv: TextView) {

        if (all.contains(calcul.last().toString()) && !calcul.last().toString().equals(")")) {
            suppr(v, tS, tR, tOc, sv)
            tS.setText(calcul)
        } else if (calcul.isEmpty()) {
            tR.setText("= 0")
        } else {
            if (mode.equals("N")) {
                resultat = calcul(calcul, v, tR)
                fullRefresh(tS, tR, tOc, sv)
            }
            if (v.tag == "=") {
                resultat = calcul(calcul, v, tR)
                histo += oldCalcul + '\n'
                oldCalcul = calcul
                calcul = resultat
                fullRefresh(tS, tR, tOc, sv)
            }
            when (calcul) {
                "42" -> Toast.makeText(v.context, "La réponse de la vie", Toast.LENGTH_LONG).show()
                "69" -> Toast.makeText(v.context, "T'es un petit cochon", Toast.LENGTH_LONG).show()
                "51" -> Toast.makeText(v.context, "Pastis ou aliens ?", Toast.LENGTH_LONG).show()
                "0+0" -> Toast.makeText(v.context, "La tête à Toto", Toast.LENGTH_LONG).show()
                "666" -> Toast.makeText(
                    v.context,
                    "Mode satanique activé",
                    Toast.LENGTH_LONG
                ).show()
                "404" -> Toast.makeText(v.context, "J'ai pas trouvé", Toast.LENGTH_LONG).show()
                else -> tS.setText(calcul)
            }
        }

    }

    fun fullRefresh(tS: TextView, tR: TextView, tOc: TextView, sv: TextView) {
        tS.setText(calcul)
        tR.setText(resultat)
        tOc.setText(oldCalcul)
        sv.setText(histo)
    }

    fun reset(tS: TextView, tR: TextView, tOc: TextView, sv: TextView) {
        if (calcul.isNotEmpty()) {
            calcul = ""
            tS.setText(calcul)
            resultat = "= "
            tR.setText(resultat)
        }

    }

    fun suppr(v: View, tS: TextView, tR: TextView, tOc: TextView, sv: TextView) {

        if (calcul.isNotEmpty() && calcul.length > 1) {
            calcul = calcul.dropLast(1)
            afficheResultat(v, tS, tR, tOc, sv)
        } else if (calcul.length == 1) {
            calcul = calcul.dropLast(1)
            tR.setText("= ")
            tS.setText(calcul)
        }


    }


}