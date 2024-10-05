package com.example.quizzy.presentation.score

import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.compose.cardLandingColor
import com.example.compose.darkGreenish
import com.example.compose.greenish
import com.example.compose.newGray
import com.example.compose.newGray_1
import com.example.compose.primaryLight
import com.example.compose.surfaceLight
import com.example.quizzy.R
import com.example.quizzy.presentation.nav_graph.Routes

//@Preview
//@Composable
//fun prevScoreScreen(){
//    ScoreScreen(numOfQuestions = 10, numOfCorrectAnswers = 6)
//}

@Composable
fun ScoreScreen(
    numOfQuestions:Int,
    numOfCorrectAnswers: Int,
     navController:NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(greenish),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                modifier = Modifier
                    .clickable {
                         navController.navigate(route = Routes.HomeScreen.route)
                    },
                imageVector = Icons.Filled.Close,
                contentDescription = "close",
                tint = darkGreenish)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(cardLandingColor),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 20.dp,
                        vertical = 20.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.congra))
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = darkGreenish)){
                        append("You Attempted ")
                    }
                    withStyle(style = SpanStyle(color = darkGreenish)){
                        append("$numOfQuestions Questions")
                    }
                    withStyle(style = SpanStyle(color = darkGreenish)){
                        append(" and from that ")
                    }
                    withStyle(style = SpanStyle(color = darkGreenish)){
                        append("$numOfCorrectAnswers answers")
                    }
                    withStyle(style = SpanStyle(color = darkGreenish)){
                        append(" are correct")
                    }
                }
                val scorePercentage = calculatePercentage(numOfCorrectAnswers,numOfQuestions)
                LottieAnimation(
                    modifier = Modifier.size(100.dp),
                    composition = composition,
                    iterations = 100
                    )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Congrats!",
                    color = darkGreenish,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "$scorePercentage% Score",
                    color = primaryLight,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Quiz Completed Successfully",
                    color = darkGreenish,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = annotatedString,
                    color = darkGreenish,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

fun calculatePercentage(k: Int, n: Int): Double {
    require(k>=0 && n>0){
        "Invalid input"
    }
    val percentage = (k.toDouble()/n.toDouble())*100.0
    return DecimalFormat("#.##").format(percentage).toDouble()
}
