package dev.zt64.sbkt.api

/**
 * API for users. Requires user id
 *
 */
public interface UserApi : BaseApi {
    /**
     * Upvote a segment
     *
     * @param userId Local user ID
     * @param segmentId
     */
    public fun upvoteSegment(
        userId: String,
        segmentId: String
    )

    /**
     * Downvote a segment
     *
     * @param userId Local user ID
     * @param segmentId
     */
    public fun downvoteSegment(
        userId: String,
        segmentId: String
    )

    /**
     * Undo a vote on a segment
     *
     * @param userId Local user ID
     * @param segmentId
     */
    public fun undoVote(
        userId: String,
        segmentId: String
    )

    /**
     * Set the username for a user
     *
     * @param userId Local user ID
     * @param username The username to set
     */
    public fun setUsername(
        userId: String,
        username: String
    )
}