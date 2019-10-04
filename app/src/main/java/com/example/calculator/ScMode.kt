package com.example.calculator

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView





class ScMode : AppCompatActivity() {

    val  c : Caculator = Caculator()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scintific_mode)
        c.mode = "S"
    }


    fun onClickMe2(v: View) {
        var tS: TextView
        tS = findViewById<TextView>(R.id.txtVSa)

        var tR: TextView
        tR = findViewById<TextView>(R.id.txtVResS)

        var tOC: TextView
        tOC = findViewById<EditText>(R.id.txtOldCalS)

        var tH: TextView
        tH = findViewById<TextView>(R.id.txtHistoS)



        when {
            v.tag == "=" -> c.afficheResultat(v,tS, tR,tOC,tH)
            v.tag == "c" -> c.reset(tS, tR,tOC,tH)
            v.tag == "s" -> c.suppr(v,tS,tR,tOC,tH)
            v.tag == "m" -> switchActivity()
            else -> c.checkBtn(v, tS, tR,tOC,tH)
        }


    }
    fun switchActivity(){
        val intent = Intent(this, NormalMode::class.java)
        startActivity(intent)

    }
}
