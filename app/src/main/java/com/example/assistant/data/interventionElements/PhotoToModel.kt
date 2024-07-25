package com.example.assistant.data.interventionElements

import com.example.assistant.R
import com.example.assistant.data.InterventionElement
import com.example.assistant.data.Message
import com.example.assistant.data.Sender
import com.example.assistant.viewModel.ChatViewModel

class PhotoToModel(val next: InterventionElement): InterventionElement {
    override suspend fun execute(chatViewModel: ChatViewModel) {
        chatViewModel.addMessage(Message("aaa", Sender.FAKEUSER, R.drawable.scones))
        chatViewModel.sendPictureToModel()
        next.execute(chatViewModel)
    }
}