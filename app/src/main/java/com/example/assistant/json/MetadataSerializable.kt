package com.example.assistant.json

import kotlinx.serialization.Serializable

@Serializable
data class MetadataSerializable (
    val promptTokenCount: Int,
    val candidatesTokenCount: Int,
    val totalTokenCount: Int
)