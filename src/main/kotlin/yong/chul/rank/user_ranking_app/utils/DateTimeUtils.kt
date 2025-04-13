package yong.chul.rank.user_ranking_app.utils

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun LocalDateTime.fromFormatter(): String{
    val instant = this.toInstant(ZoneOffset.UTC)
    return DateTimeFormatter.ISO_INSTANT.format(instant)
}