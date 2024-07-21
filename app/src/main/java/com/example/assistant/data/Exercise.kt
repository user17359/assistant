package com.example.assistant.data

data class Exercise(
    val image: Int,
    val name: String,
    val durationSeconds: Int,
    val status: Boolean = false
)