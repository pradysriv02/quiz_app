package com.example.quizzy.presentation.landing.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.darkGreenish
import com.example.compose.golden
import com.example.compose.greenish
import com.example.compose.newGray
import com.example.compose.newGray_1
import com.example.compose.onSurfaceVariantDarkHighContrast
import com.example.compose.placeColor
import com.example.compose.placeLandingColor
import com.example.compose.placeLandingTextColor
import com.example.compose.primaryLight
import com.example.compose.surfaceLight
import com.example.quizzy.presentation.aibot.ChatUIEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeToolBar(toolBarTitle: String,
                logoutButtonCLicked :() -> Unit,
                postButtonClicked:() -> Unit,
                aiChatButtonClicked:() -> Unit){
    TopAppBar(title = {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(greenish), horizontalArrangement = Arrangement.Center) {
            Text(modifier = Modifier.background(
                greenish
            ),text = toolBarTitle, color = darkGreenish, fontWeight = FontWeight.Bold )
        }

    },
        navigationIcon = {
            IconButton(onClick = {logoutButtonCLicked()}) {
                Icon(imageVector = Icons.Filled.Logout, contentDescription = "LogOut", tint = darkGreenish)
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(greenish),

        actions = {
            IconButton(onClick = { aiChatButtonClicked() }) {
                Icon(imageVector = Icons.Filled.Chat, contentDescription = "AI Chat", tint = darkGreenish)
            }
        }
    )
}

@Composable
fun NavigationDrawerHeader(value: String?){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(40.dp)){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Filled.Person, contentDescription = null,Modifier.size(30.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = value?:"John Doe", color = Color.White, fontSize = 24.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
        }

    }
}

@Composable
fun NavigationDrawerOneItem(item: DrawerItems){
    Row(modifier = Modifier
        .fillMaxWidth().background(Color.White)){
        Icon(imageVector = item.icon, contentDescription = item.description)
        Spacer(modifier = Modifier.width(18.dp))
        Text(text = item.title, fontSize = 18.sp)
    }
}


@Composable
fun NewQuizButton(
    text:String,
    padding: Dp,
    onButtonClicked: () -> Unit
){
    Box(
        modifier = Modifier
            .padding(padding)
            .clickable { onButtonClicked() }
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(darkGreenish),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 20.sp, color = placeLandingColor, style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Placeholder(){
    TextField(modifier = Modifier
        .clip(RoundedCornerShape(50.dp))
        .width(370.dp)
        .shadow(10.dp), value = "Search here", onValueChange = {

    },

        placeholder = {
            Text(text = "Search")
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = placeLandingColor,
            focusedPlaceholderColor = placeLandingTextColor,
            focusedTextColor = placeLandingTextColor,
            unfocusedTextColor = placeLandingTextColor,
            unfocusedPlaceholderColor = placeLandingTextColor
        ),
    leadingIcon = { androidx.compose.material3.Icon(imageVector = Icons.Filled.Search, contentDescription = "search", tint = placeLandingTextColor)}
    )
}