package com.joe.wanandroid

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.common.utils.arouter.RouterURLS

import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouterURLS.BASE_MAIN)
class MainActivity : AppCompatActivity() {

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }

        drawerLayout.run {
            val toggle = ActionBarDrawerToggle(
                    this@MainActivity,
                    this,
                    toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
            )
            addDrawerListener(toggle)
            toggle.syncState()
        }

        BottomNavigationViewHelper.disableShiftMode(bottomnavigationView)
        bottomnavigationView.run {
            setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
            selectedItemId = R.id.tab_home
        }

        navigationview.run {
            setNavigationItemSelectedListener(onDrawerNavigationItemSelectedListener)
        }
    }

    private val onNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                return@OnNavigationItemSelectedListener when (item.itemId) {
                    R.id.tab_home -> {
                        true
                    }
                    R.id.tab_knowledge -> {
                        true
                    }
                    R.id.tab_navigat -> {
                        true
                    }
                    R.id.tab_self -> {
                        true
                    }
                    else -> {
                        false
                    }
                }
            }

    private val onDrawerNavigationItemSelectedListener =
            NavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_mylike -> {

                    }
                    R.id.nav_guanyu -> {

                    }
                    R.id.nav_logout -> {

                    }
                }
                true
            }

}
