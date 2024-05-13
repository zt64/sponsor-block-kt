package dev.zt64.sbkt.model

import kotlinx.serialization.Serializable

@Serializable
public data class TopUsers internal constructor(
    val userNames: List<String>,
    val viewCounts: List<Int>,
    val totalSubmissions: List<Int>,
    val minutesSaved: List<Float>
)