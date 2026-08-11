package com.ricardovalverde.droidchat.ui.feature.signIn

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ricardovalverde.droidchat.R
import com.ricardovalverde.droidchat.ui.components.PrimaryButton
import com.ricardovalverde.droidchat.ui.components.PrimaryTextField
import com.ricardovalverde.droidchat.ui.theme.BackgroundGradient
import com.ricardovalverde.droidchat.ui.theme.DroidChatTheme
import com.ricardovalverde.droidchat.ui.theme.RoundedCornerShapeButton

@Composable
fun SignInRoute(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToSignUp: () -> Unit
) {
    val formState = viewModel.formState

    SignInScreen(
        formState = formState,
        onFormEvent = viewModel::onFormEvent,
        onRegisterClick = navigateToSignUp
    )
}

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    formState: SignInFormState,
    onFormEvent: (SignInFormEvent) -> Unit,
    onRegisterClick: () -> Unit
) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(brush = BackgroundGradient)
            .imePadding()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.logo), contentDescription = null)

        Spacer(modifier = Modifier.height(78.dp))

        PrimaryTextField(
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.spacing_medium)),
            value = formState.email,
            placeholder = stringResource(R.string.feature_login_email),
            leadingIcon = R.drawable.ic_email,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            errorMessage = formState.emailError?.let {
                stringResource(it)
            },
            onValueChange = {
                onFormEvent(SignInFormEvent.EmailChanged(it))
            }
        )

        Spacer(modifier = Modifier.height(14.dp))

        PrimaryTextField(
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.spacing_medium)),
            value = formState.password,
            placeholder = stringResource(R.string.feature_sign_up_password),
            leadingIcon = R.drawable.ic_lock,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            errorMessage = formState.passwordError?.let {
                stringResource(it)
            },
            onValueChange = {
                onFormEvent(SignInFormEvent.PasswordChanged(it))
            }
        )

        Spacer(modifier = Modifier.height(98.dp))

        PrimaryButton(
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.spacing_medium)),
            text = stringResource(R.string.feature_login_button),
            isLoading = formState.isLoading,
            onClick = {
                onFormEvent(SignInFormEvent.Submit)
            },
        )
        Spacer(modifier = Modifier.height(20.dp))

        val noAccountText = stringResource(R.string.feature_login_no_account)
        val registerText = stringResource(R.string.feature_login_register)
        val noAccountRegisterText = "$noAccountText $registerText"

        val annotatedString = buildAnnotatedString {
            val registerTextStartIndex = noAccountRegisterText.indexOf(registerText)
            val registerTextEndIndex = registerTextStartIndex + registerText.length

            append(noAccountRegisterText)

            addStyle(
                style = SpanStyle(color = Color.White),
                start = 0,
                end = registerTextStartIndex
            )

            addStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                ), start = registerTextStartIndex, end = registerTextEndIndex

            )
        }

        Button(
            modifier = Modifier
                .height(54.dp)
                .padding(horizontal = dimensionResource(R.dimen.spacing_medium)),
            onClick = onRegisterClick,
            border = BorderStroke(width = 1.dp, color = Color.White),
            shape = RoundedCornerShapeButton,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,

                )
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = annotatedString
            )
        }


    }
}

@Composable
@Preview
fun SignInScreenPreview() {
    DroidChatTheme {
        SignInScreen(
            formState = SignInFormState(),
            onFormEvent = {},
            onRegisterClick = {}
        )
    }
}