package com.example.assistant.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.assistant.data.Message
import com.example.assistant.data.mockData
import com.example.assistant.elements.ChatBubble
import com.example.assistant.viewModel.ChatViewModel

@Composable
fun ChatScreen(chatViewModel: ChatViewModel){
    
    var last: Boolean? = null
    
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ){
            Column (
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                for(message: Message in mockData){
                    if(last != null && last != message.user){
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                    if(message.user) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 15.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            ChatBubble(
                                message.content,
                                color = Color.Blue,
                                textColor = Color.White
                            )
                        }
                    }
                    else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            ChatBubble(
                                message.content,
                                color = Color.LightGray,
                                textColor = Color.Black
                            )
                        }
                    }
                    last = message.user
                }
            }
        }
    }
}