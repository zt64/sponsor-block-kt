package dev.zt64.sbkt.api

import dev.zt64.sbkt.model.*
import io.ktor.client.*

internal class VipApiImpl(private val client: HttpClient, override val userId: String) :
    VipApi,
    UserApi by UserApiImpl(client, userId) {
    override fun createCategoryLock(
        videoId: String,
        categories: List<Category>,
        actionTypes: List<Action>,
        reason: String
    ) {
        TODO("Not yet implemented")
    }

    override fun deleteCategoryLock(videoId: String, categories: List<Category>) {
        TODO("Not yet implemented")
    }

    override fun shadowBanUser(
        userId: String,
        enabled: Boolean,
        unHideOldSubmissions: Boolean?,
        categories: List<Category>?
    ) {
        TODO("Not yet implemented")
    }

    override fun shadowUnbanUser(userId: String) {
        TODO("Not yet implemented")
    }

    override fun warnUser(userId: String, reason: String?) {
        TODO("Not yet implemented")
    }

    override fun removeWarn(userId: String) {
        TODO("Not yet implemented")
    }

    override fun clearCache(userId: String, videoId: String) {
        TODO("Not yet implemented")
    }

    override fun purgeAllSegments(videoId: String, service: Service) {
        TODO("Not yet implemented")
    }

    override fun segmentShift(videoId: String, startTime: Float, endTime: Float) {
        TODO("Not yet implemented")
    }

    override fun addUserAsTempVIP(userId: String, channelVideoId: String) {
        TODO("Not yet implemented")
    }

    override fun removeUserAsTempVIP(userId: String, channelVideoId: String) {
        TODO("Not yet implemented")
    }

    override fun enableUserFeature(userId: String, feature: Feature) {
        TODO("Not yet implemented")
    }

    override fun disableUserFeature(userId: String, feature: Feature) {
        TODO("Not yet implemented")
    }
}