package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserStats internal constructor(
    @SerialName("userID")
    val userId: String,
    @SerialName("userName")
    val username: String,
    val overallStats: OverallStats,
    val categoryCount: CategoryCount? = null,
    val actionTypeCount: ActionTypeCount? = null
) {
    @Serializable
    public data class OverallStats internal constructor(
        val minutesSaved: Float,
        val segmentCount: Int
    )

    /**
     * Number of segments per category
     */
    @Serializable
    public data class CategoryCount internal constructor(
        val sponsor: Int,
        val intro: Int,
        val outro: Int,
        val interaction: Int,
        @SerialName("selfpromo")
        val selfPromo: Int,
        @SerialName("music_offtopic")
        val musicOffTopic: Int,
        val preview: Int,
        @SerialName("poi_highlight")
        val poiHighlight: Int,
        val filler: Int,
        @SerialName("exclusive_access")
        val exclusiveAccess: Int,
        val chapter: Int
    )

    /**
     * Number of segments per type
     */
    @Serializable
    public data class ActionTypeCount internal constructor(
        val skip: Int,
        val mute: Int,
        val full: Int,
        val poi: Int,
        val chapter: Int
    )
}