package com.sicoapp.movieapp.di

import com.sicoapp.movieapp.data.remote.firebase.FireStoreClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object FireStoreClassModule {

    @Singleton
    @Provides
    fun provideFrireStore() : FireStoreClass {
        return FireStoreClass()
    }
}

