package dev.zt64.sbkt.test

import dev.zt64.sbkt.SponsorBlockClient
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

private val VIDEO_IDS = listOf(
    "Qnauk0wEerQ"
)

private const val USER_ID = "3thSw8sqkofwfd7uS0ArRvhMuu0rb0HRLNTK"

class SBKtTest {
    private val client by lazy { SponsorBlockClient() }

    @Test
    fun testServerStatus() = runTest {
        println(client.getServerStatus())
    }

    // API takes way too long and causes test to fail because of timeout
    @Test
    fun testTotalDurationSaved() = runTest {
        client.getTotalDurationSaved()
    }

    @Test
    fun testSkipSegments() = runTest {
        VIDEO_IDS.forEach {
            client.getSegments(it)
        }
    }

    @Test
    fun testSegmentInfo() = runTest {
        val uuid = "264a26a409be16c95950ee6720eaad06c9ec89bd22472ef5eee3288ab9e95d246"

        client.getSegmentInfo(uuid)
        client.getSegmentInfo(
            listOf(uuid, uuid)
        )
    }

    @Test
    fun testSearch() = runTest {
        VIDEO_IDS.forEach {
            client.searchSegments(it)
        }
    }

    @Test
    fun testUserLookup() = runTest {
        client.getUsername(USER_ID)
        client.getUserInfo(USER_ID)
        client.getUserStats(
            userId = USER_ID,
            fetchCategoryStats = true,
            fetchActionTypeStats = true
        )
    }
}