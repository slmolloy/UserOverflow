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
                BadgeCounts(score(), score(), score())
            )
        }

    fun score(): Int = Random.nextInt().absoluteValue % 10000
}