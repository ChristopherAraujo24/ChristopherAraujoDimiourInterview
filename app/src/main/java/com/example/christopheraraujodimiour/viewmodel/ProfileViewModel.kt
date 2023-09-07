package com.example.christopheraraujodimiour.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.christopheraraujodimiour.model.remote.PresentationData
import com.example.christopheraraujodimiour.model.remote.Repository
import com.example.christopheraraujodimiour.model.remote.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    private val repository: Repository by lazy {
        RepositoryImpl()
    }

    private val _profileList = MutableLiveData<PresentationData>()
    val profileList: LiveData<PresentationData>
        get() = _profileList
    private val viewModelScope = CoroutineScope(Job())
    init {
        viewModelScope.launch {
            repository.fetchProfileList().collect {
                _profileList.postValue(it)
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel("ViewModel on cleared")
    }
}