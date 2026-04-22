package com.ricardovalverde.droidchat.ui.feature.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ricardovalverde.droidchat.R
import com.ricardovalverde.droidchat.ui.theme.BackgroundGradient
import com.ricardovalverde.droidchat.ui.theme.DroidChatTheme

@Composable
fun SignInRoute() {
    SignInScreen()
}

@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = BackgroundGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.logo), contentDescription = null)

    }


}

@Composable
@Preview
fun SignInScreenPreview() {
    DroidChatTheme {
        SignInScreen()
    }
}