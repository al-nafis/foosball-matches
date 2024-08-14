package com.mnafis.foosballmatches.tools

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


@SuppressLint("SimpleDateFormat")
fun getFormattedDate(year: Int, month: Int, day: Int): String {
    val c: Calendar = Calendar.getInstance()
    c.set(year, month, day)
    return SimpleDateFormat("MM/dd/y").format(Date(c.timeInMillis))
}