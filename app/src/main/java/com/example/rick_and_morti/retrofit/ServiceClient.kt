package com.example.rick_and_morti.retrofit

import com.example.rick_and_morti.models.MainResponse
import com.example.rick_and_morti.utils.Constants.Endpoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceClient {

    //Se utiliza  Interceptor para mostrar los resultados de la peticion
    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // var global = DatosGlobales()
    //Asociamos el interceptor a las peticiones

    private val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
        // Se añaden las cabeceras
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
            //    .addHeader("Authorization", "Basic ${global.keyApi}")
            chain.proceed(request.build())
        }
        .build()


    //Creacion de Retrofid
    private val retrofid = Retrofit.Builder()
        .baseUrl(Endpoint) //
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    //Crear una variable la cual invoca la interfaz
    val service = retrofid.create(APIService::class.java)


    fun getCharacters(currentPage: Int, onResult: (MainResponse?) -> Unit) {
        //service  userData
        service.getcharacters(currentPage).enqueue(object : Callback<MainResponse> {
            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(
                call: Call<MainResponse>,
                response: Response<MainResponse>
            ) {
                val addedUser = response.body()
                if (addedUser != null) {
                    onResult(addedUser)
                } else {
                    onResult(null)
                }
            }
        }
        )
    }

}