package com.example.assistant.repositories

import com.example.assistant.data.Message
import com.example.assistant.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig

interface ModelRepository {
    suspend fun generateResponse(message: Message): Message
}

class GeminiModelRepository : ModelRepository {

    val model = GenerativeModel(
        "gemini-1.5-flash",
        // Retrieve API key as an environmental variable defined in a Build Configuration
        // see https://github.com/google/secrets-gradle-plugin for further instructions
        BuildConfig.GEMINI_API_KEY,
        generationConfig = generationConfig {
            temperature = 1f
            topK = 64
            topP = 0.95f
            maxOutputTokens = 8192
            responseMimeType = "text/plain"
        },
        // safetySettings = Adjust safety settings
        // See https://ai.google.dev/gemini-api/docs/safety-settings
        systemInstruction = content { text("Jesteś wykwalifikowaną osobą pomagającą dziecku z otyłością.\n\nOdpisuj krótkimi wiadomościami, maksymalnie 1 paragraf") },
    )

    val chat = model.startChat()

    override suspend fun generateResponse(message: Message): Message {
        val response = chat.sendMessage(message.content)
        return Message(response.text ?: "Gemini failed to operate", false)
    }
}