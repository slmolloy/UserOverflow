package com.scottmolloy.useroverflow

import kotlin.math.absoluteValue
import kotlin.random.Random
import kotlin.random.nextInt

object UserList {
    private val names = listOf("Liam","Olivia","Noah","Emma","Oliver","Ava","Elijah","Charlotte","William","Sophia","James","Amelia","Benjamin","Isabella","Lucas","Mia","Henry","Evelyn","Alexander","Harper")

    fun sampleUserList(): List<User> =
        (0..30).map {
            User(
                BadgeCounts(score(), score(), score()),
                it,
                Random.nextInt(0..10000),
                "Earth",
                "https://www.gravatar.com/avatar/6d8ebb117e8d83d74ea95fbdd0f87e13?s=128&d=identicon&r=PG",
                names[it % names.size],
            )
        }

    private fun score(): Int = Random.nextInt().absoluteValue % 10000
}