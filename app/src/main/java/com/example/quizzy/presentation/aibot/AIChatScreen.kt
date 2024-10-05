package com.example.quizzy.presentation.aibot

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.compose.cardLandingColor
import com.example.compose.darkBluish
import com.example.compose.darkGreenish
import com.example.compose.grayValour
import com.example.compose.greenish
import com.example.compose.newGray
import com.example.compose.newGray_1
import com.example.compose.placeColor
import com.example.compose.placeLandingColor
import com.example.compose.placeLandingTextColor
import com.example.compose.primaryLight
import com.example.compose.surfaceContainerDarkHighContrast
import com.example.compose.surfaceContainerHighDarkMediumContrast
import com.example.compose.surfaceLight
import com.example.quizzy.presentation.components.DividerLines
import com.example.quizzy.presentation.nav_graph.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AIChatScreen(navController: NavController){

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Scaffold(
            topBar = {
                Box(modifier =
                Modifier
                    .fillMaxWidth()
                    .background(greenish)
                    .height(100.dp)
                    .padding(30.dp))
                {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(modifier = Modifier
                            .clickable {
                                       navController.navigate(route = Routes.LandingScreen.route)
                            },
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Back",
                            tint = darkGreenish)
                        Text(
                            text = "Ask AI",
                            fontSize = 19.sp,
                            color = darkGreenish)

                        Icon(imageVector = Icons.Outlined.Help,
                            contentDescription = "Help",
                            tint = darkGreenish)
                    }

                }
            }
        ) {

            ChatScreen(paddingValues = it)
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(paddingValues: PaddingValues){
    val chatViewModel = viewModel<ChatViewModel>()
    val chatState = chatViewModel.chatState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .background(color = greenish),
        verticalArrangement = Arrangement.Bottom
    )
    {

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp),
            reverseLayout = true
        ) {

            itemsIndexed(chatState.chatList) { index, chat ->
                if (chat.isFromUser) {
                    UserChatItem(prompt = chat.prompt)
                } else {
                    PromptChatItem(response = chat.prompt)
                }

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(16.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(placeLandingColor),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        chatViewModel.onEvent(
                            ChatUIEvent.SendPrompt(
                                chatState.prompt,
                                chatState.image
                            )
                        )
                    },
                tint = darkGreenish,
                imageVector = Icons.Outlined.Image,
                contentDescription = "Image")

            Spacer(modifier = Modifier.width(10.dp))

            TextField(modifier = Modifier.clip(RoundedCornerShape(10.dp)).width(270.dp), value = chatState.prompt, onValueChange = {
                chatViewModel.onEvent(ChatUIEvent.UpdatePrompt(it))
            },
                placeholder = {
                    Text(text = "Type your Prompt")
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = placeLandingColor,
                    focusedPlaceholderColor = darkGreenish,
                    focusedTextColor = darkGreenish,
                    unfocusedTextColor = placeLandingTextColor,
                    unfocusedPlaceholderColor = placeLandingTextColor
                ))
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        chatViewModel.onEvent(
                            ChatUIEvent.SendPrompt(
                                chatState.prompt,
                                chatState.image
                            )
                        )
                    },
                tint = darkGreenish,
                imageVector = Icons.Outlined.Send,
                contentDescription = "Send" )
        }

        }
    }

}

@Composable
fun UserChatItem(prompt: String){
    Column(
        modifier = Modifier.padding(start = 100.dp, bottom = 22.dp)
    ) {
        Text(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 15.dp))
            .border(
                1.dp,
                color = grayValour,
                shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 15.dp)
            )
            .background(darkGreenish)
            .padding(16.dp),
            text = prompt,
            fontSize = 17.sp,
            color = Color.White)
    }
}

@Composable
fun PromptChatItem(response: String){
    Column(
        modifier = Modifier.padding(end = 100.dp, bottom = 22.dp)
    ) {
        Text(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp, 12.dp, 12.dp, 0.dp))
            .border(
                0.8.dp,
                color = grayValour,
                shape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 0.dp)
            )
            .background(cardLandingColor)
            .padding(16.dp),
            text = response,
            fontSize = 17.sp,
            color = darkGreenish)
    }
}



