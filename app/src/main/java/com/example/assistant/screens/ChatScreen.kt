package com.example.assistant.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.assistant.R
import com.example.assistant.data.Message
import com.example.assistant.data.Sender
import com.example.assistant.elements.BottomNavBar
import com.example.assistant.elements.ChatBubble
import com.example.assistant.viewModel.ChatViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ChatScreen(navController: NavController, chatViewModel: ChatViewModel){

    val coroutineScope = rememberCoroutineScope()

    var last: Sender? = null
    var text by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    val messageHistory = chatViewModel.conversationHistory.observeAsState()

    LaunchedEffect(true){
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                chatViewModel.loadHistory()
            }
        }
    }

    Scaffold(
        topBar = {TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text("Konwersacja")
            }
        )
        },
        bottomBar = { BottomNavBar(selected = 2, navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ){
            Column() {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(top = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    reverseLayout = true,
                    state = listState
                ) {
                    items(messageHistory.value ?: listOf()) { message ->
                        if (last != null && last != message.user) {
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                        when (message.user) {
                            Sender.USER -> {
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
                            Sender.SYSTEM -> {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 15.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    ChatBubble(
                                        message.content,
                                        color = Color.LightGray,
                                        textColor = Color.Black,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                            else -> {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.chatbot),
                                        contentDescription = null,
                                        modifier = Modifier.height(48.dp)
                                            .padding(end = 12.dp)
                                    )
                                    ChatBubble(
                                        message.content,
                                        color = Color.LightGray,
                                        textColor = Color.Black
                                    )
                                }
                            }
                        }
                        last = message.user
                    }
                }
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(top = 15.dp)
                        .height(80.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    TextField(
                        modifier = Modifier
                            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp)
                            .fillMaxWidth()
                            .weight(1f)
                            .clip(shape = RoundedCornerShape(30.dp)),
                        value = text,
                        onValueChange = {text = it}
                    )
                    IconButton(
                        modifier = Modifier.padding(end = 15.dp),
                        enabled = (text != ""),
                        onClick = {
                            coroutineScope.launch {
                                val sendText = text
                                text = ""
                                chatViewModel.addMessage(Message(sendText, Sender.USER))
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_send_24),
                            contentDescription = "Send button",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewChatScreen()
{
    ChatScreen(rememberNavController(), viewModel())
}