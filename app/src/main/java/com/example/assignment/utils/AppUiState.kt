package com.example.assignment.utils

sealed class AppUiState{
    data object Loading : AppUiState()
    class Loaded(val data: Any) : AppUiState()
    class Error(val message: String) : AppUiState()
}
