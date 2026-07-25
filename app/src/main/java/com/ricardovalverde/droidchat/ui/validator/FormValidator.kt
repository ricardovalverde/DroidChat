package com.ricardovalverde.droidchat.ui.validator

interface FormValidator<T> {
    fun validate(formState: T): T
}