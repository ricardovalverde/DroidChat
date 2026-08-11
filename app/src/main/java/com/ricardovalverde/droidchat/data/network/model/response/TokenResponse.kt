package com.ricardovalverde.droidchat.data.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val token: String,
)