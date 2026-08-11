package com.ricardovalverde.droidchat.data.network

import com.ricardovalverde.droidchat.data.network.model.request.AuthRequest
import com.ricardovalverde.droidchat.data.network.model.request.CreateAccountRequest
import com.ricardovalverde.droidchat.data.network.model.response.TokenResponse

interface NetworkDataSource {
    suspend fun signUp(request: CreateAccountRequest)
    suspend fun signIn(request: AuthRequest): TokenResponse
}