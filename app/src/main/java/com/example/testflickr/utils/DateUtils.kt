package com.example.testflickr.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object{

        private const val DATE_FORMAT = "dd/MM/yyyy"

        fun getDateTime(s: String): String? {
            return try {
                val sdf = SimpleDateFormat(DATE_FORMAT)
                val netDate = Date(s.toLong() * 1000)
                sdf.format(netDate)
            } catch (e: Exception) {
                e.toString()
            }
        }
    }

}