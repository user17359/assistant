package com.example.assistant.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assistant.data.InterventionElement
import com.example.assistant.data.Message
import com.example.assistant.data.Sender
import com.example.assistant.data.interventionElements.InterventionOption
import com.example.assistant.repositories.ChatRepository
import com.example.assistant.repositories.GeminiModelRepository
import com.example.assistant.repositories.LocalChatRepository
import com.example.assistant.repositories.ModelRepository

enum class InputMode{
    Keyboard,
    TwoButtons,
    Off
}

class ChatViewModel(
    private val chatRepository: ChatRepository = LocalChatRepository(),
    private val modelRepository: ModelRepository = GeminiModelRepository()
): ViewModel() {

    val conversationHistory = MutableLiveData<List<Message>>()

    val inputMode = MutableLiveData(InputMode.Keyboard)

    var firstButtonContent = ""
    var secondButtonContent = ""
    var firstButton: suspend () -> Unit = {}
    var secondButton: suspend () -> Unit = {}

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

    suspend fun sendPictureToModel() {
        val response = modelRepository.generatePhotoDescription()
        addMessage(response)
    }

    suspend fun loadHistory() {
        conversationHistory.postValue(chatRepository.getMessageHistory())
    }

    suspend fun interventionExecute(interventionScheme: InterventionElement) {
        //inputMode.postValue(InputMode.Off)
        //Start(interventionScheme).execute(this)
    }

    suspend fun setTwoButtons(options: List<InterventionOption>){
        inputMode.postValue(InputMode.TwoButtons)
        firstButtonContent = options[0].caption
        secondButtonContent = options[1].caption
        firstButton = suspend {inputMode.postValue(InputMode.Off); options[0].result.execute(this)}
        secondButton = suspend {inputMode.postValue(InputMode.Off); options[1].result.execute(this)}
    }

    suspend fun endIntervention(){
        inputMode.postValue(InputMode.Keyboard)
    }
}