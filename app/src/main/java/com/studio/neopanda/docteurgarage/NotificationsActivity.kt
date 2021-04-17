package com.studio.neopanda.docteurgarage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notifications.*

class NotificationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        // set the Toolbar as ActionBar
        setSupportActionBar(toolbar)

        // set the listener to the BottomNavigationView and pass the listener item to it.
        bottomNavigationViewNotification.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListenerNotification)

        if (savedInstanceState == null) {
            val fragment = ViewNotificationsFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerNotification, fragment, fragment.javaClass.simpleName)
                .commit()
        }

    }

    private val mOnNavigationItemSelectedListenerNotification =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_notification_add -> {
                    val fragment = AddNotificationFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.containerNotification, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notification_view -> {
                    val fragment = ViewNotificationsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.containerNotification, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}
