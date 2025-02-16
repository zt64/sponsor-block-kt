package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A search result.
 *
 * @property username The name of the user.
 * @property userId The unique identifier of the user.
 */
@Serializable
public data class SearchResult internal constructor(
    @SerialName("userName")
    val username: String,
    @SerialName("userID")
    val userId: String
)