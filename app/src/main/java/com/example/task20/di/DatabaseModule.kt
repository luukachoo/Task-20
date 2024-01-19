package com.example.task20.di

import android.content.Context
import androidx.room.Room
import com.example.task20.data.local.dao.UserDao
import com.example.task20.data.local.database.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context) : UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java, "user-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }

}