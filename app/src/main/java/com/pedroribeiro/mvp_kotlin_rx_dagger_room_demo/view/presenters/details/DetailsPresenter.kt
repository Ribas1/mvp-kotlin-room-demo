package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.presenters.details

import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.DetailsView

/**
 * Created by Pedro Ribeiro on 09/03/2018.
 */
interface DetailsPresenter {
    fun getData(postId: Int, userId: Int, postBody: String)
    fun setView(view: DetailsView)
}