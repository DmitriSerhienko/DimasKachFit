package com.dimaskach.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dimaskach.R

object FragmentManager {
    var currentFragment: Fragment? = null

    fun setFragment(newFragment: Fragment, act: AppCompatActivity) {
        val transaction = act.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.placeHolder, newFragment)
        transaction.commit()
        currentFragment = newFragment
    }

}