package com.example.modul5.room

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modul5.utils.DependencyInjection

class RoomViewModelFactory private constructor(private val appRepository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            return AppViewModel(appRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: RoomViewModelFactory? = null
        fun getInstance(context: Context): RoomViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: RoomViewModelFactory(DependencyInjection.provideRepository(context))
            }.also { instance = it }
    }
    }