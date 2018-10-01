package com.labesnoite.pocketpersonaltrainer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnEntrar = findViewById(R.id.btnEntrar) as Button
        btnEntrar.setOnClickListener {

        }
        val lblCadastrar = findViewById(R.id.TxTViewCafastro) as TextView
        lblCadastrar.setOnClickListener {

        }
    }


}
