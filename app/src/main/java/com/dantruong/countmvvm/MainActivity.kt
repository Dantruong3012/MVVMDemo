package com.dantruong.countmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dantruong.countmvvm.ui.theme.CountMVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: CounterViewModel = viewModel()
                CountMVVMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterScreen(Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}

@Composable
fun CounterScreen(modifier: Modifier = Modifier, viewModel: CounterViewModel) {
    // 3. View: Lắng nghe trạng thái từ ViewModel (sẽ tự động vẽ lại khi state đổi)
    val state by viewModel.uiState.collectAsState()

    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Counter Machine")
        // Hiển thị dữ liệu từ Model (state)
        Text(text = "Current number: ${state.count}")
        
        Button(onClick = { viewModel.increment() }) {
            Text(text = "Tăng")
        }
        Button(onClick = { viewModel.decrement() }) {
            Text(text = "Giảm")
        }
    }
}