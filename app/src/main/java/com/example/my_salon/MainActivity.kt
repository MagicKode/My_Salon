package com.example.my_salon

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat.START
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.my_salon.databinding.ActivityMainBinding
import com.example.my_salon.view.home.HomeFragment
import com.example.my_salon.view.login.LoginFragment
import com.example.my_salon.view.orders.OrderFragment
import com.example.my_salon.view.registration.RegistrationActivity
import com.example.my_salon.view.share.ShareFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.menu)

        drawLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        toggle = ActionBarDrawerToggle(
            this,
            drawLayout,
            R.string.open_nav,
            R.string.close_nav
        )
        drawLayout.addDrawerListener(toggle)
        toggle.syncState()

        setSupportActionBar(binding.mainToolbar)
        binding.mainToolbar.setNavigationIcon(R.drawable.ic_menu_white)
        binding.mainToolbar.setNavigationOnClickListener {
            drawLayout.open()
        }

        // условие для отображении ДОМАШНЕЙ страницы при входе в приложение
        viewFragment(HomeFragment())

//        if (savedInstanceState == null) {
//            viewFragment(
//                HomeFragment()
//            )
//            navigationView.setCheckedItem(R.id.nav_home)
//        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                viewFragment(HomeFragment())
            }

            R.id.nav_my_orders -> {  //todo menu page
                viewFragment(OrderFragment())
            }

            R.id.nav_share -> {  //todo share function
                viewFragment(ShareFragment())
            }

            R.id.nav_registration -> {
                startActivity(Intent(this@MainActivity, RegistrationActivity::class.java))
            }

            R.id.nav_login -> {
                viewFragment(LoginFragment())
            }
        }
        drawLayout.closeDrawer(START)

        return false
    }

    private fun viewFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onBackPressed() {
        if (drawLayout.isDrawerOpen(START)) {
            drawLayout.closeDrawer(START)
        } else {
            super.onBackPressed()
        }
    }
}