package com.example.assistant.data

import com.example.assistant.viewModel.ChatViewModel

interface InterventionElement{
    suspend fun execute(chatViewModel: ChatViewModel)
}