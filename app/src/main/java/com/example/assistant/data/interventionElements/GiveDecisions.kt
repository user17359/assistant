package com.example.assistant.data.interventionElements

import com.example.assistant.data.InterventionElement
import com.example.assistant.viewModel.ChatViewModel

data class InterventionOption(
    val caption: String,
    val result: InterventionElement
)

class GiveDecisions(
    val options: List<InterventionOption>
): InterventionElement {
    override suspend fun execute(chatViewModel: ChatViewModel) {
        chatViewModel.setTwoButtons(options)
    }
}