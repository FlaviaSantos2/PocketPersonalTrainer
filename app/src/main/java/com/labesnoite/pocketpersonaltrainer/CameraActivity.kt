package com.labesnoite.pocketpersonaltrainer


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.labesnoite.pocketpersonaltrainer.cam2.CameraFragment

class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        savedInstanceState ?: supportFragmentManager.beginTransaction()
                .replace(R.id.container, CameraFragment.newInstance())
                .commit()
    }


}