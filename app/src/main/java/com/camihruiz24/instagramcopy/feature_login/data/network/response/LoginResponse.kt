package com.camihruiz24.instagramcopy.feature_login.data.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("success")
    val success: Boolean
)
