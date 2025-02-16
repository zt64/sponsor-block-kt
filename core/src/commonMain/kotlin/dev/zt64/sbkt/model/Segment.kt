package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A segment
 *
 * @property uuid Unique identifier for the segment
 * @property segment List containing start and end time in seconds
 * @property category Category of the segment
 * @property videoDuration Duration of the video when the submission occurred, used to determine if a submission is out of date. 0 when unknown, with a tolerance of +- 1 second
 * @property action Action type of the segment
 * @property locked Indicates if the submission is locked (1 for locked, 0 for unlocked)
 * @property votes Number of votes on the segment
 * @property description Description of the segment
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
    val locked: Int,
    val votes: Int,
    val description: String
)