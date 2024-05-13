package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class Category {
    @SerialName("sponsor")
    SPONSOR,

    @SerialName("selfpromo")
    SELF_PROMO,

    @SerialName("interaction")
    INTERACTION,

    @SerialName("intro")
    INTRO,

    @SerialName("outro")
    OUTRO,

    @SerialName("chapter")
    CHAPTER,

    @SerialName("preview")
    PREVIEW,

    @SerialName("music_offtopic")
    MUSIC_OFF_TOPIC,

    @SerialName("filler")
    FILLER,

    @SerialName("poi_highlight")
    POI_HIGHLIGHT
}