package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.presenters.main

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Post
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.repositories.PostRepository
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.MainView
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.presenters.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Pedro Ribeiro on 07/03/2018.
 */
class MainPresenterImpl @Inject constructor(private var compositeDisposable: CompositeDisposable,
                                            private var postRepository: PostRepository) : BasePresenter(), MainPresenter, LifecycleObserver {

    private lateinit var view: MainView
    private val TAG = javaClass.name

    override fun setView(mainView: MainView) {
        view = mainView
        (view as LifecycleOwner).lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onAttach() {
        getPosts()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDetach() {
        compositeDisposable.clear()
        (view as LifecycleOwner).lifecycle.removeObserver(this)
    }

    override fun getPosts() {
        compositeDisposable.add(
                postRepository.getPost()
                        .subscribe({ view.sendPostsToAdapter(it) }, { view.showError(it) })
                               )
    }

    override fun openDetails(post: Post) {
        view.navigateToDetails(post)
    }

}