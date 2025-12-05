package com.example.domain.useCase

import com.example.domain.ComplaintRepository
import com.example.domain.data.Complaint
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SubmitComplaintUseCase @Inject constructor(
    private val repository: ComplaintRepository // Hilt внедрит
) {
    suspend operator fun invoke(title: String, description: String) {

        if (title.isBlank() || description.isBlank()) {
            throw IllegalArgumentException("Заголовок и описание не могут быть пустыми.")
        }

        val complaint = Complaint(title = title.trim(), description = description.trim())
        repository.submitComplaint(complaint)
    }
}