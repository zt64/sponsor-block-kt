package dev.zt64.sbkt.api

import dev.zt64.sbkt.model.*
import kotlin.time.Duration

/**
 * Unauthenticated API
 *
 */
public interface GuestApi {
    /**
     * Retrieves segments for a specific video.
     *
     * @param videoId The ID of the video.
     * @param category The category of segments to retrieve. Defaults to [Category.SPONSOR].
     * @param action The action associated with the segment. Defaults to [Action.SKIP].
     * @param service The video service platform. Defaults to [Service.YOUTUBE].
     * @return A list of segments matching the criteria.
     */
    public suspend fun getSegments(
        videoId: String,
        category: Category = Category.SPONSOR,
        action: Action = Action.SKIP,
        service: Service = Service.YOUTUBE
    ): List<Segment>

    /**
     * Retrieves segments for a specific video.
     *
     * @param videoId The ID of the video.
     * @param categories A list of segment categories to retrieve.
     * @param action The action associated with the segments. Defaults to [Action.SKIP].
     * @param service The video service platform. Defaults to [Service.YOUTUBE].
     * @return A list of segments matching the criteria.
     */
    public suspend fun getSegments(
        videoId: String,
        categories: List<Category>,
        action: Action = Action.SKIP,
        service: Service = Service.YOUTUBE
    ): List<Segment>

    /**
     * Records a view for a specific segment.
     *
     * @param uuid The unique identifier of the segment.
     */
    public suspend fun viewSegment(uuid: String)

    /**
     * Retrieves information about a user.
     *
     * @param userId The user's unique identifier.
     * @return The [UserInfo] of the specified user.
     */
    public suspend fun getUserInfo(userId: String): UserInfo

    /**
     * Retrieves public information about a user.
     *
     * @param userId The user's unique identifier.
     * @return The [UserInfo] of the specified user.
     */
    public suspend fun getPublicUserInfo(userId: String): UserInfo

    /**
     * Retrieves statistical data for a user.
     *
     * @param userId The user's unique identifier.
     * @param fetchCategoryStats Whether to include category-specific statistics.
     * @param fetchActionTypeStats Whether to include action-specific statistics.
     * @return The [UserStats] for the specified user.
     */
    public suspend fun getUserStats(
        userId: String,
        fetchCategoryStats: Boolean = false,
        fetchActionTypeStats: Boolean = false
    ): UserStats

    /**
     * Retrieves public statistical data for a user.
     *
     * @param userId The user's unique identifier.
     * @param fetchCategoryStats Whether to include category-specific statistics.
     * @param fetchActionTypeStats Whether to include action-specific statistics.
     * @return The [UserStats] for the specified user.
     */
    public suspend fun getPublicUserStats(
        userId: String,
        fetchCategoryStats: Boolean = false,
        fetchActionTypeStats: Boolean = false
    ): UserStats

    /**
     * Retrieves the total number of views a user has on all their segments.
     *
     * @param userId The user's unique identifier.
     * @return The total number of views.
     */
    public suspend fun getViewsForUser(userId: String): Int

    /**
     * Retrieves the total duration saved by all of a user's submitted segments.
     *
     * @param userId The user's unique identifier.
     * @return The total saved duration.
     */
    public suspend fun getSavedTimeForUser(userId: String): Duration

    /**
     * Retrieves the current username of a user.
     *
     * @param userId The user's unique identifier.
     * @return The username of the user.
     */
    public suspend fun getUsername(userId: String): String

    /**
     * Retrieves information about a specific segment.
     *
     * @param uuid The unique identifier of the segment.
     * @return The [SegmentInfo] of the segment.
     */
    public suspend fun getSegmentInfo(uuid: String): SegmentInfo

    /**
     * Retrieves information about multiple segments.
     *
     * @param uuids A list of segment unique identifiers.
     * @return A list of [SegmentInfo] for the provided segment IDs.
     */
    public suspend fun getSegmentInfo(uuids: List<String>): List<SegmentInfo>

    /**
     * Searches for users that match a given username.
     *
     * @param username The username to search for.
     * @param exact Whether to search for exact matches only.
     * @return A list of users that match the search criteria.
     */
    public suspend fun searchUsers(username: String, exact: Boolean = false): List<SearchResult>

    /**
     * Retrieves locked categories for a video.
     *
     * @param videoId The ID of the video.
     * @param actionTypes A list of actions to check for locks. Defaults to [Action.SKIP] and [Action.MUTE].
     * @return The locked categories for the video.
     */
    public suspend fun getLockedCategories(
        videoId: String,
        actionTypes: List<Action> = listOf(Action.SKIP, Action.MUTE)
    ): LockedCategories

    /**
     * Retrieves the reason why specific categories are locked for a video.
     *
     * @param videoId The ID of the video.
     * @param category The category to check for locks, or null to check all categories.
     * @param actionTypes A list of actions to check.
     * @return A list of reasons why the categories are locked.
     */
    public suspend fun getLockReason(
        videoId: String,
        category: Category? = null,
        actionTypes: List<Action>
    ): List<LockReason>

    /**
     * Retrieves the reasons why specific categories are locked for a video.
     *
     * @param videoId The ID of the video.
     * @param categories A list of categories to check.
     * @param actionTypes A list of actions to check.
     * @return A list of reasons why the categories are locked.
     */
    public suspend fun getLockReason(
        videoId: String,
        categories: List<Category>,
        actionTypes: List<Action>
    ): List<LockReason>

    /**
     * Searches for all segments of a video based on specified filters.
     *
     * **Note:** Avoid using this method for determining which segments to skip, as
     * ranking criteria and thresholds may change over time.
     *
     * @param videoId The ID of the video.
     * @param categories A list of categories to filter.
     * @param actionTypes A list of actions to filter.
     * @param service The video service. Defaults to [Service.YOUTUBE].
     * @param page The starting page for pagination.
     * @param minVotes The minimum number of votes required.
     * @param maxVotes The maximum number of votes allowed.
     * @param minViews The minimum number of views required.
     * @param maxViews The maximum number of views allowed.
     * @param locked Whether to include locked segments.
     * @param hidden Whether to include hidden segments.
     * @param ignored Whether to include ignored segments.
     * @return A [SearchSegments] result matching the criteria.
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
     * Retrieves the current status of the server.
     *
     * @return The [ServerStatus] of the API server.
     */
    public suspend fun getServerStatus(): ServerStatus

    /**
     * Retrieves the top users based on submission statistics.
     *
     * @param sortType The sorting criteria. Defaults to [SortType.MINUTES_SAVED].
     * @return A list of the top users.
     */
    public suspend fun getTopUsers(sortType: SortType = SortType.MINUTES_SAVED): TopUsers

    /**
     * Retrieves the top users for a specific category.
     *
     * @param category The category to filter by.
     * @param sortType The sorting criteria. Defaults to [SortType.MINUTES_SAVED].
     * @return A list of the top users for the specified category.
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
}