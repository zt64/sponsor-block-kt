package dev.zt64.sbkt.api

import dev.zt64.sbkt.model.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.minutes

internal class GuestApiImpl(private val httpClient: HttpClient) : GuestApi {
    override suspend fun getSegments(
        videoId: String,
        category: Category,
        action: Action,
        service: Service
    ): List<Segment> {
        return httpClient.get("skipSegments") {
            parameter("videoID", videoId)
            parameter("category", category.name.lowercase())
            parameter("actionType", action.name.lowercase())
            parameter("service", "YouTube")
        }.body()
    }

    override suspend fun getSegments(
        videoId: String,
        categories: List<Category>,
        action: Action,
        service: Service
    ): List<Segment> {
        return httpClient.get("skipSegments") {
            parameter("videoID", videoId)
            parameter("categories", categories.joinToString(",") { it.name.lowercase() })
            parameter("action", action.name.lowercase())
            parameter("service", service.name.lowercase())
        }.body()
    }

    override suspend fun viewSegment(uuid: String) {
        httpClient.post("voteOnSponsorTime") {
            parameter("UUID", uuid)
        }
    }

    override suspend fun getUserInfo(userId: String): UserInfo {
        return httpClient.get("userInfo") {
            parameter("userID", userId)
        }.body()
    }

    override suspend fun getPublicUserInfo(userId: String): UserInfo {
        return httpClient.get("userInfo") {
            parameter("publicUserID", userId)
        }.body()
    }

    override suspend fun getUserStats(
        userId: String,
        fetchCategoryStats: Boolean,
        fetchActionTypeStats: Boolean
    ): UserStats {
        return httpClient.get("userStats") {
            parameter("userID", userId)
            parameter("fetchCategoryStats", fetchCategoryStats)
            parameter("fetchActionTypeStats", fetchActionTypeStats)
        }.body()
    }

    override suspend fun getPublicUserStats(
        userId: String,
        fetchCategoryStats: Boolean,
        fetchActionTypeStats: Boolean
    ): UserStats {
        return httpClient.get("userStats") {
            parameter("publicUserID", userId)
            parameter("fetchCategoryStats", fetchCategoryStats)
            parameter("fetchActionTypeStats", fetchActionTypeStats)
        }.body()
    }

    override suspend fun getViewsForUser(userId: String): Int {
        return httpClient
            .get("getViewsForUser") {
                parameter("userID", userId)
            }.body<ViewsForUser>()
            .viewCount
    }

    override suspend fun getSavedTimeForUser(userId: String): Duration {
        return httpClient
            .get("getSavedTimeForUser") {
                parameter("userID", userId)
            }.body<JsonObject>()["timeSaved"]!!
            .jsonPrimitive
            .float
            .toDouble()
            .minutes
    }

    override suspend fun getUsername(userId: String): String {
        return httpClient
            .get("getUsername") {
                parameter("userID", userId)
            }.body<JsonObject>()["userName"]!!
            .jsonPrimitive
            .content
    }

    override suspend fun getSegmentInfo(uuid: String): SegmentInfo {
        return httpClient
            .get("segmentInfo") {
                parameter("UUID", uuid)
            }.body<List<SegmentInfo>>()
            .single()
    }

    override suspend fun getSegmentInfo(uuids: List<String>): List<SegmentInfo> {
        return httpClient.get("segmentInfo") {
            parameter("UUIDs", "[${uuids.joinToString(",") { "\"$it\"" }}]")
        }.body()
    }

    override suspend fun searchUsers(username: String, exact: Boolean): List<SearchResult> {
        return httpClient.get("userID") {
            parameter("username", username)
            parameter("exact", exact)
        }.body()
    }

    override suspend fun getLockedCategories(
        videoId: String,
        actionTypes: List<Action>
    ): LockedCategories {
        return httpClient.get("lockCategories") {
            parameter("videoID", videoId)
            parameter("actionTypes", actionTypes.joinToString(",") { it.name.lowercase() })
        }.body()
    }

    override suspend fun getLockReason(
        videoId: String,
        category: Category?,
        actionTypes: List<Action>
    ): List<LockReason> {
        return httpClient.get("lockReason") {
            parameter("videoID", videoId)
            parameter("category", category?.name?.lowercase())
            parameter("actionTypes", actionTypes.joinToString(",") { it.name.lowercase() })
        }.body()
    }

    override suspend fun getLockReason(
        videoId: String,
        categories: List<Category>,
        actionTypes: List<Action>
    ): List<LockReason> {
        return httpClient.get("lockReason") {
            parameter("videoID", videoId)
            parameter("categories", categories.joinToString(",") { it.name.lowercase() })
            parameter("actionTypes", actionTypes.joinToString(",") { it.name.lowercase() })
        }.body()
    }

    override suspend fun searchSegments(
        videoId: String,
        categories: List<String>,
        actionTypes: List<Action>,
        service: Service,
        page: Int,
        minVotes: Int?,
        maxVotes: Int?,
        minViews: Int?,
        maxViews: Int?,
        locked: Boolean,
        hidden: Boolean,
        ignored: Boolean
    ): SearchSegments {
        return httpClient.get("searchSegments") {
            parameter("videoID", videoId)
            parameter("categories", categories.joinToString(","))
            parameter("actionTypes", "[${actionTypes.joinToString(",") { "\"$it\"" }}]")
            parameter("service", service)
            parameter("page", page)
            parameter("minViews", minViews)
            parameter("maxViews", maxViews)
            parameter("locked", locked)
            parameter("hidden", hidden)
            parameter("ignored", ignored)
        }.body()
    }

    override suspend fun getServerStatus(): ServerStatus {
        return httpClient.get("status").body()
    }

    override suspend fun getTopUsers(sortType: SortType): TopUsers {
        return httpClient.get("getTopUsers") {
            parameter("sortType", sortType.ordinal)
        }.body()
    }

    override suspend fun getTopCategoryUsers(category: Category, sortType: SortType): TopUsers {
        return httpClient.get("getTopCategoryUsers") {
            parameter("sortType", sortType.ordinal)
            parameter("category", Json.encodeToString(category).removeSurrounding("\""))
        }.body()
    }

    override suspend fun getTotalStats(countContributingUsers: Boolean): TotalStats {
        return httpClient.get("totalStats") {
            parameter("countContributingUsers", countContributingUsers)
        }.body()
    }

    override suspend fun getTotalDurationSaved(): Duration {
        return httpClient
            .get("getDaysSavedFormatted") {
                timeout {
                    requestTimeoutMillis = 10000
                }
            }.body<DaysSaved>()
            .daysSaved
            .toDouble()
            .days
    }
}