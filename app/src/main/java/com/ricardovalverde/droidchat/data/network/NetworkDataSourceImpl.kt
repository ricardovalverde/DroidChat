package com.ricardovalverde.droidchat.data.network

import com.ricardovalverde.droidchat.data.network.model.request.AuthRequest
import com.ricardovalverde.droidchat.data.network.model.request.CreateAccountRequest
import com.ricardovalverde.droidchat.data.network.model.response.TokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) :
    NetworkDataSource {
    override suspend fun signUp(request: CreateAccountRequest) {
        httpClient.post("signup") {
            setBody(request)
        }.body<Unit>()
    }

    override suspend fun signIn(request: AuthRequest): TokenResponse {
        return httpClient.post("signin") {
            setBody(request)
        }.body()
    }
}