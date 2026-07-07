package com.ricardovalverde.droidchat.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricardovalverde.droidchat.R
import com.ricardovalverde.droidchat.ui.extension.getVisualTransformationForPassword
import com.ricardovalverde.droidchat.ui.theme.DroidChatTheme

@Composable
fun SecondaryTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    extraText: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onValueChange: (String) -> Unit
) {

    //Estado interno para ver alteracoes no preview
    var inputText by remember { mutableStateOf(value) }
    var passwordVisibility by remember { mutableStateOf(false) }

    BasicTextField(
        modifier = Modifier.fillMaxWidth(),
        value = inputText,
        singleLine = true,
        maxLines = 1,
        visualTransformation = keyboardType.getVisualTransformationForPassword(passwordVisibility),
        keyboardOptions = KeyboardOptions(
            capitalization = if (keyboardType == KeyboardType.Text) {
                KeyboardCapitalization.Sentences
            } else KeyboardCapitalization.None,
            imeAction = imeAction
        ),
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7F)
        ),

        onValueChange = {
            inputText = it
            onValueChange(it)
        }
    ) { innerTextField ->
        Surface(color = MaterialTheme.colorScheme.surface) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(text = label, style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(15.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.weight(1f)) {
                            innerTextField()

                        }
                    }
                }
                if (keyboardType == KeyboardType.Password) {
                    val visibilityIcon = if (passwordVisibility) {
                        R.drawable.ic_visibility
                    } else {
                        R.drawable.ic_visibility_off
                    }

                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            painter = painterResource(visibilityIcon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SecondaryTextFieldEmailPreview(modifier: Modifier = Modifier) {
    DroidChatTheme {
        SecondaryTextField(
            label = "Email",
            value = "",
            extraText = "",
            keyboardType = KeyboardType.Email,
            onValueChange = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SecondaryTextFieldPasswordPreview(modifier: Modifier = Modifier) {
    DroidChatTheme {
        SecondaryTextField(
            label = "Senha",
            value = "",
            extraText = "Senha Inválido",
            keyboardType = KeyboardType.Password,
            onValueChange = {}
        )
    }
}