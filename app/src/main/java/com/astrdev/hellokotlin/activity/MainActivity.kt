package com.astrdev.hellokotlin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.astrdev.hellokotlin.R
import com.astrdev.hellokotlin.fragment.MainFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    companion object {
        public fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupFragment()
    }


    private fun setupFragment() {
        var fragment = supportFragmentManager.findFragmentById(R.id.activity_main_fragment_holder)
        if (fragment == null) fragment = MainFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.activity_main_fragment_holder, fragment).commit()
    }


    private fun setupToolbar() {
        setSupportActionBar(activity_main_toolbar)
    }
}