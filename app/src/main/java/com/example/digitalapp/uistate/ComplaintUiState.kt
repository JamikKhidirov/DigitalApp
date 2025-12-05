package com.example.digitalapp.uistate




data class ComplaintUiState(
    val title: String = "",
    val description: String = "",
    val isSubmitting: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)
