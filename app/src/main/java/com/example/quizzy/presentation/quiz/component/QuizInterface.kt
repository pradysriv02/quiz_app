package com.example.quizzy.presentation.quiz.component

import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.darkGreenish
import com.example.compose.greenish
import com.example.compose.newGray
import com.example.compose.surfaceLight
import com.example.quizzy.presentation.quiz.QuizState

//@Preview
//@Composable
//fun PrevQuizInterface(){
//    QuizInterface(onOptionSelected = {}, qNumber = 1, quizState = QuizState())
//}

@Composable
fun QuizInterface(
    onOptionSelected:(Int) -> Unit,
    qNumber:Int,
    quizState: QuizState,
    modifier: Modifier = Modifier
){
    val question = quizState.quiz?.question!!.replace("&quot","\"").replace("&#039","\'")
    Box(
        modifier = modifier
            .then(
                Modifier

                    .background(greenish)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 28.dp)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "$qNumber"+".",
                    color = darkGreenish,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier.weight(9f),
                    text = question,
                    color = darkGreenish,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                if(quizState.quiz.type=="multiple"){
                    val options = listOf(
                        "A" to quizState.shuffledOptions[0].replace("&quot","\"").replace("&#039","\'"),
                        "B" to quizState.shuffledOptions[1].replace("&quot","\"").replace("&#039","\'"),
                        "C" to quizState.shuffledOptions[2].replace("&quot","\"").replace("&#039","\'"),
                        "D" to quizState.shuffledOptions[3].replace("&quot","\"").replace("&#039","\'")
                    )

                    Column {
                        options.forEachIndexed { index, (optionNumber:String,optionText:String) ->
                            if(optionText.isNotEmpty()){
                                QuizOption(
                                    optionNumber = optionNumber,
                                    options = optionText,
                                    onOptionClick = { onOptionSelected(index) },
                                    selected = quizState.selectedOption==index,
                                    onUnselectOption = {onOptionSelected(-1)}
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
                else if(quizState.quiz.type=="boolean"){
                    val options = listOf(
                        "A" to quizState.shuffledOptions[0].replace("&quot","\"").replace("&#039","\'"),
                        "B" to quizState.shuffledOptions[1].replace("&quot","\"").replace("&#039","\'"),
                    )

                    Column {
                        options.forEachIndexed { index, (optionNumber:String,optionText:String) ->
                            if(optionText.isNotEmpty()){
                                QuizOption(
                                    optionNumber = optionNumber,
                                    options = optionText,
                                    onOptionClick = { onOptionSelected(index) },
                                    selected = quizState.selectedOption==index,
                                    onUnselectOption = {onOptionSelected(-1)}
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }

        }
    }
}