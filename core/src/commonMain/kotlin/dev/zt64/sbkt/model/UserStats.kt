package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * User statistics.
 *
 * @property userId Public ID of the user
 * @property username Username of the user
 * @property overallStats Overall statistics of the user
 * @property categoryCount Number of segments per category, only present if requested with `fetchCategoryStats`
 * @property actionTypeCount Number of segments per type, only present if requested with `fetchActionTypeStats`
 */
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
    /**
     * Overall statistics.
     *
     * @property minutesSaved Minutes saved by the user
     * @property segmentCount Number of segments
     */
    @Serializable
    public data class OverallStats internal constructor(
        val minutesSaved: Float,
        val segmentCount: Int
    )

    /**
     * Number of segments per category.
     *
     * @property sponsor Number of sponsor segments
     * @property intro Number of intro segments
     * @property outro Number of outro segments
     * @property interaction Number of interaction segments
     * @property selfPromo Number of self-promo segments
     * @property musicOffTopic Number of off-topic music segments
     * @property preview Number of preview segments
     * @property poiHighlight Number of point-of-interest highlight segments
     * @property filler Number of filler segments
     * @property exclusiveAccess Number of exclusive access segments
     * @property chapter Number of chapter segments
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
     * Number of segments per type.
     *
     * @property skip Number of skip segments
     * @property mute Number of mute segments
     * @property full Number of full segments
     * @property poi Number of point-of-interest segments
     * @property chapter Number of chapter segments
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