package com.example.data.di

import com.example.data.FirebaseComplaintRepository
import com.example.domain.ComplaintRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideFirebaceFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }


    // Связывание интерфейса с его конкретной реализацией (SOLID: DIP)
    @Provides
    @Singleton
    fun provideComplaintRepository(
        repository: FirebaseFirestore
    ): ComplaintRepository {
        return FirebaseComplaintRepository(repository)
    }
}