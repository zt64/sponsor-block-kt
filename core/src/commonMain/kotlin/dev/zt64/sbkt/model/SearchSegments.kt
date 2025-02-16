package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the results of a segment search query.
 *
 * @property segmentCount Total number of segments that match the query.
 * @property page The current page number of the results.
 * @property segments A list of segments that match the search criteria.
 */
@Serializable
public data class SearchSegments internal constructor(
    val segmentCount: Int,
    val page: Int,
    val segments: List<Segment>
) {
    /**
     * Represents an individual segment of a video.
     *
     * @property startTime The starting timestamp of the segment in seconds.
     * @property endTime The ending timestamp of the segment in seconds.
     * @property votes The number of votes the segment has received.
     * @property locked Indicates whether the segment is locked (1 for locked, 0 for unlocked).
     * @property uuid The unique identifier of the segment.
     * @property userId The ID of the user who submitted the segment.
     * @property description A textual description of the segment.
     * @property timeSubmitted The Unix timestamp of when the segment was submitted.
     * @property views The total number of times the segment has been viewed.
     * @property category The category of the segment, e.g., [Category.SPONSOR].
     * @property hidden Indicates whether the segment is hidden (1 for hidden, 0 for visible).
     * @property shadowHidden Indicates whether the segment is shadow-hidden (1 for hidden, 0 for visible).
     * @property action The action associated with the segment, such as [Action.SKIP] or [Action.MUTE].
     */
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