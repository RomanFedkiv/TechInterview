package com.techinterview.task1

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BankAccount {

    var balance = 1000

    suspend fun withdraw(amount: Int) {
        if (balance >= amount) {
            delay(100)
            balance -= amount
            println("Success withdraw $amount. Current balance: $balance")
        } else {
            println("Insufficient funds to withdraw $amount. Current balance: $balance")
        }
    }
}

fun main() = runBlocking {
    val bankAccount = BankAccount()
    val jobs = mutableListOf<Job>()

    repeat(100) {
        val job = launch {
            bankAccount.withdraw(100)
        }
        jobs.add(job)
    }

    jobs.forEach { it.join() }
    println("Final balance: ${bankAccount.balance}")
}
