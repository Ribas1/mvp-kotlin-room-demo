package com.pedroribeiro.mvp_kotlin_rx_dagger_room_demo.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Pedro Ribeiro on 08/03/2018.
 */
data class Address(val street: String,
                   val suite: String,
                   val city: String,
                   @JsonProperty("zipcode") val zipCode: String,
                   val geo: Geo)