package com.example.domain

import com.example.domain.data.Complaint


interface ComplaintRepository {
    suspend fun submitComplaint(complaint: Complaint)
}