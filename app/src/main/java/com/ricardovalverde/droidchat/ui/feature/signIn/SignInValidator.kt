package com.ricardovalverde.droidchat.ui.feature.signIn

import com.ricardovalverde.droidchat.R
import com.ricardovalverde.droidchat.ui.validator.EmailValidator
import com.ricardovalverde.droidchat.ui.validator.FormValidator

class SignInValidator : FormValidator<SignInFormState> {
    override fun validate(formState: SignInFormState): SignInFormState {
        val isEmailBlank = formState.email.isBlank()
        val isEmailValid = EmailValidator.isEmailValid(formState.email)
        val isPasswordValid = formState.password.isBlank()

        val hasError = listOf(
            isEmailValid,
            isPasswordValid,
            isEmailBlank,
        ).any { !it }

        return formState.copy(
            emailError = if (isEmailBlank) R.string.error_message_field_email_blank else if (!isEmailValid) R.string.error_message_email_invalid else null,
            passwordError = if (isPasswordValid) R.string.error_message_field_password_blank else null,
            hasError = hasError,
        )
    }
}