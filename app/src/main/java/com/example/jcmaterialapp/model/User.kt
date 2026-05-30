package com.example.jcmaterialapp.model

data class User(
    var name: String,
    var surname: String,
    var height: Int,
    var birthdate: Long = 0,
    var occupation: String = "",
    var notes: String = ""
)
