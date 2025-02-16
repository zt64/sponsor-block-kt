package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Defines the action to be performed upon reaching a segment in a video.
 */
@Serializable
public enum class Action {
    /**
     * Skips the segment.
     */
    @SerialName("skip")
    SKIP,

    /**
     * Mutes the audio during the segment.
     */
    @SerialName("mute")
    MUTE,

    /**
     * Skips the entire video.
     */
    @SerialName("full")
    FULL,

    /**
     * Marks a point of interest (POI) within the video.
     */
    @SerialName("poi")
    POI,

    @SerialName("chapter")
    CHAPTER
}