package com.example.quizzy.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.compose.newGray
import com.example.compose.newGray_1
import com.example.compose.placeLandingColor
import com.example.compose.placeLandingTextColor
import com.example.compose.primaryLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreenDropDownMenu(
    menuName:String,
    menuList:List<String>,
    text: String,
    onDropDownClick : (String) -> Unit
){
    var isExpanded  by remember{
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = menuName,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {isExpanded = !isExpanded})
        {
            OutlinedTextField(
                modifier = Modifier.run {
                    fillMaxWidth()
                                .menuAnchor()
                },
                value = text,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = darkGreenish,
                    unfocusedTextColor = placeLandingTextColor,
                    focusedContainerColor = cardLandingColor,
                    unfocusedContainerColor = cardLandingColor
                ),
                shape = RoundedCornerShape(15.dp)
            )
            DropdownMenu(
                modifier = Modifier
                    .background(Color.White),
                expanded = isExpanded,
                onDismissRequest = {isExpanded=false}
            )
            {
                menuList.forEachIndexed { index, s ->
                    DropdownMenuItem(
                        text = { Text(text = s, color = Color.Gray)},
                        onClick = {
                            onDropDownClick(menuList[index])
                            isExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}
@Composable
fun QuizButtonGenerate(
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