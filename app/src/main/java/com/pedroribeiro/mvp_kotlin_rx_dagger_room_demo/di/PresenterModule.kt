package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.di

import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.repositories.DetailsRepository
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.repositories.PostRepository
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.presenters.details.DetailsPresenterImpl
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.presenters.main.MainPresenterImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by Pedro Ribeiro on 12/03/2018.
 */
@Module
class PresenterModule {
    @Provides
    @Singleton
    fun provideMainPresenter(compositeDisposable: CompositeDisposable,
                             postRepository: PostRepository)
            : MainPresenterImpl = MainPresenterImpl(compositeDisposable, postRepository)

    @Provides
    @Singleton
    fun provideDetailsPresenter(compositeDisposable: CompositeDisposable,
                                detailsRepository: DetailsRepository)
            : DetailsPresenterImpl = DetailsPresenterImpl(compositeDisposable, detailsRepository)

}