package com.scottmolloy.useroverflow

import kotlin.math.absoluteValue
import kotlin.random.Random

object UserList {
    private val names = listOf("Liam","Olivia","Noah","Emma","Oliver","Ava","Elijah","Charlotte","William","Sophia","James","Amelia","Benjamin","Isabella","Lucas","Mia","Henry","Evelyn","Alexander","Harper")

    fun sampleUserList(): List<User> =
        (0..30).map {
            User(
                it.toDouble(),
                names[it % names.size],
                Random.nextDouble() * 100000000,
                BadgeCounts(score(), score(), score()),
                "https://www.gravatar.com/avatar/6d8ebb117e8d83d74ea95fbdd0f87e13?s=128&d=identicon&r=PG"
            )
        }

    private fun score(): Int = Random.nextInt().absoluteValue % 10000
}