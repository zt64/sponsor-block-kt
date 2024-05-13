package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property category Category of lock
 * @property locked Status of lock
 * @property reason Reason for lock
 * @property userId Public ID of locking VIP
 * @property username Username of locking VIP
 */
@Serializable
public data class LockReason internal constructor(
    val category: Category,
    val locked: Int,
    val reason: String,
    @SerialName("userID")
    val userId: String,
    @SerialName("userName")
    val username: String
)