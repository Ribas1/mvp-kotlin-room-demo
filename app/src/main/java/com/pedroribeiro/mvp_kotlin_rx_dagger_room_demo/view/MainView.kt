package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view

import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Post

/**
 * Created by Pedro Ribeiro on 07/03/2018.
 */
interface MainView {
    fun sendPostsToAdapter(posts: List<Post>)
    fun navigateToDetails(post: Post)
    fun showError(it: Throwable)
}