package com.example.assistant.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ChatBubble(message: String = "",
               color: Color = Color.LightGray,
               textColor: Color = Color.Black,
               textAlign: TextAlign = TextAlign.Start,
               maxWidth: Dp = 250.dp){

    val cornerRadius = 15.dp
    val padding = PaddingValues(15.dp, 10.dp)

    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(shape = RoundedCornerShape(cornerRadius))
            .background(color = color)

    ){
        Text(
            modifier = Modifier.
                        padding(padding)
                        .widthIn(0.dp, maxWidth),
            text = message,
            color = textColor,
            textAlign = textAlign
        )
    }
}

@Preview
@Composable
fun PreviewChatBubble(){
    ChatBubble("Lorem ipsum", Color.Blue, Color.White)
}

@Preview
@Composable
fun PreviewChatBubbles(){
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ChatBubble("Hi!", Color.LightGray, Color.Black)
        ChatBubble("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut finibus dignissim leo", Color.LightGray, Color.Black)
        ChatBubble("Hi!", Color.LightGray, Color.Black)
    }
}