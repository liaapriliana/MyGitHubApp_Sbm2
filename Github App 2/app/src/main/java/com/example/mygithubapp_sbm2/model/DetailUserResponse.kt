package com.example.mygithubapp_sbm2.model

data class DetailUserResponse(
    val login : String,
    val id: Int,
    val avatar_url: String,
    val followers_url : String,
    val following_url : String,
    val company : String,
    val location : String,
    val name : String,
    val following : Int,
    val followers : Int,
)
