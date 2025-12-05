package com.example.data.di

import com.example.data.FirebaseComplaintRepository
import com.example.domain.ComplaintRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun bindComplaintRepository(
        repository: FirebaseComplaintRepository
    ): ComplaintRepository

}