package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class Action {
    @SerialName("skip")
    SKIP,

    @SerialName("mute")
    MUTE,

    @SerialName("full")
    FULL,

    /**
     * Point of Interest
     */
    @SerialName("poi")
    POI,

    @SerialName("chapter")
    CHAPTER
}