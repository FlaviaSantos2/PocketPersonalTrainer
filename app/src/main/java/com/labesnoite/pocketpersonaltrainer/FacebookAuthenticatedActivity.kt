package com.labesnoite.pocketpersonaltrainer

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.facebook.appevents.AppEventsLogger
import com.facebook.FacebookSdk


class FacebookAuthenticatedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        //FacebookSdk.sdkInitialize(applicationContext.getApplicationContext())
        //AppEventsLogger.(this)
    }

}