package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property uptime Uptime of server in seconds
 * @property commit Full SHA hash of the latest git commit, development or test
 * @property db Current database version
 * @property startTime Unix time that request was received
 * @property processTime Seconds between startTime and sending response
 * @property loadAvg 5 and 15 minute loadavg
 * @property statusRequests number of /status requests in the last minute
 * @property hostname hostname of current server
 */
@Serializable
public data class ServerStatus internal constructor(
    val uptime: Float,
    val commit: String,
    val db: Int,
    val startTime: Long,
    val processTime: Int,
    val redisProcessTime: Int,
    @SerialName("loadavg")
    val loadAvg: List<Float>,
    val connections: Int,
    val statusRequests: Int,
    val hostname: String,
    val postgresStats: PostgresStats,
    val postgresPrivateStats: PostgresStats,
    val redisStats: RedisStats
)

@Serializable
public data class PostgresStats(
    val activeRequests: Int,
    val avgReadTime: Float,
    val avgWriteTime: Float,
    val avgFailedTime: Float,
    val pool: Pool
) {
    @Serializable
    public data class Pool(
        val total: Int,
        val idle: Int,
        val waiting: Int
    )
}

@Serializable
public data class RedisStats(
    val activeRequests: Long,
    val writeRequests: Long,
    val avgReadTime: Double,
    val avgWriteTime: Double,
    val memoryCacheHits: Double,
    val memoryCacheTotalHits: Double,
    val memoryCacheLength: Long,
    val memoryCacheSize: Long,
    val lastInvalidation: Long,
    val lastInvalidationMessage: Long
)