package dev.zt64.sbkt.api

/**
 * API for admin users.
 */
public interface AdminApi : BaseApi {
    /**
     * Enable VIP status for a user
     *
     * @param userId
     */
    public fun enableUserVip(userId: String)

    /**
     * Disable VIP status for a user
     *
     * @param userId
     */
    public fun disableUserVip(userId: String)

    /**
     * Set the username for a user
     *
     * @param userId The public user ID
     * @param username
     * @param adminUserId
     */
    public fun setUsername(
        userId: String,
        username: String,
        adminUserId: String
    )
}