package com.example.christopheraraujodimiour.model.remote

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun fetchProfileList(): Flow<PresentationData>
}