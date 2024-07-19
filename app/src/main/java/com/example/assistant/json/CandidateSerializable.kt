package com.example.assistant.json

import kotlinx.serialization.Serializable

@Serializable
data class CandidateSerializable (
    val content: ContentSerializable,
    val finishReason: String? = null,
    val safetyRatings: List<SafetySerializable>? = null,
)