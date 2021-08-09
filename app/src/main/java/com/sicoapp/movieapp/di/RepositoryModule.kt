package com.sicoapp.movieapp.di

import com.sicoapp.movieapp.data.database.DataBaseDataSource
import com.sicoapp.movieapp.data.remote.NetworkDataSource
import com.sicoapp.movieapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        networkDataSource: NetworkDataSource,
        databaseDataSource: DataBaseDataSource
    ): Repository {
        return Repository(
            networkDataSource = networkDataSource,
            databaseDataSource = databaseDataSource
        )
    }
}