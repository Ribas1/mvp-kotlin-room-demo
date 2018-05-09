package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.di

import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.db.AppDatabase
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.network.Services
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.repositories.DetailsRepository
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.repositories.PostRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Pedro Ribeiro on 12/03/2018.
 */
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providePostRepository(api: Services,
                              appDatabase: AppDatabase): PostRepository = PostRepository(api,
            appDatabase)

    @Provides
    @Singleton
    fun provideDetailsRepository(api: Services,
                                 appDatabase: AppDatabase): DetailsRepository = DetailsRepository(
            api, appDatabase)
}