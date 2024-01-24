package com.example.task20.di

import com.example.task20.data.local.repository.UserRepositoryImpl
import com.example.task20.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindLocalRepository(repositoryImpl: UserRepositoryImpl): UserRepository

}