package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SearchResult internal constructor(
    @SerialName("userName")
    val username: String,
    @SerialName("userID")
    val userId: String
)