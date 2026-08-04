package com.ricardovalverde.droidchat.ui.feature.signIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ricardovalverde.droidchat.ui.validator.FormValidator

class SignInViewModel(
    private val formValidator: FormValidator<SignInFormState>
) : ViewModel() {

    var formState by mutableStateOf(SignInFormState())
        private set


    fun onFormEvent(event: SignInFormEvent) {
        when (event) {
            is SignInFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email, emailError = null)

            }

            is SignInFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password, passwordError = null)
            }

            SignInFormEvent.Submit -> {
                doSignIn()
            }
        }
    }

    private fun doSignIn() {
        if (isFormValid()) {
            formState = formState.copy(isLoading = true)
            //Request API
        }
    }

    private fun isFormValid(): Boolean {
        return !formValidator.validate(formState).also {
            formState = it
        }.hasError
    }
}