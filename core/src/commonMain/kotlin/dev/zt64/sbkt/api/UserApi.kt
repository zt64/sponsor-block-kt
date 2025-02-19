package dev.zt64.sbkt.api

import dev.zt64.sbkt.model.*
import kotlin.time.Duration

/**
 * API for authenticated users. Requires a user ID.
 * Extends [GuestApi] to provide additional functionality for authenticated users.
 */
public interface UserApi : GuestApi {
    /**
     * The unique identifier for the authenticated user.
     */
    public val userId: String

    /**
     * Creates a new segment on a video.
     *
     * @param videoId The unique identifier of the video.
     * @param startTime The starting time of the segment.
     * @param endTime The ending time of the segment.
     * @param category The category of the segment, such as [Category.SPONSOR].
     * @param userAgent The client identifier in the format `"Name of Client/Version"` or `"[BOT] Name of Bot/Version"`,
     *                  e.g., `"Chromium/1.0.0"`. Defaults to `"SponsorBlockKT/"`.
     * @param service The platform where the video is hosted, defaulting to [Service.YOUTUBE].
     * @param videoDuration The total duration of the video. If omitted, it will attempt to be retrieved from the YouTube API.
     *                      This is used to determine if a submission is outdated.
     * @param action The action to be taken when reaching the segment, defaulting to [Action.SKIP].
     */
    public suspend fun createSegment(
        videoId: String,
        startTime: Duration,
        endTime: Duration,
        category: Category,
        userAgent: String = "SponsorBlockKT/",
        service: Service = Service.YOUTUBE,
        videoDuration: Duration? = null,
        action: Action = Action.SKIP
    )

    /**
     * Votes on a segment.
     *
     * @param uuid The unique identifier of the segment.
     * @param type The type of vote to apply, e.g., [Vote.UPVOTE] or [Vote.DOWNVOTE].
     */
    public suspend fun vote(uuid: String, videoId: String, type: Vote)

    /**
     * Upvotes a segment.
     *
     * @param userId The local user ID.
     * @param segmentId The unique identifier of the segment.
     */
    public suspend fun upvoteSegment(segmentId: String, videoId: String)

    /**
     * Downvotes a segment.
     *
     * @param userId The local user ID.
     * @param segmentId The unique identifier of the segment.
     */
    public suspend fun downvoteSegment(segmentId: String, videoId: String)

    /**
     * Undoes a vote on a segment.
     *
     * @param userId The local user ID.
     * @param segmentId The unique identifier of the segment.
     */
    public suspend fun unvoteSegment(segmentId: String, videoId: String)

    /**
     * Sets the username for the authenticated user.
     *
     * @param username The new username to assign to the user.
     */
    public suspend fun setUsername(username: String)
}