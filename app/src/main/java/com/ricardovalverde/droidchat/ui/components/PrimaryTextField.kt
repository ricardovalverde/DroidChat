package com.ricardovalverde.droidchat.ui.components

import android.R.attr.text
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricardovalverde.droidchat.R
import com.ricardovalverde.droidchat.ui.theme.DroidChatTheme
import com.ricardovalverde.droidchat.ui.theme.RoundedCornerShape
import org.w3c.dom.Text
import java.nio.file.WatchEvent

@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    errorMessage: String? = null,
    @DrawableRes leadingIcon: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    val passwordVisibilityIcon =
        if (passwordVisibility) R.drawable.ic_visibility else R.drawable.ic_visibility_off


    Column(modifier = modifier) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            value = value,
            shape = RoundedCornerShape,
            placeholder = { Text(text = placeholder) },
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            visualTransformation =
                if (!passwordVisibility && keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
            leadingIcon = {
                leadingIcon?.let {
                    Icon(
                        painter = painterResource(leadingIcon),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurface,

                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,

                disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),

                focusedContainerColor = MaterialTheme.colorScheme.surface,

                unfocusedContainerColor = MaterialTheme.colorScheme.surface,

                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,

                cursorColor = MaterialTheme.colorScheme.primary,

                focusedBorderColor = MaterialTheme.colorScheme.primary,

                unfocusedBorderColor = if (errorMessage != null) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
            ),
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
            }
        )


        errorMessage?.let {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Senha Obrigatória",
                color = MaterialTheme.colorScheme.error
            )
        }
    }


}


@Composable
@Preview
fun PrimaryTextFieldPreview() {
    DroidChatTheme {
        PrimaryTextField(
            placeholder = "Email",
            value = "",
            onValueChange = { },
            leadingIcon = R.drawable.ic_envelope,
            keyboardType = KeyboardType.Email
        )
    }
}