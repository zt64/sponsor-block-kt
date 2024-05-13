package dev.zt64.sbkt

import dev.zt64.sbkt.model.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.minutes

private const val BASE_URL = "https://sponsor.ajay.app/api/"
private const val USER_AGENT = "github.com/zt64/sponsorblock-kt"

private fun getHttpClientConfiguration(
    apiUrl: String,
    userAgent: String
): HttpClientConfig<*>.() -> Unit = {
    install(ContentNegotiation) {
        json(Json)
    }

    install(UserAgent) {
        agent = userAgent
    }

    install(HttpTimeout)

    defaultRequest {
        contentType(ContentType.Application.Json)
        url(apiUrl)
    }
}

/**
 * SponsorBlock API client
 */
public class SponsorBlockClient(private val httpClient: HttpClient) : SponsorBlockApi {
    public constructor(
        apiUrl: String = BASE_URL,
        userAgent: String = USER_AGENT
    ) : this(HttpClient(getHttpClientConfiguration(apiUrl, userAgent)))

    public constructor(
        apiUrl: String = BASE_URL,
        userAgent: String = USER_AGENT,
        engine: HttpClientEngineFactory<*>
    ) : this(HttpClient(engine, getHttpClientConfiguration(apiUrl, userAgent)))

    override suspend fun getSegments(
        videoId: String,
        category: Category,
        action: Action,
        service: Service
    ): List<Segment> {
        val res = httpClient.get("skipSegments") {
            parameter("videoID", videoId)
            parameter("category", category.name.lowercase())
            parameter("actionType", action.name.lowercase())
            parameter("service", "YouTube")
        }

        return if (res.status == HttpStatusCode.OK) res.body() else emptyList()
    }

    override suspend fun getSegments(
        videoId: String,
        categories: List<Category>,
        action: Action,
        service: Service
    ): List<Segment> {
        return httpClient
            .get("skipSegments") {
                parameter("videoID", videoId)
                parameter("categories", categories.joinToString(",") { it.name.lowercase() })
                parameter("action", action.name.lowercase())
                parameter("service", service.name.lowercase())
            }.body()
    }

    override suspend fun getRequiredSegments(
        videoId: String,
        category: Category,
        segments: List<String>,
        action: Action,
        service: Service
    ): List<Segment> {
        return httpClient
            .get("skipSegments") {
                parameter("videoID", videoId)
                parameter("category", category.name.lowercase())
                parameter("segments", segments.joinToString(","))
                parameter("action", action.name.lowercase())
                parameter("service", service.name.lowercase())
            }.body()
    }

    override suspend fun getRequiredSegments(
        videoId: String,
        categories: List<Category>,
        segments: List<String>,
        action: Action,
        service: Service
    ): List<Segment> {
        return httpClient
            .get("skipSegments") {
                parameter("videoID", videoId)
                parameter("categories", categories.map { it.name.lowercase() })
                parameter("segments", segments.joinToString(","))
                parameter("action", action.name.lowercase())
                parameter("service", service.name.lowercase())
            }.body()
    }

    override suspend fun createSegment(
        videoId: String,
        startTime: Duration,
        endTime: Duration,
        category: Category,
        userId: String,
        userAgent: String,
        service: Service,
        videoDuration: Duration?,
        action: Action
    ) {
        httpClient.post("skipSegments") {
            parameter("videoID", videoId)
            parameter("userID", userId)
            parameter("startTime", startTime.inWholeMilliseconds)
            parameter("endTime", endTime.inWholeMilliseconds)
            parameter("category", category.name.lowercase())
            parameter("userAgent", userAgent)
            parameter("service", service.name.lowercase())
            parameter("videoDuration", videoDuration?.inWholeMilliseconds)
            parameter("actionType", action.name.lowercase())
        }
    }

    override suspend fun vote(
        uuid: String,
        userId: String,
        type: Vote
    ) {
        httpClient.post("voteOnSponsorTime") {
            parameter("UUID", uuid)
            parameter("userID", userId)
            parameter("type", type.value)
        }
    }

    override suspend fun vote(
        uuid: String,
        userId: String,
        category: Category
    ) {
        httpClient.post("voteOnSponsorTime") {
            parameter("UUID", uuid)
            parameter("userID", userId)
            parameter("category", category.name)
        }
    }

    override suspend fun viewSegment(uuid: String) {
        httpClient.post("voteOnSponsorTime") {
            parameter("UUID", uuid)
        }
    }

    override suspend fun getUserInfo(userId: String): UserInfo {
        return httpClient
            .get("userInfo") {
                parameter("userID", userId)
            }.body()
    }

    override suspend fun getPublicUserInfo(userId: String): UserInfo {
        return httpClient
            .get("userInfo") {
                parameter("publicUserID", userId)
            }.body()
    }

    override suspend fun getUserStats(
        userId: String,
        fetchCategoryStats: Boolean,
        fetchActionTypeStats: Boolean
    ): UserStats {
        return httpClient
            .get("userStats") {
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
        return httpClient
            .get("userStats") {
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

    // override suspend fun setUsername(
    //     userId: String,
    //     username: String
    // ) {
    //     httpClient.post("setUsername") {
    //         parameter("userID", userId)
    //         parameter("username", username)
    //     }
    // }

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
        return httpClient
            .get("segmentInfo") {
                parameter("UUIDs", "[${uuids.joinToString(",") { "\"$it\"" }}]")
            }.body()
    }

    override suspend fun searchUsers(
        username: String,
        exact: Boolean
    ): List<SearchResult> {
        return httpClient
            .get("userID") {
                parameter("username", username)
                parameter("exact", exact)
            }.body()
    }

    override suspend fun getLockedCategories(
        videoId: String,
        actionTypes: List<Action>
    ): LockedCategories {
        return httpClient
            .get("lockCategories") {
                parameter("videoID", videoId)
                parameter("actionTypes", actionTypes.joinToString(",") { it.name.lowercase() })
            }.body()
    }

    override suspend fun getLockReason(
        videoId: String,
        category: Category?,
        actionTypes: List<Action>
    ): List<LockReason> {
        return httpClient
            .get("lockReason") {
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
        return httpClient
            .get("lockReason") {
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
        return httpClient
            .get("searchSegments") {
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
        return httpClient
            .get("getTopUsers") {
                parameter("sortType", sortType.ordinal)
            }.body()
    }

    override suspend fun getTopCategoryUsers(
        category: Category,
        sortType: SortType
    ): TopUsers {
        return httpClient
            .get("getTopCategoryUsers") {
                parameter("sortType", sortType.ordinal)
                parameter("category", Json.encodeToString(category).removeSurrounding("\""))
            }.body()
    }

    override suspend fun getTotalStats(countContributingUsers: Boolean): TotalStats {
        return httpClient
            .get("totalStats") {
                parameter("countContributingUsers", countContributingUsers)
            }.body()
    }

    override suspend fun getTotalDurationSaved(): Duration {
        return httpClient
            .get("getDaysSavedFormatted") {
                timeout {
                    requestTimeoutMillis = 5000
                }
            }.body<DaysSaved>()
            .daysSaved
            .toDouble()
            .days
    }

    override fun isUserVip(userId: String): Boolean {
        TODO("Not yet implemented")
    }
}