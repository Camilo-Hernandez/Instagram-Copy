package com.camihruiz24.instagramcopy.feature_login.presentation

import android.app.Activity
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.camihruiz24.instagramcopy.R

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {

    val isLoading : Boolean by loginViewModel.isLoading.observeAsState(initial = false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (isLoading){

            Box {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            Header(Modifier.align(Alignment.TopEnd))
            Body(Modifier.align(Alignment.Center), loginViewModel)
            Footer(Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        SignUp()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SignUp() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "Don't have an account?", fontSize = 12.sp, color = Color(0xffb5b5b5))
        Text(
            text = "Sign Up.",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9),
        )
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
fun Body(modifier: Modifier, loginViewModel: LoginViewModel) {

    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable: Boolean by loginViewModel.isLoginEnabled.observeAsState(initial = false)

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        ImageLogo()
        Spacer(modifier = Modifier.height(16.dp))
        EmailTextField(email) {
            loginViewModel.onLoginChanged(it, password)
        }
        Spacer(modifier = Modifier.size(8.dp))
        PasswordTextField(password) {
            loginViewModel.onLoginChanged(email, it)
        }
        Spacer(Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton(isLoginEnable, modifier.fillMaxWidth()) {
            loginViewModel.onLoginButtonPressed()
        }
        Spacer(modifier = Modifier.height(24.dp))
        LoginDivider()
        Spacer(modifier = Modifier.height(32.dp))
        SocialLogin()
    }
}

@Composable
fun LoginButton(loginEnable: Boolean, modifier: Modifier, onLoginButtonPressed: () -> Unit) {
    Button(
        onClick = onLoginButtonPressed,
        enabled = loginEnable,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff4ea8e9),
            disabledContainerColor = Color(0xff78c8f9),
            contentColor = Color.White,
            disabledContentColor = Color.White,
        )
    ) {
        Text(text = "Log in")
    }

}


@Preview(showBackground = true)
@Composable
fun SocialLogin() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Accede con Facebook",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Continúa como Camilo",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color(0xff4ea8e9)
        )
    }
}

@Composable
fun LoginDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR",
            Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xffb5b5b5)
        )
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
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
    Image(
        painter = painterResource(id = R.drawable.instagram_logo),
        contentDescription = "Logo de Instagram"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Phone number, username or email")
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF363636),
            containerColor = Color(0xFFEEEEEE),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            ),

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(password: String, onTextChanged: (String) -> Unit) {

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    TextField(
        value = password,

        onValueChange = onTextChanged,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Password")
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF363636),
            containerColor = Color(0xFFEEEEEE),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        visualTransformation = if(isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val passwordVisibilityIcon = if (isPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible}) {
                Icon(imageVector = passwordVisibilityIcon, contentDescription = "Esconder o mostrar contraseña")
            }
        }
        )
}