package com.example.assistant.json

import kotlinx.serialization.Serializable

@Serializable
data class ContentSerializable (
    val role: String,
    val parts: List<PartSerializable>,
    val finishReason: String? = null
)