package com.ricardovalverde.droidchat.ui.feature.signUp

import com.ricardovalverde.droidchat.R
import com.ricardovalverde.droidchat.ui.validator.EmailValidator
import com.ricardovalverde.droidchat.ui.validator.FormValidator
import com.ricardovalverde.droidchat.ui.validator.PasswordValidator

class SignUpFormValidator : FormValidator<SignUpFormState> {
    override fun validate(formState: SignUpFormState): SignUpFormState {

        val isFirstNameValid = formState.firstName.isNotEmpty()
        val isLastNameValid = formState.lastName.isNotEmpty()
        val isEmailValid = EmailValidator.isEmailValid(formState.email)
        val isPasswordValid = PasswordValidator.isPasswordValid(formState.password)
        val isPasswordConfirmationValid =
            PasswordValidator.isPasswordValid(formState.passwordConfirmation)
                    && formState.passwordConfirmation == formState.password

        val hasError = listOf(
            isFirstNameValid,
            isLastNameValid,
            isEmailValid,
            isPasswordValid,
            isPasswordConfirmationValid,
        ).any { !it }

        return formState.copy(
            firstNameError = if (!isFirstNameValid) R.string.error_message_field_blank else null,
            lastNameError = if (!isLastNameValid) R.string.error_message_field_blank else null,
            emailError = if (!isEmailValid) R.string.error_message_email_invalid else null,
            passwordError = if (!isPasswordValid) R.string.error_message_password_invalid else null,
            passwordConfirmationError = if (!isPasswordConfirmationValid) R.string.error_message_password_confirmation_invalid else null,
            hasError = hasError,
        )
    }
}