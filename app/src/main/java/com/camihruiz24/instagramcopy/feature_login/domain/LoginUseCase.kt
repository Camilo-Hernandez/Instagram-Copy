package com.camihruiz24.instagramcopy.feature_login.domain

import com.camihruiz24.instagramcopy.feature_login.data.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(user: String, password: String) : Boolean =
        repository.doLogin(user, password)
}