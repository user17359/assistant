package com.example.assistant.data

data class Exercise(
    val id: Int,
    val image: Int,
    val name: String,
    val durationSeconds: Int,
    var status: Boolean = false
)