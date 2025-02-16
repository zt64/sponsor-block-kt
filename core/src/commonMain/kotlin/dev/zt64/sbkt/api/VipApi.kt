package dev.zt64.sbkt.api

import dev.zt64.sbkt.model.*

/**
 * API for VIP users.
 */
public interface VipApi : UserApi {
    /**
     * Create a category lock on the video, disallowing further submissions for that category
     *
     * @param videoId
     * @param userId
     */
    public fun createCategoryLock(
        videoId: String,
        categories: List<Category>,
        actionTypes: List<Action>,
        reason: String
    )

    /**
     * Delete a category lock on the video
     *
     * @param videoId
     * @param categories
     */
    public fun deleteCategoryLock(videoId: String, categories: List<Category>)

    /**
     * Shadow-ban a user
     *
     * @param userId
     * @param enabled
     * @param unHideOldSubmissions
     * @param categories
     */
    public fun shadowBanUser(
        userId: String,
        enabled: Boolean,
        unHideOldSubmissions: Boolean?,
        categories: List<Category>?
    )

    /**
     * Un-shadow-ban a user
     *
     * @param userId
     */
    public fun shadowUnbanUser(userId: String)

    /**
     * Warn a user
     *
     * @param userId Public user ID of the user to warn
     * @param reason The reason for the warning
     */
    public fun warnUser(userId: String, reason: String? = null)

    /**
     * Remove a warning from a user
     *
     * @param userId Public user ID of the user to remove the warning from
     */
    public fun removeWarn(userId: String)

    /**
     * Clear the cache for a specific video
     *
     * @param userId The local user ID of the VIP user
     * @param videoId The video ID to clear the cache for
     */
    public fun clearCache(userId: String, videoId: String)

    /**
     * Purge all segments from the cache
     *
     * @param videoId The video ID to purge all segments for
     */
    public fun purgeAllSegments(videoId: String, service: Service)

    /**
     * Shift all segments on a video
     */
    public fun segmentShift(videoId: String, startTime: Float, endTime: Float)

    public fun addUserAsTempVIP(userId: String, channelVideoId: String)

    public fun removeUserAsTempVIP(userId: String, channelVideoId: String)

    /**
     * Enable a feature for a user
     *
     * @param userId the user for whom to enable the feature for
     * @param feature the feature to enable
     */
    public fun enableUserFeature(userId: String, feature: Feature)

    /**
     * Disable a feature for a user
     *
     * @param userId the user for whom to disable the feature for
     * @param feature the feature to enable
     */
    public fun disableUserFeature(userId: String, feature: Feature)
}