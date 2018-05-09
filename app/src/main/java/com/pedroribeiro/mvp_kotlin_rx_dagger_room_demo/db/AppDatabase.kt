package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.db.daos.CommentDao
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.db.daos.PostDao
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.db.daos.UserDao
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Comment
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Post
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.User

/**
 * Created by Pedro Ribeiro on 08/03/2018.
 */
@Database(entities = [User::class, Post::class, Comment::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

    abstract fun userDao(): UserDao

    abstract fun commentDao(): CommentDao
}