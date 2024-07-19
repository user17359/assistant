package com.example.assistant.data

enum class Sender {
    USER,
    MODEL,
    SYSTEM
}

data class Message(
    val content: String,
    val user: Sender
)