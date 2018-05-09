package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.App
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.R
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Details
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.DetailsView
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.presenters.details.DetailsPresenterImpl
import kotlinx.android.synthetic.main.details_fragment.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by Pedro Ribeiro on 08/03/2018.
 */
class DetailsFragment : Fragment(), DetailsView {

    @Inject lateinit var detailsPresenter: DetailsPresenterImpl
    private var postId: Int by Delegates.notNull()
    private var userId: Int by Delegates.notNull()
    private var postBody: String = ""

    companion object {
        fun newInstance(postId: Int, userId: Int, postBody: String): DetailsFragment {
            val bundle = Bundle()
            bundle.putInt("post_id", postId)
            bundle.putInt("user_id", userId)
            bundle.putString("post_body", postBody)
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).component.inject(this)
        getValues()
        detailsPresenter.setView(this)
        detailsPresenter.onAttach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsPresenter.getData(postId, userId, postBody)
    }

    override fun onDestroy() {
        super.onDestroy()
        detailsPresenter.onDetach()
    }

    private fun getValues() {
        postId = arguments?.getInt("post_id") ?: 0
        userId = arguments?.getInt("user_id") ?: 0
        postBody = arguments?.getString("post_body") ?: ""
    }

    override fun populateView(details: Details?, postBody: String) {
        details_fragment_author.text = details!!.users.get(0).name
        details_fragment_description.text = postBody
        details_fragment_comments.text = getString(R.string.details_fragment_number_comments) +
                " " + details.comments.size.toString()
    }

    override fun handleError(throwable: Throwable?) {
        Toast.makeText(context, R.string.error, Toast.LENGTH_LONG).show()
    }
}