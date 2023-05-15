package com.camihruiz24.instagramcopy.feature_login.data

import com.camihruiz24.instagramcopy.feature_login.data.network.LoginService

class LoginRepository {

    // Esta variable es llamada por el dominio
    private val api = LoginService()

    suspend fun doLogin(user: String, password: String) : Boolean{
        // Aquí se pueden hacer comprobaciones sobre a qué fuente de datos consultar
        // Usando condicionales sobre los datos que entran como argumento para entender de dónde se deben consultar
        // En este caso, no hay necesidad de hacer comprobaciones pues la única fuente es la API

        return api.doLogin(user, password)
    }

}