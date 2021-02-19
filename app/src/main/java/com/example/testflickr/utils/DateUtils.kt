package com.example.testflickr.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object{
        public fun getDateTime(s: String): String? {
            try {
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val netDate = Date(s.toLong() * 1000)
                return sdf.format(netDate)
            } catch (e: Exception) {
                return e.toString()
            }
        }
    }

}