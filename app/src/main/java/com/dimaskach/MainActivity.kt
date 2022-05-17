package com.dimaskach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dimaskach.fragments.DaysFragment
import com.dimaskach.utils.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }
}