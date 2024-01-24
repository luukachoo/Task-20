package com.example.task20.di

import com.example.task20.domain.repository.UserRepository
import com.example.task20.domain.use_case.AddUserUseCase
import com.example.task20.domain.use_case.RemoveUserUseCase
import com.example.task20.domain.use_case.UpdateUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAddUserUseCase(userRepository: UserRepository): AddUserUseCase {
        return AddUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteUserUseCase(userRepository: UserRepository): RemoveUserUseCase {
        return RemoveUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateUserUseCase(userRepository: UserRepository): UpdateUserUseCase {
        return UpdateUserUseCase(userRepository)
    }

}