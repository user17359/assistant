package com.example.assistant.data.interventionElements

import com.example.assistant.data.InterventionElement
import com.example.assistant.data.Message
import com.example.assistant.viewModel.ChatViewModel

class SendMessage(val message: Message, val next: InterventionElement) : InterventionElement {
    override suspend fun execute(chatViewModel: ChatViewModel) {
        chatViewModel.addMessage(message)
        next.execute(chatViewModel)
    }

}