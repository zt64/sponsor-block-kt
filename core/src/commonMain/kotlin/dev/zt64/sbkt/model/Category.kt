package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A category assigned to a segment in a video.
 */
@Serializable
public enum class Category {
    /**
     * A segment where a sponsor message is shown.
     */
    @SerialName("sponsor")
    SPONSOR,

    /**
     * A segment where the content creator promotes their own content or products.
     */
    @SerialName("selfpromo")
    SELF_PROMO,

    /**
     * A segment where there is interaction with the audience, such as Q&A or comments.
     */
    @SerialName("interaction")
    INTERACTION,

    /**
     * The introduction segment of the video.
     */
    @SerialName("intro")
    INTRO,

    /**
     * The ending segment of the video.
     */
    @SerialName("outro")
    OUTRO,

    /**
     * A segment that is a chapter or section of the video.
     */
    @SerialName("chapter")
    CHAPTER,

    /**
     * A preview segment of the video.
     */
    @SerialName("preview")
    PREVIEW,

    /**
     * A segment where off-topic music is played.
     */
    @SerialName("music_offtopic")
    MUSIC_OFF_TOPIC,

    /**
     * A filler segment that does not add significant content to the video.
     */
    @SerialName("filler")
    FILLER,

    /**
     * A segment that highlights a point of interest in the video.
     */
    @SerialName("poi_highlight")
    POI_HIGHLIGHT
}