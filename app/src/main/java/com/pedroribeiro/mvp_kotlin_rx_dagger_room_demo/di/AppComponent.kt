package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.di

import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.App
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.fragments.DetailsFragment
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.fragments.MainFragment
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.presenters.details.DetailsPresenterImpl
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.presenters.main.MainPresenterImpl
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Pedro Ribeiro on 12/03/2018.
 */
@Singleton
@Component(modules = [AppModule::class, PresenterModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(app: App)
    fun inject(mainFragment: MainFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(mainPresenterImpl: MainPresenterImpl)
    fun inject(detailsPresenterImpl: DetailsPresenterImpl)
}