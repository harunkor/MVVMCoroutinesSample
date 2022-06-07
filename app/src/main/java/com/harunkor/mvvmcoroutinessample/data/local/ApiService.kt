package com.harunkor.mvvmcoroutinessample.data.local

import com.harunkor.mvvmcoroutinessample.domain.model.User
import retrofit2.http.GET

interface ApiService {
   @GET("users")
   suspend fun getUsers(): List<User>
}