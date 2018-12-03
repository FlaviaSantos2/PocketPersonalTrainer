package com.labesnoite.pocketpersonaltrainer.menuPrincipalFragments

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                CalendarioTreino()
            }
            1 -> PerfilUsuario()
            else -> {
                return SobreTreinos()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }
    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Treinos do Dia"
            1 -> "Meu Perfil"
            else -> {
                return "Sobre Treinos"
            }
        }
    }
}