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

    private val timeExecution = Channel<String>()
    val timeFlow = timeExecution.receiveAsFlow()

    fun downloadFiles() {
        //val startExecutionTime = System.currentTimeMillis()

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

        //this.timeExecution.trySend("${System.currentTimeMillis() - startExecutionTime}")
    }
    
    private suspend fun downloadFile(): String {
        delay(1000)  // Simulation API call
        return "File downloaded"
    }
}
