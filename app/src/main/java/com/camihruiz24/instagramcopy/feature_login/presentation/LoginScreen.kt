package com.camihruiz24.instagramcopy.feature_login.presentation

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.camihruiz24.instagramcopy.R

@Composable
fun LoginScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center))
    }
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Cerrar aplicación",
        modifier = modifier.clickable { activity.finish() }
    )
}

@Composable
fun Body(modifier: Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Column(modifier = modifier) {
        ImageLogo()
        Spacer(modifier = Modifier.height(16.dp))
        EmailTextField(email) { email = it }
        Spacer(modifier = Modifier.size(8.dp))
        PasswordTextField(password) { password = it }
        Spacer(Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
    }
}


@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.ExtraBold,
        color = Color(0xFF4EA8E9),
        modifier = modifier,
    )
}

@Composable
fun ImageLogo() {
    Image(painter = painterResource(id = R.drawable.instagram_logo), contentDescription = "Logo de Instagram")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(email: String, onTextChanged: (String) -> Unit) {
    TextField(value = email, onValueChange = {
        onTextChanged(it)
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(password: String, onTextChanged: (String) -> Unit) {
    TextField(value = password, onValueChange = onTextChanged)
}