package com.dantruong.countmvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// 1. Model: Lớp dữ liệu đại diện cho trạng thái của ứng dụng
data class CounterState(
    val count: Int = 0
)

class CounterViewModel: ViewModel() {
    // 2. ViewModel: Quản lý dữ liệu bằng StateFlow (chuẩn độc lập với UI, tốt hơn MutableState của Compose)
    private val _uiState = MutableStateFlow(CounterState())
    val uiState: StateFlow<CounterState> = _uiState.asStateFlow()

    // Các hàm xử lý logic (Events từ View)
    fun increment() {
        _uiState.update { currentState ->
            currentState.copy(count = currentState.count + 1)
        }
    }

    fun decrement() {
        _uiState.update { currentState ->
            currentState.copy(count = currentState.count - 1)
        }
    }
}