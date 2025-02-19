package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property userId Public userID
 * @property username Public userID if not set
 * @property minutesSaved Minutes saved
 * @property segmentCount Total number of segments excluding ignored/ hidden segments
 * @property ignoredSegmentCount Total number of ignored/ hidden segments
 * @property viewCount Total number of views excluding view on ignored/ hidden segments
 * @property ignoredViewCount Total number of view on ignored/ hidden segments
 * @property warnings Currently enabled warnings
 * @property reputation
 * @property vip VIP status
 * @property lastSegmentId UUID of last submitted segment
 */
@Serializable
public data class UserInfo(
    @SerialName("userID")
    val userId: String,
    @SerialName("userName")
    val username: String,
    val minutesSaved: Float,
    val segmentCount: Int,
    val ignoredSegmentCount: Int,
    val viewCount: Int,
    val ignoredViewCount: Int,
    val warnings: Int,
    val warningReason: String,
    val reputation: Float,
    val vip: Boolean,
    @SerialName("lastSegmentID")
    val lastSegmentId: String?
)