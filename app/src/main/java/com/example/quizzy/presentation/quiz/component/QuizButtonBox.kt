package com.example.quizzy.presentation.quiz.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.cardLandingColor
import com.example.compose.darkGreenish
import com.example.compose.primaryLight

@Composable
fun QuizButtonBox(
    text:String,
    padding: Dp,
    onButtonClicked: () -> Unit
){
    Box(
        modifier = Modifier
            .padding(padding)
            .clickable { onButtonClicked() }
            .height(60.dp)
            .width(150.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(darkGreenish),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 20.sp, color = cardLandingColor, style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold))
    }
}