package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.R
import com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.view.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, MainFragment())
                .commit()
    }
}
