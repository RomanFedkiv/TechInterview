package com.techinterview.task2

class LegacyDownloader {

    fun downloadFileDescription(callback: Callback) {
        Thread {
            Thread.sleep(2000)
            callback.onSuccess("legacy description")
        }.start()
    }
}

interface Callback {
    fun onSuccess(description: String)

    fun onError(errorDescription: String)
}
