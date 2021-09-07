package com.scottmolloy.useroverflow

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val items: List<User>,
    val has_more: Boolean,
    val quota_max: Int,
    val quota_remaining: Int,
)

@Serializable
data class User(
    val account_id: Double,
    val display_name: String,
    val reputation: Double,
    val badge_counts: BadgeCounts,
    val profile_image: String,
)

@Serializable
data class BadgeCounts(
    val bronze: Int = 0,
    val silver: Int = 0,
    val gold: Int = 0,
)