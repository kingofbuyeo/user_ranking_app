package yong.chul.rank.user_ranking_app.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

fun jsonModule(): ObjectMapper {
    val mapper = jacksonObjectMapper()
    mapper.registerModule(JavaTimeModule())
    return mapper
}

inline fun <reified T> T.toJsonString(): String {

    return jsonModule().writeValueAsString(this)
}

inline fun <reified T> String.fromJson(): T? {
    return jsonModule().readValue(this, T::class.java)
}