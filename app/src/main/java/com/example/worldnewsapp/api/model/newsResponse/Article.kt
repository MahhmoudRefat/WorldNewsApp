package com.example.worldnewsapp.api.model.newsResponse

import com.example.worldnewsapp.api.Constants
import com.example.worldnewsapp.api.model.sourcesResponse.Source
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
){
    fun formatDurationFromNow(): String {
        val dateFormat = when {
            publishedAt.contains(".") -> SimpleDateFormat(
                Constants.DATE_PATTERN1,
                Locale.getDefault(),
            )

            else -> SimpleDateFormat(Constants.DATE_PATTERN2, Locale.getDefault())
        }
        dateFormat.timeZone = TimeZone.getTimeZone(Constants.TIMEZONE)

        val currentDate = Calendar.getInstance().time
        val providedDate = dateFormat.parse(publishedAt)
        val difference = currentDate.time - providedDate!!.time

        val seconds = difference / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val months = days / 30
        val years = months / 12

        return when {
            years > 0 -> "$years years ago"
            months > 0 -> "$months months ago"
            days > 0 -> "$days days ago"
            hours > 0 -> "$hours hours ago"
            minutes > 0 -> "$minutes minutes ago"
            else -> "$seconds seconds ago"
           }
      }
}

