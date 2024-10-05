package com.example.quizzy.presentation.quiz.component

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.compose.cardLandingColor
import com.example.compose.darkGreenish
import com.example.compose.newGray_1
import com.example.compose.placeLandingColor
import com.example.compose.placeLandingTextColor
import com.example.compose.primaryContainerLight
import com.example.compose.primaryLight


@Preview
@Composable
fun PrevQuizOption(){
    QuizOption(
        optionNumber = "A",
        options = "Some option having 5 to 6 words",
        selected = false,
        onOptionClick = {},
        onUnselectOption = {})
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizOption(
    optionNumber:String,
    options:String,
    selected:Boolean,
    onOptionClick : () -> Unit,
    onUnselectOption:() -> Unit
){
    val optionTextColor = if(selected) Color.White else darkGreenish
    val transition = updateTransition(selected, label = "selected")
    val startColor by transition.animateColor(
        transitionSpec = { tween(durationMillis = 100, easing = LinearEasing) },
        label = "startColor") {
            selectedBox ->
        if (selectedBox) darkGreenish
        else cardLandingColor
    }
    Box(
        modifier = Modifier
            .noRippleClickable { onOptionClick() }
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(
                color = startColor,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if(!selected){
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .weight(1.5f)
                        .shadow(10.dp, CircleShape, clip = true, Color.Black)
                        .clip(CircleShape)
                        .background(placeLandingColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = optionNumber,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = darkGreenish,
                        textAlign = TextAlign.Center
                    )
                }
            }
            else{
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(CircleShape)
                        .size(90.dp)
                )
            }
            Spacer(modifier = Modifier
                .width(10.dp)
                .weight(0.6f))

            Text(
                modifier = Modifier.weight(7.1f),
                text = options,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 3,
                color = optionTextColor
            )
            if(selected){
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .weight(1.5f)
                        .shadow(10.dp, CircleShape, clip = true, Color.Black)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .clickable { onUnselectOption() }
                        ,
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "X",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
            else{
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(CircleShape)
                        .size(90.dp)
                )
            }
        }
    }
}

@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit) : Modifier = composed{
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource()}){
        onClick()
    }
}