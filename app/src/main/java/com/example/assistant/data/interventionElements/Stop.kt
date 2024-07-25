package com.example.assistant.data.interventionElements

import com.example.assistant.data.InterventionElement
import com.example.assistant.viewModel.ChatViewModel

class Stop: InterventionElement{
    override suspend fun execute(chatViewModel: ChatViewModel) {
        chatViewModel.endIntervention()
    }
}