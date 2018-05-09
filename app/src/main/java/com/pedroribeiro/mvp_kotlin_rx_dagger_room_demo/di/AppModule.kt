package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.db.AppDatabase
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.network.Services
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

/**
 * Created by Pedro Ribeiro on 12/03/2018.
 */
@Module
class AppModule(private val app: Application) {

    val BASE_URL = "http://jsonplaceholder.typicode.com/"

    val objectMapper: ObjectMapper = ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .registerModule(KotlinModule())

    val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    val api: Services = retrofit.create(Services::class.java)

    val appDatabase: AppDatabase = Room
            .databaseBuilder(app.applicationContext, AppDatabase::class.java, "app-database")
            .build()

    val compositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun provideApi(): Services {
        return api
    }

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return appDatabase
    }

    @Provides
    @Singleton
    fun provideCompositeDisposabl(): CompositeDisposable {
        return compositeDisposable
    }

    @Provides
    @Singleton
    fun provideContext(): Context = app
}