package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property segmentCount Total number of segments matching query
 * @property page Page number
 * @property segments
 */
@Serializable
public data class SearchSegments internal constructor(
    val segmentCount: Int,
    val page: Int,
    val segments: List<Segment>
) {
    @Serializable
    public data class Segment internal constructor(
        val startTime: Float,
        val endTime: Float,
        val votes: Int,
        val locked: Int,
        @SerialName("UUID")
        val uuid: String,
        @SerialName("userID")
        val userId: String,
        val description: String,
        val timeSubmitted: Long,
        val views: Int,
        val category: Category,
        val hidden: Int,
        val shadowHidden: Int,
        @SerialName("actionType")
        val action: Action
    )
}