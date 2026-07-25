package com.ricardovalverde.droidchat.ui.feature.signUp

import android.net.Uri

sealed interface SignUpFormEvent {
    data class ProfilePhotoUriChanged(val uri: Uri?) : SignUpFormEvent
    data class FirstNameChanged(val firstName: String) : SignUpFormEvent
    data class LastnameChanged(val lastName: String) : SignUpFormEvent
    data class EmailChanged(val email: String) : SignUpFormEvent
    data class PasswordChanged(val password: String) : SignUpFormEvent
    data class PasswordConfirmationChanged(val confirmationPassword: String) : SignUpFormEvent


    data object OpenProfilePictureModalBottomSheet : SignUpFormEvent
    data object CloseProfilePictureModalBottomSheet : SignUpFormEvent
    data object Submit : SignUpFormEvent
}

