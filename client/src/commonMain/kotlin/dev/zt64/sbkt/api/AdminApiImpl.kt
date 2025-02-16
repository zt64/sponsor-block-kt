package dev.zt64.sbkt.api

import io.ktor.client.*

internal class AdminApiImpl(private val client: HttpClient, override val userId: String) :
    AdminApi,
    VipApi by VipApiImpl(client, userId) {
    override fun enableUserVip(userId: String) {
        TODO("Not yet implemented")
    }

    override fun disableUserVip(userId: String) {
        TODO("Not yet implemented")
    }

    override fun setUsername(userId: String, username: String) {
        TODO("Not yet implemented")
    }
}