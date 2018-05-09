package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model

/**
 * Created by Pedro Ribeiro on 09/03/2018.
 */
data class Details(val users: List<User>,
                   val comments: List<Comment>) {
    constructor(user: User, comments: List<Comment>) : this(arrayListOf(user), comments)
}