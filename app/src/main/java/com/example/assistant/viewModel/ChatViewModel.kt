package com.example.assistant.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private val _conversationHistory: MutableState<List<Message>> = mutableStateOf(
        listOf()
    )

    val conversationHistory: State<List<Message>> = _conversationHistory

    suspend fun addMessage(message: Message) {
        chatRepository.addMessage(message)
        if(message.user == Sender.USER) {
            chatResponse(message)
        }
    }

    private suspend fun chatResponse(message: Message) {
        val response = modelRepository.generateResponse(message)
        addMessage(response)
    }

    suspend fun retrieveHistory() {
        _conversationHistory.value = chatRepository.getMessageHistory()
    }
}