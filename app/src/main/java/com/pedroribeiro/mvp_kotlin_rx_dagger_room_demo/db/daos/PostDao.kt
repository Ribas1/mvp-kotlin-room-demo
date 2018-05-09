package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.db.daos

import android.arch.persistence.room.*
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Post
import io.reactivex.Single

/**
 * Created by Pedro Ribeiro on 08/03/2018.
 */
@Dao
interface PostDao {

    @Query("SELECT * FROM post")
    fun getAllPosts(): Single<List<Post>>

    @Query("SELECT * FROM post LIMIT 1")
    fun getSinglePost(): Single<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<Post>)

    @Delete
    fun deletePost(post: Post)
}