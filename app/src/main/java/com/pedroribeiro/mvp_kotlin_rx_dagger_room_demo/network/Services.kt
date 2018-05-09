package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.network

import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Comment
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Post
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Pedro Ribeiro on 07/03/2018.
 */
interface Services {
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    @GET("/users")
    fun getUsers(): Observable<List<User>>

    @GET("/users")
    fun getUserById(@Query("id") id: Int): Observable<List<User>>

    @GET("/comments")
    fun getComments(): Observable<List<Comment>>

    @GET("/comments")
    fun getCommentsById(@Query("postId") postId: Int): Observable<List<Comment>>
}