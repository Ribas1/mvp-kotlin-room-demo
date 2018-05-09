package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.presenters

/**
 * Created by Pedro Ribeiro on 07/03/2018.
 */
abstract class BasePresenter {
    abstract fun onAttach()
    abstract fun onDetach()
}