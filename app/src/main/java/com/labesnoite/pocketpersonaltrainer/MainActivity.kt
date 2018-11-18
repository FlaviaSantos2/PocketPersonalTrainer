package com.labesnoite.pocketpersonaltrainer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.util.Log
import android.view.View
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager.*
import com.facebook.login.LoginResult
import java.util.*

class MainActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //login
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)
        btnEntrar.setOnClickListener {

        }
        //Chama tela de cadastro
        val lblCadastrar = findViewById<TextView>(R.id.TxTViewCafastro)
        lblCadastrar.setOnClickListener {
            val intent = Intent(this, CadastrarActivity::class.java)
            startActivity(intent)
        }
        //Login pelo Facebook
        val btnLoginFacebook = findViewById<Button>(R.id.btnFacebookLogin)
        btnLoginFacebook.setOnClickListener {
            facebookLogin()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    private fun facebookLogin() {
        callbackManager = CallbackManager.Factory.create()
        getInstance().logInWithReadPermissions(this, Arrays.asList("name", "email", "picture"))
        getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {

                        Log.d("MainActivity", "Facebook token: " + loginResult.accessToken.token)
                        Log.d("MainActivity", "Get from Facebook: " + loginResult.recentlyGrantedPermissions)
                        //val it = Intent(applicationContext, FacebookAuthenticatedActivity::class.java)
                        //startActivity(it)
                    }

                    override fun onCancel() {
                        Log.d("MainActivity", "Facebook onCancel.")
                    }

                    override fun onError(error: FacebookException) {
                        Log.d("MainActivity", "Facebook onError.")
                    }
                })
    }
}
