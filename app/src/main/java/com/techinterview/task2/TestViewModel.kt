package com.techinterview.task2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TestViewModel: ViewModel() {

    private val legacyDownloader = LegacyDownloader()
    private val fileDescription = Channel<String>()
    val fileDescriptionFlow = fileDescription.receiveAsFlow()

    private val time = Channel<String>()
    val timeFlow = time.receiveAsFlow()

    fun downloadFiles() = calculateTime {
        var fileDescription = "no description"
        var file = "no file"

        legacyDownloader.downloadFileDescription(callback = object : Callback {
            override fun onSuccess(description: String) {
                fileDescription = description
            }

            override fun onError(errorDescription: String) {
                fileDescription = errorDescription
            }
        })

        viewModelScope.launch {
            file = downloadFile()
        }

        this.fileDescription.trySend("$file with $fileDescription")
    }
    
    private suspend fun downloadFile(): String {
        delay(1000)
        return "File downloaded"
    }

    private fun calculateTime(block: () -> Unit) {
        val start = System.currentTimeMillis()
        block.invoke()
        val end = System.currentTimeMillis()
        time.trySend("${end - start}")
    }
}
