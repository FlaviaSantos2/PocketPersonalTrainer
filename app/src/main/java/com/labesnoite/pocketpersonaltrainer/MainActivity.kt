package com.labesnoite.pocketpersonaltrainer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.facebook.*
import com.facebook.login.LoginManager.getInstance
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null
    private var profileTracker: ProfileTracker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //login
        btnEntrar.setOnClickListener {
            if (loginValidate()) {
//                var user:Usuario? = getUsuarioBanco()
                //              if(user != null){
                val intent = Intent(this, MenuPrincipalActivity::class.java)
                val params: Bundle = Bundle()
                //                params.putSerializable("Usuario", user)
                //              intent.putExtra("Params", params)
                startActivity(intent)
                //        }
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

    /*private fun checkMensalidadeStatus(usuario:Usuario?): Boolean{
        var isOk = false
        if(usuario != null){
            if(usuario.getDtVencimentoMensalidade() == Date())
                isOk = true
        }
        return isOk
    }*/
    /*private fun getUsuarioBanco(): Usuario? {
        var user:Usuario? = Usuario()
        /*val call = RetrofitInitializer().userService().loginApp(edtEmail.text.toString(), edT_passWd.text.toString())
        call.enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>?, response: Response<Usuario?>?) {
                response?.body()?.let {
                    user = it
                    checkMensalidadeStatus(user)
                }
            }

            override fun onFailure(call: Call<Usuario?>?, t: Throwable?) {
                Log.e("Falha no login", t?.message)
            }
        })*/
        val vall = RetrofitInitializer().userService().getUser()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                        user = it.usuario!!
                })

        return user
    }*/

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
                        val profile = Profile.getCurrentProfile()
                        Log.d("MainActivity", "Facebook token: " + loginResult.accessToken.token)
                        Log.d("MainActivity", "Get from Facebook: " + loginResult.recentlyGrantedPermissions)
                        intent.putExtra("PerfilFb", profile)
                        startActivity(intent)
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
        if (edtEmail.text.toString().equals("gabrielluis_21@hotmail.com")
                && edT_passWd.text.toString().equals("pocketpersonaltrainer")) {
            return true
        }
        return false
    }
}
