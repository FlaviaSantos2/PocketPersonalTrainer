package com.labesnoite.pocketpersonaltrainer

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.labesnoite.pocketpersonaltrainer.entidade.TreinoUsuario
import kotlinx.android.synthetic.main.fragment_menu_calendario.*

class AgendarTreino : AppCompatActivity() {

    private var treinoAgendado: TreinoUsuario? = null
    private var btnAgendar: View = btnAddTreino

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_agenda_treino)
    }


    private fun getAllTreinosName() {

    }


}