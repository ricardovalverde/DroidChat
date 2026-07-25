package com.ricardovalverde.droidchat.ui.feature.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ricardovalverde.droidchat.R
import com.ricardovalverde.droidchat.ui.components.PrimaryButton
import com.ricardovalverde.droidchat.ui.components.ProfilePictureOptionModalBottomSheet
import com.ricardovalverde.droidchat.ui.components.ProfilePictureSelector
import com.ricardovalverde.droidchat.ui.components.SecondaryTextField
import com.ricardovalverde.droidchat.ui.theme.BackgroundGradient
import com.ricardovalverde.droidchat.ui.theme.DroidChatTheme
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute(
    viewModel: SignUpViewModel = viewModel {
        SignUpViewModel(formValidator = SignUpFormValidator())
    }
) {
    val formState = viewModel.formState
    SignUpScreen(
        formState = formState,
        onFormEvent = viewModel::onFormEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    formState: SignUpFormState,
    onFormEvent: (SignUpFormEvent) -> Unit
) {

    val sheetState = rememberModalBottomSheetState()

    val scope = rememberCoroutineScope()


    Box(
        modifier = modifier
            .background(brush = BackgroundGradient)
            .verticalScroll(state = rememberScrollState())
            .imePadding()
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {


            Spacer(Modifier.height(56.dp))

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null
            )

            Spacer(Modifier.height(16.dp))

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomEnd = CornerSize(0.dp),
                    bottomStart = CornerSize(0.dp)
                )
            ) {

                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    ProfilePictureSelector(
                        modifier = Modifier
                            .clickable {
                                onFormEvent(SignUpFormEvent.OpenProfilePictureModalBottomSheet)
                            },
                        imageUri = formState.profilePictureUri
                    )

                    Spacer(Modifier.height(30.dp))

                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_first_name),
                        value = formState.firstName,
                        errorText = formState.firstNameError?.let {
                            stringResource(
                                it,
                                stringResource(R.string.feature_sign_up_first_name)
                            )
                        },
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.FirstNameChanged(it))
                        }
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_last_name),
                        value = formState.lastName,
                        errorText = formState.lastNameError?.let {
                            stringResource(
                                it,
                                stringResource(R.string.feature_sign_up_last_name)
                            )
                        },
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.LastnameChanged(it))
                        }
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        label = stringResource(R.string.feature_login_email),
                        value = formState.email,
                        errorText = formState.emailError?.let { stringResource(it) },
                        keyboardType = KeyboardType.Email,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.EmailChanged(it))
                        }
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_password),
                        value = formState.password,
                        extraText = formState.passwordMatchExtraText?.let { stringResource(it) },
                        errorText = formState.passwordError?.let { stringResource(it) },
                        keyboardType = KeyboardType.Password,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.PasswordChanged(it))
                        }
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_password_confirmation),
                        value = formState.passwordConfirmation,
                        extraText = formState.passwordMatchExtraText?.let { stringResource(it) },
                        errorText = formState.passwordConfirmationError?.let { stringResource(it) },
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.PasswordConfirmationChanged(it))
                        }
                    )

                    Spacer(Modifier.height(36.dp))

                    PrimaryButton(
                        text = stringResource(R.string.feature_sign_up_button),
                        onClick = { onFormEvent(SignUpFormEvent.Submit) }
                    )
                }
            }

            if (formState.isProfilePictureModalBottomSheetOpen) {
                ProfilePictureOptionModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = { onFormEvent(SignUpFormEvent.CloseProfilePictureModalBottomSheet) },
                    onPictureSelected = {
                        onFormEvent(SignUpFormEvent.ProfilePhotoUriChanged(it))

                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onFormEvent(SignUpFormEvent.CloseProfilePictureModalBottomSheet)
                            }
                        }
                    }
                )
            }
        }
    }
}


@Preview
@Composable
private fun SignUp() {
    DroidChatTheme {
        SignUpScreen(formState = SignUpFormState(), onFormEvent = {})
    }
}