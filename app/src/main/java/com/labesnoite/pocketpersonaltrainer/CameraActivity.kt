package com.labesnoite.pocketpersonaltrainer

import android.content.res.Configuration
import android.hardware.camera2.CameraCharacteristics

<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity
=======
>>>>>>> PocketPersonalTrainer App
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
<<<<<<< HEAD
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.labesnoite.pocketpersonaltrainer.cameraApp.*
=======
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.labesnoite.pocketpersonaltrainer.cameraApp.PreviewFragment
>>>>>>> PocketPersonalTrainer App
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : AppCompatActivity(), DrawerLayout.DrawerListener {

    override fun onDrawerStateChanged(newState: Int) {
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        drawerLayout.bringChildToFront(drawerView)
        drawerLayout.requestLayout()
    }

    override fun onDrawerClosed(drawerView: View) {
    }

    override fun onDrawerOpened(drawerView: View) {
    }

    private val PREVIEW_FRAGMENT_TAG = "previewFragment"

    val drawerToogle by lazy {
        ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        navigationView.setNavigationItemSelectedListener {
            selectDrawerItem(it)
            true
        }
        drawerLayout.addDrawerListener(this)
        val fragment = PreviewFragment.newInstance()
        addFragment(fragment, PREVIEW_FRAGMENT_TAG)
        // The ViewPager will be implemented once it's fragments have
        // been implemented.
//        val pagerAdapter = CamFragmentPagerAdapter(supportFragmentManager)
//        viewPager.adapter = pagerAdapter
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }

    private fun selectDrawerItem(item: MenuItem) {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.switchCameraId -> {
                fragment = supportFragmentManager.findFragmentByTag(PREVIEW_FRAGMENT_TAG)
                if (fragment is PreviewFragment)
                    item.icon = if (fragment.toggleCameraFacing() == CameraCharacteristics.LENS_FACING_BACK)
                        getDrawable(R.drawable.ic_camera_front)
                    else
                        getDrawable(R.drawable.ic_camera_rear)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (drawerToogle.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fragment_menu, menu)
        return false
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, fragment, tag)
        fragmentTransaction.commit()
    }
}