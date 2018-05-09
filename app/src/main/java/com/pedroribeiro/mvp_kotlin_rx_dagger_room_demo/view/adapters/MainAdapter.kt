package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.clicks
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.R
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model.Post
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.presenters.main.MainPresenter
import kotlinx.android.synthetic.main.main_item_post.view.*

/**
 * Created by Pedro Ribeiro on 08/03/2018.
 */
class MainAdapter(
        var presenter: MainPresenter) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var posts: List<Post> = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
                LayoutInflater.from(parent!!.context).inflate(R.layout.main_item_post, parent,
                        false))
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.postTitle.text = posts.get(position).title
        Glide.with(holder.itemView.context).load(R.drawable.placeholder).into(holder.postImage)
        holder.itemView.clicks()
                .subscribe { presenter.openDetails(posts.get(position)) }
    }

    fun setData(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    class MainViewHolder(var view: View?) : RecyclerView.ViewHolder(view) {
        var postTitle = view!!.post_title
        var postImage = view!!.post_image
    }
}