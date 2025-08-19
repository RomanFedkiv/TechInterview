package com.techinterview.task2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.techinterview.databinding.ActivityTestBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TestActivity: AppCompatActivity() {
    
    private val viewModel: TestViewModel by viewModels()
    private val binding: ActivityTestBinding by lazy { ActivityTestBinding.inflate(layoutInflater) }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
        initViews()
        collectDescription()
    }
    
    private fun initViews() {
        binding.downloadButton.setOnClickListener {
            viewModel.downloadFiles()
        }
    }

    private fun collectDescription() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            viewModel.fileDescriptionFlow.collect { description ->
                binding.fileDescription.text = description
            }
        }
        CoroutineScope(Job() + Dispatchers.Main).launch {
            viewModel.timeFlow.collect { time ->
                binding.time.text = "Time execution: $time"
            }
        }
    }
}
