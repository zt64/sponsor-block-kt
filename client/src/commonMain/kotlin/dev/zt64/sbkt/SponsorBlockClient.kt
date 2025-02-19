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
        engine: HttpClientEngineFactory<*>,
        apiUrl: String = BASE_URL,
        userAgent: String = USER_AGENT
    ) : this(HttpClient(engine, getHttpClientConfiguration(apiUrl, userAgent)))

    public inner class Guest : GuestApi by GuestApiImpl(httpClient)

    public inner class User(userId: String) : UserApi by UserApiImpl(httpClient, userId)

    public companion object {
        public fun guest(apiUrl: String = BASE_URL, userAgent: String = USER_AGENT): Guest {
            return SponsorBlockClient(
                HttpClient(getHttpClientConfiguration(apiUrl, userAgent))
            ).Guest()
        }

        public fun user(
            userId: String,
            apiUrl: String = BASE_URL,
            userAgent: String = USER_AGENT
        ): User {
            return SponsorBlockClient(
                HttpClient(getHttpClientConfiguration(apiUrl, userAgent))
            ).User(userId)
        }
    }
}

/**
 * Creates a new instance of the SponsorBlockClient for guest access.
 *
 * @param apiUrl The base URL of the SponsorBlock API. Defaults to [BASE_URL].
 * @param userAgent The user agent string to be used in requests. Defaults to [USER_AGENT].
 * @param clientConfig A lambda function to configure the HttpClient.
 * @return A new instance of SponsorBlockClient.Guest.
 */
@Suppress("FunctionName")
public fun SponsorBlockClient(
    apiUrl: String = BASE_URL,
    userAgent: String = USER_AGENT,
    clientConfig: HttpClientConfig<*>.() -> Unit = getHttpClientConfiguration(apiUrl, userAgent)
): SponsorBlockClient.Guest {
    return SponsorBlockClient(HttpClient(clientConfig)).Guest()
}