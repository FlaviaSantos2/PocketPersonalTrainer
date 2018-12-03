package com.labesnoite.pocketpersonaltrainer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.labesnoite.pocketpersonaltrainer.entidade.Usuario
import com.labesnoite.pocketpersonaltrainer.menuPrincipalFragments.AgendarTreino
import com.labesnoite.pocketpersonaltrainer.menuPrincipalFragments.PageAdapter
import kotlinx.android.synthetic.main.activity_principal.*
import kotlinx.android.synthetic.main.fragment_menu_calendario.*

class MenuPrincipalActivity : AppCompatActivity() {

    private var user = Usuario()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val pageAdapter = PageAdapter(supportFragmentManager)

        mainLayout.adapter = pageAdapter
        tabsMenu.setupWithViewPager(mainLayout)

        btnAddTreino.setOnClickListener {
            val fragment: Fragment = AgendarTreino()
            val fM = supportFragmentManager
            val fmt = fM.beginTransaction()
            fmt.replace(R.id.AgendarTreino, fragment)
            fmt.commit()
        }

    }

    private fun setPerfilUsuario() {
        //user = intent.extras.get("Usuario") as Usuario
        //PerfilUsuario().setUsuarioNoFragment(user)
    }

    private fun agendarTreino() {

    }

    private fun listaTodosTreinosAgendados() {

    }
}