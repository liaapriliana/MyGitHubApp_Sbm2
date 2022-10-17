package com.example.mygithubapp_sbm2.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubapp_sbm2.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val  listUsers = MutableLiveData<ArrayList<User>>()

    fun set_searchUser(query: String){
        val client = ApiConfig.getApiService().searchUser(query)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    listUsers.postValue(response.body()?.items)
                    }
                }



            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("onFailure", t.message!!)
            }
        })
    }
    fun get_searchUser(): LiveData<ArrayList<User>>{
        return listUsers
    }
}