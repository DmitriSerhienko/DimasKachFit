package com.dimaskach.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


object TimeUtil {
    @SuppressLint("SimpleDateFormat")
    val formatter = SimpleDateFormat("mm:ss")
    fun getTime(time: Long): String {
        val cv = Calendar.getInstance()
        cv.timeInMillis = time
        return formatter.format(cv.time)
    }
}