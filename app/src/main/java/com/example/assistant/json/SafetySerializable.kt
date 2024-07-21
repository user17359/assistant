package com.example.assistant.json

import kotlinx.serialization.Serializable

@Serializable
data class SafetySerializable (
    val category: String,
    val probability: String,
    val probabilityScore: Double,
    val severity: String,
    val severityScore: Double
)