package com.example.assistant.data.interventionElements

import com.example.assistant.data.InterventionElement
import com.example.assistant.viewModel.ChatViewModel
import kotlinx.coroutines.delay

class Delay (val ms: Long, val next: InterventionElement) : InterventionElement {
    override suspend fun execute(chatViewModel: ChatViewModel) {
        delay(ms)
        next.execute(chatViewModel)
    }
}