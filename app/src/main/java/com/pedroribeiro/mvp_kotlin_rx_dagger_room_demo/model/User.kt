package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by Pedro Ribeiro on 07/03/2018.
 */
@Entity(indices = [(Index("id"))])
@JsonIgnoreProperties(ignoreUnknown = true)
data class User(@PrimaryKey val id: Int,
                val name: String,
                val username: String,
                val email: String,
                val phone: String,
                val website: String)