package com.labesnoite.pocketpersonaltrainer.menuPrincipalFragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.labesnoite.pocketpersonaltrainer.AgendarTreino
import com.labesnoite.pocketpersonaltrainer.R

class CalendarioTreino : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_calendario, container, false)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnAddTreino -> {
                val intent = Intent(activity!!.applicationContext, AgendarTreino::class.java)
                startActivity(intent)
            }
        }
    }

}