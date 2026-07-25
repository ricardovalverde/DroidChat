package com.ricardovalverde.droidchat.ui.validator

object PasswordValidator {
    fun isPasswordValid(password: String): Boolean {
        return password.length >= 8 && password.any { it.isDigit() } && password.any { it.isLetter() }
    }
}