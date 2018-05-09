package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.db.daos

import android.arch.persistence.room.*
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.User
import io.reactivex.Single

/**
 * Created by Pedro Ribeiro on 08/03/2018.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE id =  :id")
    fun getUserById(id: Int): Single<List<User>>

    @Query("SELECT * FROM user")
    fun getAllUsers(): Single<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>)

    @Delete
    fun deleteUser(user: User)
}