package com.labesnoite.pocketpersonaltrainer.menuPrincipalFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.labesnoite.pocketpersonaltrainer.R
import com.labesnoite.pocketpersonaltrainer.entidade.Usuario
import kotlinx.android.synthetic.main.fragment_perfil_usuario.*


class PerfilUsuario : Fragment() {

    private lateinit var user: Usuario

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        img_perfil.setImageURI(user.getUriFotoPerfil())
        txt_user_nome.text = user.getNome()

        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil_usuario, container, false)
    }

    fun setUsuarioNoFragment(usuario: Usuario) {
        this.user = usuario
    }

}