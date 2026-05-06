package com.rajotiya.mytestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.rajotiya.mytestapp.req_capture.RequirementCaptureFlowRoot
import com.rajotiya.mytestapp.req_capture.RequirementCaptureFlowVM
import com.rajotiya.mytestapp.ui.theme.MyTestAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel = RequirementCaptureFlowVM()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MyTestAppTheme(dynamicColor = false) {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { padding ->
                    RequirementCaptureFlowRoot(
                        modifier = Modifier.fillMaxWidth()
                            .padding(padding)
                            .background(MaterialTheme.colorScheme.background),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}