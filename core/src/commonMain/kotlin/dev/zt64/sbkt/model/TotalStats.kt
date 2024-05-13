package dev.zt64.sbkt.model

import kotlinx.serialization.Serializable

/**
 * @property userCount Only if countContributingUsers was true
 * @property activeUsers Sum of public install stats from Chrome web-store and Firefox addons store
 * @property apiUsers 48-hour active API users
 * @property viewCount
 * @property totalSubmissions
 * @property minutesSaved
 */
@Serializable
public data class TotalStats internal constructor(
    val userCount: Int = 0,
    val activeUsers: Int,
    val apiUsers: Int,
    val viewCount: Int,
    val totalSubmissions: Int,
    val minutesSaved: Float
)