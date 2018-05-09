package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo

import android.app.Application
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.di.AppComponent
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.di.AppModule
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.di.DaggerAppComponent

/**
 * Created by Pedro Ribeiro on 10/03/2018.
 */
class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}