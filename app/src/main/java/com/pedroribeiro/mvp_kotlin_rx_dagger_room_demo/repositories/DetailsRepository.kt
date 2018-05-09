package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.repositories

import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.db.AppDatabase
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Comment
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Details
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.User
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.network.Services
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by Pedro Ribeiro on 12/03/2018.
 */
class DetailsRepository @Inject constructor(api: Services, appDatabase: AppDatabase) {

    private var api = api
    private var userDao = appDatabase.userDao()
    private var commentDao = appDatabase.commentDao()
    private var postId: Int by Delegates.notNull()
    private var userId: Int by Delegates.notNull()

    fun setIds(postId: Int, userId: Int) {
        this.postId = postId
        this.userId = userId
    }

    fun getDetails(): Observable<Details> {
        return Observable.concatArray(mergeDbData(postId, userId), mergeData(postId, userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getUserFromDb(userId: Int): Observable<List<User>>? {
        return userDao.getUserById(userId).filter { it.isNotEmpty() }.toObservable()
    }

    private fun getCommentsFromDb(postId: Int): Observable<List<Comment>> {
        return commentDao.getCommentsById(postId).filter { it.isNotEmpty() }.toObservable()
    }

    private fun mergeDbData(postId: Int, userId: Int): Observable<Details> {
        return Observable.zip(getUserFromDb(userId), getCommentsFromDb(postId),
                BiFunction { users, comments -> Details(users, comments) })
    }

    private fun loadUser(userId: Int): Observable<List<User>> {
        return api.getUserById(userId).doOnNext { userDao.insertUsers(it) }
    }

    private fun loadComments(postId: Int): Observable<List<Comment>> {
        return api.getCommentsById(postId).doOnNext { commentDao.insertComments(it) }
    }

    private fun mergeData(postId: Int, userId: Int): Observable<Details> {
        return Observable.zip(loadUser(userId), loadComments(postId),
                BiFunction { users, comments -> Details(users, comments) })
    }
}