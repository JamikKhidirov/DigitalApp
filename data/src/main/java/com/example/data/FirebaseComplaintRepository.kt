package com.example.data

import com.example.domain.ComplaintRepository
import com.example.domain.data.Complaint
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class FirebaseComplaintRepository @Inject constructor(
    private val firestore: FirebaseFirestore // Hilt внедрит
) : ComplaintRepository {

    private val complaintsCollection = firestore.collection("complaints")

    override suspend fun submitComplaint(complaint: Complaint) {

        try {
            complaintsCollection
                .add(complaint)
                .await()
        }

        catch (e: Exception){
            throw IllegalStateException("Ошибка при отправке в Firebase: ${e.message}")
        }
    }

}