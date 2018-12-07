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
    private val uri = "https://www.facebook.com/photo.php?fbid=2388823477799106&set=a.157617434253066&type=3&theater"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//
//

        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil_usuario, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //img_perfil.setImageURI(Uri.parse(uri))
        txt_user_nome.text = "Gabriel Luis da Silva"
    }

    fun setUsuarioNoFragment(usuario: Usuario) {
        img_perfil.setImageURI(usuario.getUriFotoPerfil())
        txt_user_nome.text = usuario.getNome()
        txt_user_pesoatual.text = usuario.getPeso().toString()
        txt_perfil_pesoperdido.text = "0"
    }

}