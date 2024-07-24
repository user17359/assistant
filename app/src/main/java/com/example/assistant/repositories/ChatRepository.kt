package com.example.assistant.repositories

import com.example.assistant.data.Message
import com.example.assistant.data.presets.mockData

interface ChatRepository {
    suspend fun getMessageHistory(): List<Message>
    suspend fun addMessage(message: Message)
}

class LocalChatRepository : ChatRepository {
    private val history: MutableList<Message> = mockData.toMutableList()

    override suspend fun getMessageHistory() : List<Message> {
        return history.map { it.copy() }
    }

    override suspend fun addMessage(message: Message) {
        history.add(0, message)
    }
}