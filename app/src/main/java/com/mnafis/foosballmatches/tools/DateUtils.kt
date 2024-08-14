package com.mnafis.foosballmatches.tools

import com.mnafis.foosballmatches.models.DateInfo

fun DateInfo.getDate(): String {
    return "$month/$day/$year"
}

fun DateInfo.getTime(): String {
    var meridiem = "AM"
    var formattedHour = hour
    if (hour > 12) {
        formattedHour = hour - 12
        meridiem = "PM"
    }
    return "$formattedHour:$minute $meridiem"
}