package com.labesnoite.pocketpersonaltrainer

import android.content.Intent

<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
=======
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
>>>>>>> PocketPersonalTrainer App
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
<<<<<<< HEAD
import com.facebook.login.LoginManager.*
import com.facebook.login.LoginResult
import com.labesnoite.pocketpersonaltrainer.config.RetrofitInitializer
import com.labesnoite.pocketpersonaltrainer.entidade.Usuario
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
=======
import com.facebook.login.LoginManager.getInstance
import com.facebook.login.LoginResult
import com.labesnoite.pocketpersonaltrainer.entidade.Usuario
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
>>>>>>> PocketPersonalTrainer App

class MainActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null
    private var user: Usuario = Usuario()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        <<<<<<< HEAD
        val call = RetrofitInitializer().userService().loginApp(edtEmail.text.toString(), edT_passWd.text.toString())
        call.enqueue(object : Callback<Usuario?> {
            override fun onResponse(call: Call<Usuario?>?, response: Response<Usuario?>?) {
                response?.body()?.let {
                    user = it
                }
            }

            override fun onFailure(call: Call<Usuario?>?, t: Throwable?) {
                Log.e("Falha no login", t?.message)
            }
        })

        //login
        btnEntrar.setOnClickListener {
            if (loginValidate()) {
                val intent = Intent(this, MenuPrincipalActivity::class.java)
                //intent.putExtra("Usuario.id")
                ====== =
                //login
                btnEntrar.setOnClickListener {
                    if (loginValidate()) {
                        //val call = RetrofitInitializer().userService().loginApp(edtEmail.text.toString(), edT_passWd.text.toString())
                        //call.enqueue(object : Callback<Usuario?> {
                        //override fun onResponse(call: Call<Usuario?>?, response: Response<Usuario?>?) {
                        //  response?.body()?.let {
                        //   user = it
                        // checkMensalidadeEmDia(user)
                        //}
                        //}

                        //override fun onFailure(call: Call<Usuario?>?, t: Throwable?) {
                        //   Log.e("Falha no login", t?.message)
                        //}
                        //})
                        val intent = Intent(this, MenuPrincipalActivity::class.java)
                        //intent.putExtra("Usuario", user)
                        >>>>>>> PocketPersonalTrainer App
                        startActivity(intent)
                    } else {
                        AlertDialog.Builder(this@MainActivity)
                                .setMessage("E-mail ou senha invalidos!")
                                .setPositiveButton(android.R.string.ok, null)
                                .show()
                        edtEmail.setFocusable(true)
                    }
                }
        //Chama tela de cadastro
        txtViewCadastrar.setOnClickListener {
            val intent = Intent(this, CadastrarActivity::class.java)
            startActivity(intent)
        }
        //Login pelo Facebook
        btnFacebookLogin.setOnClickListener {
            facebookLogin()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    private fun facebookLogin() {
        callbackManager = CallbackManager.Factory.create()
        getInstance().logInWithReadPermissions(this, Arrays.asList("name", "email", "user_picture"))
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
                }
        )
    }

    private fun loginValidate(): Boolean {
        <<<<<<< HEAD
        val isValidate: Boolean
        if (edtEmail.text.isBlank() || edT_passWd.text.isBlank()) {
            isValidate = false
        } else {
            isValidate = true
        }

        return isValidate
    }
            ====== =
            var isValidate = false
            if (edtEmail.text.isEmpty() || edT_passWd.text.isEmpty())
                isValidate = true

            return isValidate
        }

        private fun checkMensalidadeEmDia(user: Usuario): Boolean {
            var emDia = false
            if (user.getDtVencimentoMensalidade() > Date() && user.getStatus() == 0)
                emDia = true

            return emDia
        }
        >>>>>>> PocketPersonalTrainer App
}
