package com.harunkor.mvvmcoroutinessample.domain.repository

import com.harunkor.mvvmcoroutinessample.data.local.ApiService
import com.harunkor.mvvmcoroutinessample.domain.model.User
import javax.inject.Inject

class MainRepository @Inject constructor(private val  apiService: ApiService) {
    suspend fun getUsers(): List<User> {
        return  apiService.getUsers()
    }
}