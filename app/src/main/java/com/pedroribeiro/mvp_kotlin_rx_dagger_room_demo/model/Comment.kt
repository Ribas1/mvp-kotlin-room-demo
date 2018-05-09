package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Pedro Ribeiro on 07/03/2018.
 */
@Entity
data class Comment(val postId: Int,
                   @PrimaryKey val id: Int,
                   val name: String,
                   val email: String,
                   val body: String)