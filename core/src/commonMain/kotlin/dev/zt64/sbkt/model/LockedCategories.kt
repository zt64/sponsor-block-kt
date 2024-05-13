package dev.zt64.sbkt.model

import kotlinx.serialization.Serializable

/**
 * @property categories
 * @property reason Specified reason for the lock. Only the most recent reason will be returned
 * @property actionTypes
 */
@Serializable
public data class LockedCategories internal constructor(
    val categories: List<Category>,
    val reason: String,
    val actionTypes: List<Action>
)