package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view

import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Details

/**
 * Created by Pedro Ribeiro on 09/03/2018.
 */
interface DetailsView {
    fun populateView(details: Details?, postBody: String)
    fun handleError(throwable: Throwable?)
}