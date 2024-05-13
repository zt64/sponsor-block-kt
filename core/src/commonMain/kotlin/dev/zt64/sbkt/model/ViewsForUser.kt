package dev.zt64.sbkt.model

import kotlinx.serialization.Serializable

@Serializable
public data class ViewsForUser internal constructor(val viewCount: Int)