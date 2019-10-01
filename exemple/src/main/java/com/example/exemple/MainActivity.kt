package com.example.exemple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fragments.ArticlesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*

 */
        val fragment = ArticlesFragment()

        supportFragmentManager.beginTransaction().apply {

            replace(R.id.fragment_container, fragment)

            addToBackStack(null)
        }.commit()


    }


}
