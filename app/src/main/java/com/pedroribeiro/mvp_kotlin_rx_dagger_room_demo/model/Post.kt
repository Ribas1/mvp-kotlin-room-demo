package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Pedro Ribeiro on 07/03/2018.
 */
@Entity(indices = [(Index("userId"))])
data class Post(val userId: Int,
                @PrimaryKey @JsonProperty("id") val postId: Int,
                val title: String,
                val body: String)