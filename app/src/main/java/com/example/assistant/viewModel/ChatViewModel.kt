package com.example.assistant.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assistant.data.Message
import com.example.assistant.data.Sender
import com.example.assistant.repositories.ChatRepository
import com.example.assistant.repositories.GeminiModelRepository
import com.example.assistant.repositories.LocalChatRepository
import com.example.assistant.repositories.ModelRepository

class ChatViewModel(
    private val chatRepository: ChatRepository = LocalChatRepository(),
    private val modelRepository: ModelRepository = GeminiModelRepository()
): ViewModel() {

    val conversationHistory = MutableLiveData<List<Message>>()

    init {
        conversationHistory.value = listOf()
    }

    suspend fun addMessage(message: Message) {
        chatRepository.addMessage(message)
        loadHistory()
        if(message.user == Sender.USER) {
            chatResponse(message)
        }
    }

    private suspend fun chatResponse(message: Message) {
        val response = modelRepository.generateResponse(message)
        addMessage(response)
    }

    suspend fun loadHistory() {
        conversationHistory.postValue(chatRepository.getMessageHistory())
    }
}