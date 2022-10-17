package com.example.mygithubapp_sbm2.api

import com.example.mygithubapp_sbm2.model.DetailUserResponse
import com.example.mygithubapp_sbm2.model.User
import com.example.mygithubapp_sbm2.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun searchUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username : String
    ): Call<DetailUserResponse>

    @GET("/users/{username}/followers")
    @Headers("Authorization: token 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")
    fun followerUser(
        @Path("username") username : String
    ): Call<ArrayList<User>>

    @GET("/users/{username}/following")
    @Headers("Authorization: token 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")
    fun followingUser(
        @Path("username") username : String
    ): Call<ArrayList<User>>
}




























