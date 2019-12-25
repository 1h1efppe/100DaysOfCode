package com.betaraybill.randomnumbers

import kotlin.random.Random

class Randomizer {
    var number = getRandomNumber()

    fun getRandomNumber() : Int {
        return Random.nextInt(0, 100)
    }
}