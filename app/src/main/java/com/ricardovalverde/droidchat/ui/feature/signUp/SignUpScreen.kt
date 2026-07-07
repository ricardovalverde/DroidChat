package com.ricardovalverde.droidchat.ui.feature.signUp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ricardovalverde.droidchat.ui.theme.DroidChatTheme

@Composable
fun SignUpRoute() {
    SignUpScreen()
}

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {

}

@Preview
@Composable
private fun SignUp() {
    DroidChatTheme {
        SignUpScreen()
    }
}