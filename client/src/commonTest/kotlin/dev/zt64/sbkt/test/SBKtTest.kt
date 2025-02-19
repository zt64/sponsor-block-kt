package dev.zt64.sbkt.test

import dev.zt64.sbkt.SponsorBlockClient
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

private val VIDEO_IDS = listOf(
    "Qnauk0wEerQ"
)

// Generated local user id
private const val USER_ID = "3thSw8sqkofwfd7uS0ArRvhMuu0rb0HRLNTK"

class SBKtTest {
    private val guestClient by lazy { SponsorBlockClient() }

    @Test
    fun testServerStatus() = runTest {
        println(guestClient.getServerStatus())
    }

    // API takes way too long and causes test to fail because of timeout
    @Test
    fun testTotalDurationSaved() = runTest {
        guestClient.getTotalDurationSaved()
    }

    @Test
    fun testSkipSegments() = runTest {
        VIDEO_IDS.forEach {
            guestClient.getSegments(it)
        }
    }

    @Test
    fun testSegmentInfo() = runTest {
        VIDEO_IDS.forEach { id ->
            val segments = guestClient.getSegments(id)

            guestClient.getSegmentInfo(segments.map { it.uuid })
        }
    }

    @Test
    fun testSearch() = runTest {
        VIDEO_IDS.forEach {
            guestClient.searchSegments(it)
        }
    }

    @Test
    fun testUserLookup() = runTest {
        guestClient.getUsername(USER_ID)
        guestClient.getUserInfo(USER_ID)
        guestClient.getPublicUserInfo(USER_ID)
        guestClient.getUserStats(
            userId = USER_ID,
            fetchCategoryStats = true,
            fetchActionTypeStats = true
        )
    }

    @Test
    fun testVote() = runTest {
        val client = SponsorBlockClient.user(USER_ID)

        VIDEO_IDS.forEach { id ->
            val segment = client.getSegments(id).first()
            client.unvoteSegment(segment.uuid, id)
            client.upvoteSegment(segment.uuid, id)
            client.downvoteSegment(segment.uuid, id)
        }
    }
}