package com.example.domain.data



data class Complaint(
    val title: String = "",
    val description: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
