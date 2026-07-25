package com.ricardovalverde.droidchat.ui.feature.signUp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ricardovalverde.droidchat.R
import com.ricardovalverde.droidchat.ui.validator.FormValidator

class SignUpViewModel(
    private val formValidator: FormValidator<SignUpFormState>
) : ViewModel() {

    var formState by mutableStateOf(SignUpFormState())
        private set

    fun onFormEvent(event: SignUpFormEvent) {
        when (event) {
            is SignUpFormEvent.ProfilePhotoUriChanged -> {
                formState = formState.copy(profilePictureUri = event.uri)
            }

            is SignUpFormEvent.FirstNameChanged -> {
                formState = formState.copy(firstName = event.firstName)
            }

            is SignUpFormEvent.LastnameChanged -> {
                formState = formState.copy(lastName = event.lastName)
            }

            is SignUpFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }

            is SignUpFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
                updatePasswordMatchExtraText()
            }

            is SignUpFormEvent.PasswordConfirmationChanged -> {
                formState = formState.copy(passwordConfirmation = event.confirmationPassword)
                updatePasswordMatchExtraText()
            }


            SignUpFormEvent.OpenProfilePictureModalBottomSheet -> {
                formState = formState.copy(isProfilePictureModalBottomSheetOpen = true)
            }

            SignUpFormEvent.CloseProfilePictureModalBottomSheet -> {
                formState = formState.copy(isProfilePictureModalBottomSheetOpen = false)
            }

            SignUpFormEvent.Submit -> {
                doSignUp()
            }
        }
    }

    private fun updatePasswordMatchExtraText() {
        formState = formState.copy(
            passwordMatchExtraText = if (formState.password.isNotEmpty() && formState.password == formState.passwordConfirmation) {
                R.string.feature_sign_up_passwords_match
            } else null
        )
    }


    private fun doSignUp() {
        if (isValidForm()) {
            formState = formState.copy(isLoading = true)
            //Request API
        }
    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(formState).also {
            formState = it
        }.hasError
    }
}

