package com.scottmolloy.useroverflow.data

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
    val badge_counts: BadgeCounts,
    val account_id: Int,
    val reputation: Int,
    val location: String? = null,
    val profile_image: String,
    val display_name: String,
)

@Serializable
data class BadgeCounts(
    val bronze: Int = 0,
    val silver: Int = 0,
    val gold: Int = 0,
)