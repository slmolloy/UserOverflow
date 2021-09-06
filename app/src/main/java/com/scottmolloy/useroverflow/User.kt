package com.scottmolloy.useroverflow

data class User(
    val account_id: Double,
    val display_name: String,
    val reputation: Double,
    val badge_counts: BadgeCounts,
)

data class BadgeCounts(
    val bronze: Int = 0,
    val silver: Int = 0,
    val gold: Int = 0,
)