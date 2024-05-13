package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property videoId
 * @property hashedVideoId
 * @property startTime
 * @property endTime
 * @property votes
 * @property locked Status of lock - If up-voted by a VIP, the segment is locked
 * @property incorrectVotes
 * @property uuid
 * @property userId Public ID of submitter
 * @property timeSubmitted
 * @property views Number of reported views on the segment
 * @property category
 * @property service
 * @property videoDuration
 * @property hidden If the segment has two down-votes or was down-voted by a VIP
 * @property reputation Reputation of submitter at time of submission
 * @property shadowHidden If the submitter is shadow-banned
 * @property userAgent User agent of the submitter
 * @property description
 * @property action
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