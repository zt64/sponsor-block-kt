package dev.zt64.sbkt

import dev.zt64.sbkt.api.*
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://sponsor.ajay.app/api/"
private const val USER_AGENT = "github.com/zt64/sponsorblock-kt"

private fun getHttpClientConfiguration(
    apiUrl: String = BASE_URL,
    userAgent: String = USER_AGENT
): HttpClientConfig<*>.() -> Unit = {
    install(ContentNegotiation) {
        json(Json)
    }

    install(UserAgent) {
        agent = userAgent
    }

    install(HttpTimeout)

    defaultRequest {
        contentType(ContentType.Application.Json)
        url(apiUrl)
    }
}

/**
 * SponsorBlock API client
 */
public class SponsorBlockClient internal constructor(private val httpClient: HttpClient) {
    public constructor(
        apiUrl: String = BASE_URL,
        userAgent: String = USER_AGENT,
        engine: HttpClientEngineFactory<*>
    ) : this(HttpClient(engine, getHttpClientConfiguration(apiUrl, userAgent)))

    public inner class Guest : GuestApi by GuestApiImpl(httpClient)

    public inner class User(userId: String) : UserApi by UserApiImpl(httpClient, userId)
}

@Suppress("FunctionName")
public fun SponsorBlockClient(
    apiUrl: String = BASE_URL,
    userAgent: String = USER_AGENT,
    clientConfig: HttpClientConfig<*>.() -> Unit = getHttpClientConfiguration(apiUrl, userAgent)
): SponsorBlockClient.Guest {
    return SponsorBlockClient(HttpClient(clientConfig)).Guest()
}

@Suppress("FunctionName")
public fun SponsorBlockClient(
    userId: String,
    apiUrl: String = BASE_URL,
    userAgent: String = USER_AGENT,
    engine: HttpClientEngineFactory<*>,
    clientConfig: HttpClientConfig<*>.() -> Unit = getHttpClientConfiguration(apiUrl, userAgent)
): SponsorBlockClient.User {
    return SponsorBlockClient(HttpClient(clientConfig)).User(userId)
}