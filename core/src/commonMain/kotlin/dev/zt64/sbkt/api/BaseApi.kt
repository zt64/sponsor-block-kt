package dev.zt64.sbkt.api

import dev.zt64.sbkt.model.*
import kotlin.time.Duration

public interface BaseApi {
    /**
     * Get segments for a video
     *
     * @param videoId
     * @param action
     * @param service
     * @return A list of segments
     */
    public suspend fun getSegments(
        videoId: String,
        category: Category = Category.SPONSOR,
        action: Action = Action.SKIP,
        service: Service = Service.YOUTUBE
    ): List<Segment>

    /**
     * Get segments for a video
     *
     * @param videoId
     * @param categories
     * @param action
     * @param service
     * @return
     */
    public suspend fun getSegments(
        videoId: String,
        categories: List<Category>,
        action: Action = Action.SKIP,
        service: Service = Service.YOUTUBE
    ): List<Segment>

    /**
     * Get required segments for a video
     *
     * @param videoId
     * @param category
     * @param segments List of segment UUIDs to require to be retrieved, even if they don't meet the minimum vote threshold
     * @param action
     * @param service
     * @return
     */
    public suspend fun getRequiredSegments(
        videoId: String,
        category: Category = Category.SPONSOR,
        segments: List<String>,
        action: Action = Action.SKIP,
        service: Service = Service.YOUTUBE
    ): List<Segment>

    /**
     * Get required segments for a video
     *
     * @param videoId
     * @param categories
     * @param segments List of segment UUIDs to require to be retrieved, even if they don't meet the minimum vote threshold
     * @param action
     * @param service
     * @return
     */
    public suspend fun getRequiredSegments(
        videoId: String,
        categories: List<Category> = listOf(Category.SPONSOR),
        segments: List<String>,
        action: Action = Action.SKIP,
        service: Service = Service.YOUTUBE
    ): List<Segment>

    /**
     * Create a segment on a video
     *
     * @param videoId
     * @param startTime
     * @param endTime
     * @param category
     * @param userId This should be a randomly generated UUID stored locally (not the public one)
     * @param userAgent "Name of Client/Version" or "[BOT] Name of Bot/Version" ex. "Chromium/1.0.0"
     * @param service
     * @param videoDuration Duration of video, will attempt to retrieve from the YouTube API if missing (to be used to determine when a submission is out of date)
     * @param action
     */
    public suspend fun createSegment(
        videoId: String,
        startTime: Duration,
        endTime: Duration,
        category: Category,
        userId: String,
        userAgent: String = "SponsorBlockKT/",
        service: Service = Service.YOUTUBE,
        videoDuration: Duration? = null,
        action: Action = Action.SKIP
    )

    /**
     * Vote on a segment
     *
     * @param uuid
     * @param userId
     * @param type
     */
    public suspend fun vote(
        uuid: String,
        userId: String,
        type: Vote
    )

    /**
     * Vote to change the category of a segment
     *
     * @param uuid
     * @param userId
     * @param category
     */
    public suspend fun vote(
        uuid: String,
        userId: String,
        category: Category
    )

    /**
     * Add view to segment
     *
     * @param uuid
     */
    public suspend fun viewSegment(uuid: String)

    /**
     * Get information about a user
     *
     * @param userId
     */
    public suspend fun getUserInfo(userId: String): UserInfo

    /**
     * Get information about a user
     *
     * @param userId
     */
    public suspend fun getPublicUserInfo(userId: String): UserInfo

    /**
     * Get stats for a user
     *
     * @param userId
     * @param fetchCategoryStats
     * @param fetchActionTypeStats
     */
    public suspend fun getUserStats(
        userId: String,
        fetchCategoryStats: Boolean = false,
        fetchActionTypeStats: Boolean = false
    ): UserStats

    /**
     * Get stats for a user
     *
     * @param userId
     * @param fetchCategoryStats
     * @param fetchActionTypeStats
     */
    public suspend fun getPublicUserStats(
        userId: String,
        fetchCategoryStats: Boolean = false,
        fetchActionTypeStats: Boolean = false
    ): UserStats

    /**
     * Get the number of views a user has on all their segments
     *
     * @param userId Local user ID
     */
    public suspend fun getViewsForUser(userId: String): Int

    /**
     * Get the total time saved from all the user's segments
     *
     * @param userId Local user ID
     * @return The saved time
     */
    public suspend fun getSavedTimeForUser(userId: String): Duration

    /**
     * Get current username
     *
     * @param userId
     */
    public suspend fun getUsername(userId: String): String

    /**
     * Get information about a segment
     *
     * @param uuid
     */
    public suspend fun getSegmentInfo(uuid: String): SegmentInfo

    /**
     * Get information about segments
     *
     * @param uuids
     */
    public suspend fun getSegmentInfo(uuids: List<String>): List<SegmentInfo>

    /**
     * Search for users matching the given username.
     *
     * @param username The username to search for
     * @param exact Exact matches only
     * @return A list of users matching the search
     */
    public suspend fun searchUsers(
        username: String,
        exact: Boolean = false
    ): List<SearchResult>

    /**
     * Get locked categories for a video
     *
     * @param videoId
     * @param actionTypes
     */
    public suspend fun getLockedCategories(
        videoId: String,
        actionTypes: List<Action> = listOf(Action.SKIP, Action.MUTE)
    ): LockedCategories

    /**
     * Get reason for lock(s)
     *
     * @param videoId
     * @param category Categories to get reasons for, defaults to all
     * @param actionTypes
     * @return List of lock reasons
     */
    public suspend fun getLockReason(
        videoId: String,
        category: Category? = null,
        actionTypes: List<Action>
    ): List<LockReason>

    /**
     * Get reason for lock(s)
     *
     * @param videoId
     * @param categories Categories to get reasons for, defaults to all
     * @param actionTypes
     * @return List of lock reasons
     */
    public suspend fun getLockReason(
        videoId: String,
        categories: List<Category>,
        actionTypes: List<Action>
    ): List<LockReason>

    /**
     * Get all segments of a video based on specified filters.
     * Note: It is suggested that you don't use this for knowing which segments to skip on your client, as thresholds and values that determine which segments are the best change over time.
     *
     * @param videoId
     * @param categories
     * @param actionTypes
     * @param service
     * @param page Page to start from
     * @param minVotes Vote threshold, inclusive, default includes all segments
     * @param maxVotes Vote threshold, inclusive, default includes all segments
     * @param minViews View threshold, inclusive, default includes all segments
     * @param maxViews View threshold, inclusive, default includes all segments
     * @param locked
     * @param hidden
     * @param ignored
     */
    public suspend fun searchSegments(
        videoId: String,
        categories: List<String> = emptyList(),
        actionTypes: List<Action> = listOf(Action.SKIP, Action.MUTE),
        service: Service = Service.YOUTUBE,
        page: Int = 0,
        minVotes: Int? = null,
        maxVotes: Int? = null,
        minViews: Int? = null,
        maxViews: Int? = null,
        locked: Boolean = true,
        hidden: Boolean = true,
        ignored: Boolean = true
    ): SearchSegments

    /**
     * Get status of server
     */
    public suspend fun getServerStatus(): ServerStatus

    /**
     * Get top submitters
     *
     * @param sortType
     */
    public suspend fun getTopUsers(sortType: SortType = SortType.MINUTES_SAVED): TopUsers

    /**
     * Get top submitters by category
     *
     * @param category
     * @param sortType
     */
    public suspend fun getTopCategoryUsers(
        category: Category,
        sortType: SortType = SortType.MINUTES_SAVED
    ): TopUsers

    /**
     * Get total stats
     *
     * @param countContributingUsers
     */
    public suspend fun getTotalStats(countContributingUsers: Boolean = false): TotalStats

    /**
     * Get duration saved by all skips
     */
    public suspend fun getTotalDurationSaved(): Duration

    /**
     * Get user VIP status
     *
     * @param userId Private user ID
     */
    public fun isUserVip(userId: String): Boolean
}