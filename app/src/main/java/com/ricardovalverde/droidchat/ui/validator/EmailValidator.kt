package com.ricardovalverde.droidchat.ui.validator

object EmailValidator {
    private val EMAIL_REGEX = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")

    fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.matches(email)
    }
}
