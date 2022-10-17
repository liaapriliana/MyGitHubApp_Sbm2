package com.example.mygithubapp_sbm2.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubapp_sbm2.api.ApiConfig
import retrofit2.Call
import retrofit2.Response

class DetailUserViewModel: ViewModel() {
    val user =  MutableLiveData<DetailUserResponse>()

    fun setDetailUsers(username: String){
        ApiConfig.getApiService()
            .getDetailUser(username)
            .enqueue(object : retrofit2.Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ){
                    if (response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun getDetailUsers() : LiveData<DetailUserResponse> {
        return user
    }
}
