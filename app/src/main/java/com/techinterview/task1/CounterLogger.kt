package com.techinterview.task1

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private const val JOBS_SIZE = 250

class CounterLogger {

    private var counter = -1
    val logs = mutableListOf<Int>()

    fun increment() {
        val newValue = counter + 1
        counter = newValue
        logs.add(newValue)
    }
}

fun main() = runBlocking {
    val logger = CounterLogger()
    val jobs = mutableListOf<Job>()

    repeat(JOBS_SIZE) {
        val job = CoroutineScope(Job()).launch {
            logger.increment()
        }
        jobs.add(job)
    }

    jobs.forEach { it.join() }

    println("Expected size: $JOBS_SIZE, actual: ${logger.logs.size}")
    println("Max value in logs: ${logger.logs.maxOrNull()}")
    println("Logs: ${logger.logs}")
}
