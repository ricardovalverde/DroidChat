package com.ricardovalverde.droidchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.graphics.toColorInt
import com.ricardovalverde.droidchat.ui.ChatApp
import com.ricardovalverde.droidchat.ui.theme.DroidChatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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
