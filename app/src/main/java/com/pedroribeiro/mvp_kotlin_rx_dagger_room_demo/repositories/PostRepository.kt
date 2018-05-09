package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.repositories

import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.db.AppDatabase
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Post
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.network.Services
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Pedro Ribeiro on 08/03/2018.
 */
class PostRepository(api: Services, appDatabase: AppDatabase) {

    private var api = api
    private var postDao = appDatabase.postDao()

    fun getPost(): Observable<List<Post>> {
        return Observable.concatArray(getPostsFromDb(), getPostsFromApi())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getPostsFromDb(): Observable<List<Post>> {
        return postDao.getAllPosts().filter { it.isNotEmpty() }
                .toObservable()
    }

    private fun getPostsFromApi(): Observable<List<Post>> {
        return api.getPosts().doOnNext {
            savePostsInDb(it)
        }
    }

    private fun savePostsInDb(posts: List<Post>) {
        Observable.fromCallable {
            postDao.insertPosts(posts)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe()
    }
}