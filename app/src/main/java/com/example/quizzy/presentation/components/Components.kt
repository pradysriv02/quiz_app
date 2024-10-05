package com.example.quizzy.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.darkGreenish
import com.example.compose.greenish
import com.example.compose.placeLandingColor
import com.example.compose.primaryLight
import com.example.compose.scrimLight
import com.example.compose.surfaceDimLight

@Composable
fun NormalTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = darkGreenish,
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = darkGreenish,
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextComponent(labelValue: String,
                    onTextChanged:(String) -> Unit,
                    imageVector: ImageVector,
                    errorStatus:Boolean = false) {

    val textValue = remember { mutableStateOf(" ") }

    OutlinedTextField(
        label = { Text(text = labelValue)},
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = darkGreenish,
            focusedLabelColor = darkGreenish,
            unfocusedBorderColor = primaryLight,
            cursorColor = darkGreenish,
            focusedContainerColor = placeLandingColor,
            unfocusedContainerColor = placeLandingColor

        ),

        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        }
        ,

        leadingIcon = { Icon(imageVector = imageVector, contentDescription = "mail")}
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun PasswordComponent(labelValue: String,
                      imageVector: ImageVector,
                      onTextChanged: (String) -> Unit,
                      errorStatus: Boolean = false){
    val focusManager = LocalFocusManager.current
    val password = remember { mutableStateOf(" ") }
    val passwordVisible = remember { mutableStateOf(false) }
    OutlinedTextField(

        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = darkGreenish,
            focusedLabelColor = darkGreenish,
            unfocusedBorderColor = primaryLight,
            cursorColor = primaryLight,
            focusedContainerColor = placeLandingColor,
            unfocusedContainerColor = placeLandingColor

        ),
        label = { Text(text = labelValue)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        singleLine = true,
        maxLines = 1,
        keyboardActions = KeyboardActions {
            focusManager.clearFocus()
        },
        value = password.value,
        onValueChange = {
            password.value = it
            onTextChanged(it)
        }
        ,

        leadingIcon = { Icon(imageVector = imageVector, contentDescription = "lock")},
        trailingIcon = {
            val iconImage = if(passwordVisible.value){
                Icons.Outlined.Visibility
            }else{
                Icons.Outlined.VisibilityOff
            }

            val description = if(passwordVisible.value){
                "Visible"
            }
            else{
                "Not Visible"
            }
            IconButton(onClick = {passwordVisible.value=!passwordVisible.value}) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun SignUpButton(value: String,onButtonClicked:() -> Unit,isEnabled:Boolean = false){
    Button(onClick = { onButtonClicked.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    color = darkGreenish,
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = value,
                fontSize = 18.sp,
                color = greenish,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ForgotPasswordText(){
    val textString = "Forgot Your Password?"
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = darkGreenish)){
            pushStringAnnotation(tag = textString, annotation = textString)
            append(textString)
        }
    }
    ClickableText(text = annotatedString, modifier = Modifier.fillMaxWidth(), style = TextStyle(
        textAlign = TextAlign.Center
    )
    ) {

    }
}

@Composable
fun DividerLines(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = darkGreenish,
            thickness = 1.dp
        )
        Text(
            text = "or",
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = darkGreenish,
            thickness = 1.dp
        )
    }
}

@Composable
fun ClickableLoginRegisterTextComponent(val1:String,val2:String,onTextSelected: (String) -> Unit){
    val intitalText = val1
    val loginText =  val2


    val annotatedString = buildAnnotatedString {
        append(intitalText)
        withStyle(style = SpanStyle(color = darkGreenish)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }
    ClickableText(
        text = annotatedString,
        onClick = { offset: Int? ->
            offset?.let { nonNullOffset ->
                annotatedString.getStringAnnotations(
                    nonNullOffset,
                    nonNullOffset
                ).firstOrNull()?.also { span ->
                    if(span.item==loginText) onTextSelected(span.item)
                }
            }
        },
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(textAlign = TextAlign.Center),

        maxLines = Int.MAX_VALUE // Or specify the desired max lines
    )
}

@Composable
fun ClickableTextComponent(onTextSelected: (String) -> Unit){
    val intitalString = "By continuing you accept our"
    val privacyPolicy = " Privacy Policy"
    val andText = " and"
    val tandC = " Terms and Conditions"

    val annotatedString = buildAnnotatedString {
        append(intitalString)
        withStyle(style = SpanStyle(color = darkGreenish)){
            pushStringAnnotation(tag = privacyPolicy, annotation = privacyPolicy)
            append(privacyPolicy)
        }
        append(andText)
        withStyle(style = SpanStyle(color = darkGreenish)){
            pushStringAnnotation(tag = tandC, annotation = tandC)
            append(tandC)
        }
    }
    ClickableText(
        text = annotatedString,
        onClick = { offset: Int? ->
            offset?.let { nonNullOffset ->
                annotatedString.getStringAnnotations(
                    nonNullOffset,
                    nonNullOffset
                ).firstOrNull()?.also { span ->
                    if(span.item==tandC) onTextSelected(span.item)
                }
            }
        },
        modifier = Modifier.fillMaxWidth(),


        maxLines = Int.MAX_VALUE // Or specify the desired max lines
    )
}

@Composable
fun CheckBoxComponent(onTextSelected: (String) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()

            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val checkBoxChecked = remember {
            mutableStateOf(false)
        }
        Checkbox(checked = checkBoxChecked.value, onCheckedChange = {
            checkBoxChecked.value = !checkBoxChecked.value
        }, colors = CheckboxDefaults.colors(checkedColor = darkGreenish))
        ClickableTextComponent(onTextSelected = onTextSelected)
    }
}