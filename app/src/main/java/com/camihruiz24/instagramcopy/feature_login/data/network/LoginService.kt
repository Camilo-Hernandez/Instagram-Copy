package com.camihruiz24.instagramcopy.feature_login.data.network

import com.camihruiz24.instagramcopy.feature_login.data.network.response.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class LoginService @Inject constructor(
    private val loginClient: LoginClient
){
    suspend fun doLogin(user: String, password: String) : Boolean =
        // TODO: Injectar el dispatcher como dependencia para facilitar el testing
        withContext(Dispatchers.IO) {
            // Se pone el doLogin() dentro del contexto del Dispatcher.IO para ejecutar la llamada a la api usando este tipo de Hilo especial para input output
            val response: Response<LoginResponse> =
                loginClient.doLogin()
            // Aquí se obtiene la respuesta del servidor
            response.body()?.success ?: false
        }
}