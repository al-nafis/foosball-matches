package com.mnafis.foosballmatches.tools

import android.annotation.SuppressLint
import com.mnafis.foosballmatches.models.DateInfo
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


@SuppressLint("SimpleDateFormat")
fun getFormattedDate(dateInfo: DateInfo): String {
    val c: Calendar = Calendar.getInstance()
    c.set(dateInfo.year, dateInfo.month, dateInfo.day)
    return SimpleDateFormat("MM/dd/y").format(Date(c.timeInMillis))
}