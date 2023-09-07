package com.example.christopheraraujodimiour.model.remote

sealed class PresentationData{
    data class Success(val data: DataResponse): PresentationData()
    data class Error(val error: String): PresentationData()
    object Loading : PresentationData()
}
