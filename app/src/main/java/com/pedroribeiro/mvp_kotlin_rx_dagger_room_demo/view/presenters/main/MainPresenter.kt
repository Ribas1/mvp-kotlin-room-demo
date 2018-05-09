package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.presenters.main

import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Post
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.MainView

/**
 * Created by Pedro Ribeiro on 07/03/2018.
 */
interface MainPresenter {
    fun getPosts()
    fun openDetails(post: Post)
    fun setView(mainView: MainView)
}