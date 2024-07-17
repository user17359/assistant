package com.example.assistant.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.assistant.data.Message
import com.example.assistant.repositories.ChatRepository
import com.example.assistant.repositories.LocalChatRepository

class ChatViewModel(
    private val chatRepository: ChatRepository = LocalChatRepository()
): ViewModel() {

    private val _conversationHistory: MutableState<List<Message>> = mutableStateOf<List<Message>>(
        listOf()
    )

    val conversationHistory: State<List<Message>> = _conversationHistory

    suspend fun addMessage(message: Message) {
        chatRepository.addMessage(message)
    }

    suspend fun retrieveHistory() {
        _conversationHistory.value = chatRepository.getMessageHistory()
    }
}