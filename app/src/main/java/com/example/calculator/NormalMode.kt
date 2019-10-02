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
    }

    fun onClickMe(v: View) {
        var tS: TextView
        tS = findViewById<TextView>(R.id.textVSaisie)

        var tR: TextView
        tR = findViewById<TextView>(R.id.txtVRes)

        when {
            v.tag == "=" -> c.afficheResultat(v,tS, tR)
            v.tag == "c" -> c.reset(tS, tR)
            v.tag == "s" -> c.suppr(tS)
            v.tag == "()" -> switchActivity()
            else -> c.checkBtn(v, tS, tR)
        }
    }


    fun switchActivity(){
        val intent = Intent(this, ScintificMode::class.java)
        startActivity(intent)

    }


}
