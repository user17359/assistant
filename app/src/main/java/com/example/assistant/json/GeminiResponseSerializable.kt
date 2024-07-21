package com.example.assistant.json

import kotlinx.serialization.Serializable

@Serializable
data class GeminiResponseSerializable (
    val candidates: List<CandidateSerializable>,
    val usageMetadata: MetadataSerializable? = null
)