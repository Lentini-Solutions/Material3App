package com.example.jcmaterialapp.utils

import com.example.jcmaterialapp.model.User
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

//This function, required Min API SDK Android 26 YES OR YES
fun convertMillisDate(millis: Long): String {

    val dateFormatter = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.SHORT) //FormatStyle.SHORT show day/month/year
        .withLocale(Locale.getDefault())

    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(millis),
        ZoneId.of("UTC")).
    format(dateFormatter)
}

fun userFormatter(user: User): String {
    val result = StringBuilder()
    result.append("Nombre: $user.name\n")
    result.append("Apellido: ${user.surname}\n")
    result.append("Fecha de Nacimiento: ${convertMillisDate(user.birthdate)}\n")
    result.append("Ocupación: ${user.occupation}\n")
    result.append("Notas: ${user.notes}")
    return result.toString()
}