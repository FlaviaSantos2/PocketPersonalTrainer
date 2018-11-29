package com.labesnoite.pocketpersonaltrainer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.labesnoite.pocketpersonaltrainer.entidade.Usuario
import com.labesnoite.pocketpersonaltrainer.menuPrincipalFragments.PageAdapter
import kotlinx.android.synthetic.main.activity_principal.*

class MenuPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_menu)

        class MenuPrincipalActivity : AppCompatActivity() {

            private var user = Usuario()

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_principal)

                val pageAdapter = PageAdapter(supportFragmentManager)

                //user = intent.extras.getSerializable("Usuario") as Usuario
                // PerfilUsuario().setUsuarioNoFragment(user)

                mainLayout.adapter = pageAdapter
                tabsMenu.setupWithViewPager(mainLayout)
            }
        }
    }
}