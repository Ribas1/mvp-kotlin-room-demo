package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.presenters.details

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Details
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.repositories.DetailsRepository
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.DetailsView
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.presenters.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Pedro Ribeiro on 09/03/2018.
 */
class DetailsPresenterImpl @Inject constructor(private var compositeDisposable: CompositeDisposable,
                                               private var detailsRepository: DetailsRepository) : BasePresenter(), DetailsPresenter, LifecycleObserver {

    private lateinit var view: DetailsView

    override fun setView(detailsView: DetailsView) {
        view = detailsView
        (view as LifecycleOwner).lifecycle.addObserver(this)
    }

    override fun onAttach() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDetach() {
        compositeDisposable.clear()
        (view as LifecycleOwner).lifecycle.removeObserver(this)
    }

    override fun getData(postId: Int, userId: Int, postBody: String) {
        detailsRepository.setIds(postId, userId)
        compositeDisposable.add(
                detailsRepository.getDetails()
                        .subscribe({ details: Details? -> view.populateView(details, postBody) },
                                { throwable: Throwable? -> view.handleError(throwable) }))
    }


}