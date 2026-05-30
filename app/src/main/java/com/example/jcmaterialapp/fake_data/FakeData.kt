package com.example.jcmaterialapp.fake_data

import kotlinx.coroutines.delay
import kotlin.random.Random

suspend fun delay(){
    delay(Random.nextLong(500, 2_000))
}

suspend fun simulateDelayLong(){
    delay(Random.nextLong(3_000, 5_000))
}