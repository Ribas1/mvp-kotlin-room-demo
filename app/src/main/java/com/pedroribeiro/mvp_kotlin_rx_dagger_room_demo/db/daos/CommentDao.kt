package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.db.daos

import android.arch.persistence.room.*
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Comment
import io.reactivex.Single

/**
 * Created by Pedro Ribeiro on 08/03/2018.
 */
@Dao
interface CommentDao {

    @Query("SELECT * FROM comment WHERE postId = :postId")
    fun getCommentByPostId(postId: Int): Single<List<Comment>>

    @Query("SELECT * FROM comment WHERE id =  :id")
    fun getCommentsById(id: Int): Single<List<Comment>>

    @Query("SELECT * FROM comment")
    fun getAllComments(): Single<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComments(comments: List<Comment>)

    @Delete
    fun deleteComment(comment: Comment)
}