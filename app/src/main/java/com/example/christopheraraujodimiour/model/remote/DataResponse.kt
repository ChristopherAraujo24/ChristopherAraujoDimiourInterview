package com.example.christopheraraujodimiour.model.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class DataResponse(
    val data: List<Profile>
)
@Parcelize
data class Profile(
    val first_name: String,
    val last_name: String,
    val email: String,
    val avatar: String
): Parcelable
