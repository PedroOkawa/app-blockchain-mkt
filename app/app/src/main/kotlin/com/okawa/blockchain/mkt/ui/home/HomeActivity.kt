package com.okawa.blockchain.mkt.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.okawa.blockchain.mkt.R
import com.okawa.blockchain.mkt.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.vp_content) as DynamicNavHostFragment
        val navController = navHostFragment.navController
        binding.bottomMenu.setupWithNavController(navController)
    }
}