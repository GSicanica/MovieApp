package com.sicoapp.movieapp

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.sicoapp.movieapp.databinding.ActivityEntryBinding
import com.sicoapp.movieapp.ui.profil.MyProfileViewModel
import com.sicoapp.movieapp.utils.MovieFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_entry.*
import javax.inject.Inject

@AndroidEntryPoint
class EntryActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val myProfileViewModel by viewModels<MyProfileViewModel>()
    lateinit var navController: NavController
    lateinit var bottomNav: BottomNavigationView
    lateinit var binding: ActivityEntryBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var fragmentFactory: MovieFragmentFactory

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.topMovieFragment,
                R.id.popularMovieFragment,
                R.id.searchFragment,
                R.id.savedFragment,
                R.id.movieDetailsFragment
            ), drawerLayout
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.fragmentFactory = fragmentFactory

        binding = DataBindingUtil.setContentView(this, R.layout.activity_entry)

        bottomNav = binding.bottomNavigationView

        bottomNav.visibility = View.GONE

        navController = findNavController(R.id.nav_host_fragment_entry)

        toolbar = binding.toolbar

        setupBottomNavigation()

        setupNav()

        setupNavigation()

        //upload myProfil and drawer from DB
        myProfileViewModel.userFromDB.observe(this) {
            val image = it?.image
            val name = it?.name

            if (image != null) {
                Log.e("simpleName", "2")
                myProfileViewModel.bindMyProfile.image = image
                setDrawerHeaderImage(image)
            }

            if (it?.name != null) {
                myProfileViewModel.bindMyProfile.name = name
                setDrawerHeaderProfilName(name)
            }
        }
    }

    private fun setupNavigation() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        drawerLayout = binding.drawerLayout
        val navigationView = binding.navigationView
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        menuItem.isChecked = true
        drawerLayout.closeDrawers()
        when (menuItem.itemId) {
            R.id.my_profile -> navController.navigate(R.id.myProfileFragment)
            R.id.movies -> navController.navigate(R.id.topMovieFragment)
            R.id.saved -> navController.navigate(R.id.savedFragment)
            R.id.sign_out -> navController.navigate(R.id.introFragment)
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setupNav() {
        navController.addOnDestinationChangedListener {
                _, destination, _ ->
            when (destination.id) {
                R.id.topMovieFragment -> showBottomNav()
                R.id.popularMovieFragment -> showBottomNav()
                R.id.searchFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun setDrawerHeaderProfilName(image: String?) {
        val headerProfilName =
            this.navigation_view.getHeaderView(0)
                .findViewById(R.id.tv_username) as TextView

        headerProfilName.text = image
    }


    private fun setDrawerHeaderImage(image: String?) {
        //set into drawer header
        val headerProfilImageView =
            this.navigation_view.getHeaderView(0)
                .findViewById(R.id.header_imageView) as ImageView

        this.let { context ->
            Glide
                .with(context)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_local_movies_24)
                .into(headerProfilImageView)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    private fun setupBottomNavigation() {
        bottomNav.setupWithNavController(navController)
    }

    private fun showBottomNav() {
        toolbar.visibility = View.VISIBLE
        bottomNav.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        toolbar.visibility = View.GONE
        bottomNav.visibility = View.GONE
    }
}
