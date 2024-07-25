package com.example.assistant.data

enum class Sender {
    USER,
    MODEL,
    SYSTEM,
    FAKEUSER
}

data class Message(
    val content: String,
    val user: Sender,
    val image: Int? = null
)