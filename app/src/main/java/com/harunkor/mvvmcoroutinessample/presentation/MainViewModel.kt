package com.harunkor.mvvmcoroutinessample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.harunkor.mvvmcoroutinessample.domain.repository.MainRepository
import com.harunkor.mvvmcoroutinessample.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    fun fetchUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))

        try {
            emit(Resource.succes(mainRepository.getUsers()))
        }catch (exception: Exception){
            emit(Resource.error(exception.message ?: "Unk. Error",null))
        }

    }
}