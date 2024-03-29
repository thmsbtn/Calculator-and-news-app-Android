package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.TextView


class NormalMode : AppCompatActivity() {

    val  c : Caculator = Caculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal_mode)
        c.mode = "N"
    }

    fun onClickMe(v: View) {
        var tS: TextView = findViewById<TextView>(R.id.txtVSa)

        var tR: TextView = findViewById<TextView>(R.id.txtVRes)

        var tOC: TextView  = findViewById<TextView>(R.id.txtOldCal)

        var tH: TextView = findViewById<TextView>(R.id.txtHisto)



        when {
            v.tag == "=" -> c.afficheResultat(v,tS, tR,tOC,tH)
            v.tag == "c" -> c.reset(tS, tR,tOC,tH)
            v.tag == "s" -> c.suppr(v,tS,tR,tOC,tH)
            v.tag == "()" -> switchActivity()
            else -> c.checkBtn(v, tS, tR,tOC,tH)
        }
    }


    fun switchActivity(){
        val intent = Intent(this, ScMode::class.java)
        startActivity(intent)

    }


}
