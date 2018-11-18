package com.labesnoite.pocketpersonaltrainer


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.support.design.widget.FloatingActionButton
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_cadastrar.*



class CadastrarActivity : AppCompatActivity() {


    private lateinit var imageView: ImageView
    private lateinit var fabtnTirarFoto: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        imageView = findViewById(R.id.fotoPerfil)
        fabtnTirarFoto = findViewById(R.id.fabtnTirarFoto)

        fabtnTirarFoto.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validarSenha() {
        val txtValidaSenha = findViewById<TextView>(R.id.txtView_validarPwd)
        val edtSenha = findViewById<EditText>(R.id.edtSenhaC)
        while (edtConfSenha.text != edtSenha.text) {
            txtValidaSenha.setText(R.string.lbl_passwdNotValid)

        }
        txtValidaSenha.setText(R.string.lbl_passwdValid)
    }
}

