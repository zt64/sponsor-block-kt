package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property uuid
 * @property segment [0, 15.23] start and end time in seconds
 * @property category
 * @property videoDuration Duration of video when submission occurred (to be used to determine when a submission is out of date). 0 when unknown. +- 1 second
 * @property action If submission is locked
 * @property userId Public user ID of submitter
 * @property votes Votes on segment
 */
@Serializable
public data class Segment internal constructor(
    @SerialName("UUID")
    val uuid: String,
    val segment: List<Float>,
    val category: Category,
    val videoDuration: Float,
    @SerialName("actionType")
    val action: Action,
    @SerialName("userID")
    val userId: String,
    val locked: Int,
    val votes: Int,
    val description: String
)