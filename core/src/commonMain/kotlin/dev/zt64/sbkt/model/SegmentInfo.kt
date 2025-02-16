package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents information about a segment in a video.
 *
 * @property videoId The unique identifier of the video.
 * @property hashedVideoId A hashed version of the video ID for obfuscation.
 * @property startTime The start time of the segment in seconds.
 * @property endTime The end time of the segment in seconds.
 * @property votes The number of total votes received for the segment.
 * @property locked Indicates if the segment is locked (1 if upvoted by a VIP, 0 otherwise).
 * @property incorrectVotes The number of incorrect votes (i.e., votes marking the segment as incorrect).
 * @property uuid The unique identifier of the segment.
 * @property userId The public ID of the submitter.
 * @property timeSubmitted The timestamp (epoch time in seconds) when the segment was submitted.
 * @property views The number of reported views for this segment.
 * @property category The category assigned to the segment, e.g., [Category.SPONSOR].
 * @property service The platform where the video is hosted, e.g., [Service.YOUTUBE].
 * @property videoDuration The total duration of the video in seconds.
 * @property hidden Indicates whether the segment is hidden (1 if it has two downvotes or was downvoted by a VIP, 0 otherwise).
 * @property reputation The reputation score of the submitter at the time of submission.
 * @property shadowHidden Indicates if the submitter is shadow-banned (1 if shadow-banned, 0 otherwise).
 * @property userAgent The user agent of the client that submitted the segment.
 * @property description A textual description or comment about the segment.
 * @property action The action to be performed when reaching this segment, e.g., [Action.SKIP].
 */
@Serializable
public data class SegmentInfo internal constructor(
    @SerialName("videoID")
    val videoId: String,
    @SerialName("hashedVideoID")
    val hashedVideoId: String,
    val startTime: Float,
    val endTime: Float,
    val votes: Int,
    val locked: Int,
    val incorrectVotes: Int,
    @SerialName("UUID")
    val uuid: String,
    @SerialName("userID")
    val userId: String,
    val timeSubmitted: Long,
    val views: Int,
    val category: Category,
    val service: Service,
    val videoDuration: Int,
    val hidden: Int,
    val reputation: Int,
    val shadowHidden: Int,
    val userAgent: String,
    val description: String,
    @SerialName("actionType")
    val action: Action
)