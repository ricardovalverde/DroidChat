package com.ricardovalverde.droidchat.ui.feature.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ricardovalverde.droidchat.R
import com.ricardovalverde.droidchat.ui.components.PrimaryButtonScreen
import com.ricardovalverde.droidchat.ui.components.PrimaryTextField
import com.ricardovalverde.droidchat.ui.theme.BackgroundGradient
import com.ricardovalverde.droidchat.ui.theme.DroidChatTheme

@Composable
fun SignInRoute(
    viewModel: SignInViewModel = viewModel()
) {
    val formState = viewModel.formState

    SignInScreen(
        formState = formState,
        onFormEvent = viewModel::onFormEvent
    )
}

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    formState: SignInFormState,
    onFormEvent: (SignInFormEvent) -> Unit
) {


    Column(
        modifier = Modifier
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
            leadingIcon = R.drawable.ic_envelope,
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

        PrimaryButtonScreen(
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.spacing_medium)),
            text = stringResource(R.string.feature_login_button),
            isLoading = formState.isLoading,
            onClick = {
                onFormEvent(SignInFormEvent.Submit)
            },
        )
    }
}

@Composable
@Preview
fun SignInScreenPreview() {
    DroidChatTheme {
        SignInScreen(
            formState = SignInFormState(),
            onFormEvent = {}
        )
    }
}