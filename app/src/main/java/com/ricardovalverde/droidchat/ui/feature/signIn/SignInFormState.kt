package com.ricardovalverde.droidchat.ui.feature.signIn

import androidx.annotation.StringRes


data class SignInFormState(
    val email: String = "",
    @field:StringRes val emailError: Int? = null,
    val password: String = "",
    @field:StringRes val passwordError: Int? = null,
    val isLoading: Boolean = false
)