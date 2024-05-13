package dev.zt64.sbkt.model

/**
 * Enum representing a vote on a submission.
 */
public enum class Vote(public val value: Int) {
    DOWNVOTE(0),
    UPVOTE(1),
    UNDO(20)
}