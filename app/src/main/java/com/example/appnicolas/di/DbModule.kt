package com.example.appnicolas.di

import android.content.Context
import androidx.room.Room
import com.example.appnicolas.db.ProjectDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideDb(
        @ApplicationContext app: Context
    ) =Room.databaseBuilder(
        app,
        ProjectDB::class.java,
        "project_db"
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(db: ProjectDB) = db.FavoriteDao()
}