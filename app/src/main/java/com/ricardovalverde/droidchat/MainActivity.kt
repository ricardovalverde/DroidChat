package com.ricardovalverde.droidchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import com.ricardovalverde.droidchat.ui.ChatApp
import com.ricardovalverde.droidchat.ui.theme.DroidChatTheme

class MainActivity :


ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                "#FF00BCCE".toColorInt()
            ),
            navigationBarStyle = SystemBarStyle.dark(
                "#FF000000".toColorInt()
            )
        )

        setContent {
            DroidChatTheme {
                ChatApp()
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DroidChatTheme {
        Greeting("Android")
    }
}