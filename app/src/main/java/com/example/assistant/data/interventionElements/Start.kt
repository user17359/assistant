package com.example.assistant.data.interventionElements

import com.example.assistant.data.InterventionElement
import com.example.assistant.viewModel.ChatViewModel

class Start(val next: InterventionElement): InterventionElement {
    override suspend fun execute(chatViewModel: ChatViewModel) {
        next.execute(chatViewModel)
    }
}