package com.okawa.blockchain.mkt.ui.tutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.okawa.blockchain.mkt.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
    }
}