package com.ricardovalverde.droidchat.ui.feature.signUp

import android.net.Uri
import androidx.annotation.StringRes

data class SignUpFormState(
    val profilePictureUri: Uri? = null,

    val firstName: String = "",

    @field:StringRes
    val firstNameError: Int? = null,

    val lastName: String = "",

    @field:StringRes
    val lastNameError: Int? = null,

    val email: String = "",

    @field:StringRes
    val emailError: Int? = null,

    val password: String = "",

    @field:StringRes
    val passwordError: Int? = null,

    val passwordConfirmation: String = "",

    @field:StringRes
    val passwordConfirmationError: Int? = null,


    @field:StringRes
    val passwordMatchExtraText: Int? = null,

    val isProfilePictureModalBottomSheetOpen: Boolean = false,

    val hasError: Boolean = false,

    val isLoading: Boolean = false,

    )
