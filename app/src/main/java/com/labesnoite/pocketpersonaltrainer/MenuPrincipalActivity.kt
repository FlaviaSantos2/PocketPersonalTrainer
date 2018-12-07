package com.labesnoite.pocketpersonaltrainer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.labesnoite.pocketpersonaltrainer.entidade.TreinoUsuario
import com.labesnoite.pocketpersonaltrainer.menuPrincipalFragments.PageAdapter
import kotlinx.android.synthetic.main.activity_principal.*

class MenuPrincipalActivity : AppCompatActivity() {

    private var listTreino: ArrayList<TreinoUsuario>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val pageAdapter = PageAdapter(supportFragmentManager)

        mainLayout.adapter = pageAdapter
        tabsMenu.setupWithViewPager(mainLayout)
        //var user:Usuario? = getUsuarioBancoById()
    }

    /* private fun getUsuarioBancoById():Usuario? {
         var user:Usuario? = Usuario()
 /*        val params:Bundle = intent.getBundleExtra("Params")
         if(params.isEmpty){
             user = null
         }else{
             user = params.getSerializable("Usuario") as Usuario
             val call = RetrofitInitializer().userService().userById(user.getId())
             call.enqueue(object : Callback<Usuario> {
                 override fun onResponse(call: Call<Usuario>?, response: Response<Usuario?>?) {
                     response?.body()?.let {
                         user = it
                     }
             }

             override fun onFailure(call: Call<Usuario?>?, t: Throwable?) {
                     Log.e("Falha no login", t?.message)
                 }
             })
         }*/
         return user
     }*/


    private fun agendarTreino(treinoAgendado: TreinoUsuario) {
        //listTreino.add(treinoAgendado)
    }

    private fun listaTodosTreinosAgendados() {
        //for()
        //txtTreinosLista.text = listTreino
    }
}