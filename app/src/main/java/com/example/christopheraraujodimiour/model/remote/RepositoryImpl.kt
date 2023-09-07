package com.example.christopheraraujodimiour.model.remote

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl: Repository {
    private val service: Service by lazy {
        Service.initRetrofit()
    }
    override suspend fun fetchProfileList(): Flow<PresentationData> {
        return flow {
            delay(500)
            emit(PresentationData.Loading)
            val response = service.getProfileList()

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(PresentationData.Success(it))
                } ?: emit(PresentationData.Error(response.message()))
            } else {
                emit(PresentationData.Error(response.message()))
            }
        }
    }
}