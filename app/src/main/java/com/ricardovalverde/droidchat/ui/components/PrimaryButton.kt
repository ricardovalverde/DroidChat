package com.ricardovalverde.droidchat.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricardovalverde.droidchat.ui.theme.DroidChatTheme
import com.ricardovalverde.droidchat.ui.theme.RoundedCornerShapeButton

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    isLoading: Boolean = false

) {


    Button(
        modifier = modifier
            .height(54.dp),
        onClick = onClick,
        enabled = !isLoading,
        shape = if (!isLoading) RoundedCornerShapeButton else ButtonDefaults.shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Box(modifier = Modifier.animateContentSize()) {

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(30.dp)
                        .aspectRatio(1f),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeCap = StrokeCap.Round
                )
            } else {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = text
                )
            }
        }

    }

}


@Composable
@Preview
fun PrimaryButtonPreview() {
    DroidChatTheme {
        PrimaryButton(text = "Teste SignIn", onClick = {}, isLoading = false)
    }
}

@Composable
@Preview
fun PrimaryButtonPreviewLoading() {
    DroidChatTheme {
        PrimaryButton(text = "Teste SignIn", onClick = {}, isLoading = true)
    }
}