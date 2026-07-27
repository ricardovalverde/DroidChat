package com.ricardovalverde.droidchat.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.ricardovalverde.droidchat.R
import com.ricardovalverde.droidchat.ui.extension.getVisualTransformationForPassword
import com.ricardovalverde.droidchat.ui.theme.DroidChatTheme
import com.ricardovalverde.droidchat.ui.theme.RoundedCornerShapeTextField

@Composable
fun SecondaryTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    extraText: String? = null,
    errorText: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onValueChange: (String) -> Unit
) {

    //Estado interno para ver alteracoes no preview
    var inputText by remember { mutableStateOf(value) }
    var passwordVisibility by remember { mutableStateOf(false) }
    val supportingTextColor =
        if (extraText != null) Color.Green.copy(green = 30f) else
            if (errorText != null) Color.Red else Color.Unspecified

    val passwordVisibilityIcon =
        if (passwordVisibility) R.drawable.ic_visibility else R.drawable.ic_visibility_off

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = inputText,
        shape = RoundedCornerShapeTextField,
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = label) },
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
        supportingText = {
            Text(
                text = extraText ?: (errorText ?: ""),
                color = supportingTextColor
            )
        },
        trailingIcon = {
            if (keyboardType == KeyboardType.Password) {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = painterResource(passwordVisibilityIcon),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                }

            }
        },

        onValueChange = {
            inputText = it
            onValueChange(it)
        }
    )
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