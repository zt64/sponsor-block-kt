package dev.zt64.sbkt.api

import dev.zt64.sbkt.model.*
import io.ktor.client.*
import io.ktor.client.request.*
import kotlin.time.Duration

internal class UserApiImpl(private val httpClient: HttpClient, override val userId: String) :
    UserApi,
    GuestApi by GuestApiImpl(httpClient) {
    override suspend fun createSegment(
        videoId: String,
        startTime: Duration,
        endTime: Duration,
        category: Category,
        userAgent: String,
        service: Service,
        videoDuration: Duration?,
        action: Action
    ) {
        httpClient.post("skipSegments") {
            parameter("videoID", videoId)
            parameter("startTime", startTime.inWholeSeconds.toInt())
            parameter("endTime", endTime.inWholeSeconds.toInt())
            parameter("category", category.name.lowercase())
            parameter("userID", userId)
            parameter("userAgent", userAgent)
            parameter("service", service.name.lowercase())
            videoDuration?.let { parameter("videoDuration", it.inWholeSeconds.toInt()) }
            parameter("actionType", action.name.lowercase())
        }
    }

    override suspend fun vote(uuid: String, videoId: String, type: Vote) {
        httpClient.post("voteOnSponsorTime") {
            parameter("UUID", uuid)
            parameter("userID", userId)
            parameter("videoID", videoId)
            parameter("type", type.value)
        }
    }

    override suspend fun upvoteSegment(segmentId: String, videoId: String) {
        vote(segmentId, videoId, Vote.UPVOTE)
    }

    override suspend fun downvoteSegment(segmentId: String, videoId: String) {
        vote(segmentId, videoId, Vote.DOWNVOTE)
    }

    override suspend fun unvoteSegment(segmentId: String, videoId: String) {
        vote(segmentId, videoId, Vote.UNDO)
    }

    override suspend fun setUsername(username: String) {
        httpClient.post("setUsername") {
            parameter("userID", userId)
            parameter("username", username)
        }
    }
}