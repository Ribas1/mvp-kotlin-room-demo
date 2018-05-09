package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.App
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.R
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Post
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.MainView
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.adapters.MainAdapter
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.presenters.main.MainPresenterImpl
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

/**
 * Created by Pedro Ribeiro on 07/03/2018.
 */
class MainFragment : Fragment(), MainView {

    @Inject
    lateinit var mainPresenter: MainPresenterImpl
    val adapter: MainAdapter by lazy { MainAdapter(mainPresenter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).component.inject(this)
        mainPresenter.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main_fragment_recycler_view.layoutManager = LinearLayoutManager(context)
        main_fragment_recycler_view.adapter = adapter
    }

    override fun sendPostsToAdapter(posts: List<Post>) {
        adapter.setData(posts)
    }

    /**
     * Should provide more context about the error but showing a general string in order to have some
     * error handling
     */
    override fun showError(it: Throwable) {
        Toast.makeText(context, getString(R.string.error), Toast.LENGTH_LONG).show()
    }

    override fun navigateToDetails(post: Post) {
        activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.addToBackStack(this.javaClass.name)
                ?.replace(R.id.fragment_container,
                        DetailsFragment.newInstance(post.postId, post.userId, post.body))
                ?.commit()
    }
}
