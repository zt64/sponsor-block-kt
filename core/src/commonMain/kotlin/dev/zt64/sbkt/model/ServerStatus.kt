package dev.zt64.sbkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Server status information.
 *
 * @property uptime Uptime of server in seconds
 * @property commit Full SHA hash of the latest git commit, development or test
 * @property db Current database version
 * @property startTime Unix time that request was received
 * @property processTime Seconds between startTime and sending response
 * @property redisProcessTime Seconds taken by Redis to process the request
 * @property loadAvg 5 and 15 minute load average
 * @property connections Number of active connections
 * @property statusRequests Number of /status requests in the last minute
 * @property hostname Hostname of current server
 * @property postgresStats Statistics related to PostgreSQL
 * @property postgresPrivateStats Private statistics related to PostgreSQL
 * @property redisStats Statistics related to Redis
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

/**
 * PostgreSQL statistics.
 *
 * @property activeRequests Number of active requests
 * @property avgReadTime Average read time
 * @property avgWriteTime Average write time
 * @property avgFailedTime Average failed time
 * @property pool Connection pool statistics
 */
@Serializable
public data class PostgresStats internal constructor(
    val activeRequests: Int,
    val avgReadTime: Float,
    val avgWriteTime: Float,
    val avgFailedTime: Float,
    val pool: Pool
) {
    /**
     * Connection pool statistics.
     *
     * @property total Total connections
     * @property idle Idle connections
     * @property waiting Waiting connections
     */
    @Serializable
    public data class Pool internal constructor(
        val total: Int,
        val idle: Int,
        val waiting: Int
    )
}

/**
 * Redis statistics.
 *
 * @property activeRequests Number of active requests
 * @property writeRequests Number of write requests
 * @property avgReadTime Average read time
 * @property avgWriteTime Average write time
 * @property memoryCacheHits Number of memory cache hits
 * @property memoryCacheTotalHits Total number of memory cache hits
 * @property memoryCacheLength Length of memory cache
 * @property memoryCacheSize Size of memory cache
 * @property lastInvalidation Time of last invalidation
 * @property lastInvalidationMessage Message of last invalidation
 */
@Serializable
public data class RedisStats internal constructor(
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