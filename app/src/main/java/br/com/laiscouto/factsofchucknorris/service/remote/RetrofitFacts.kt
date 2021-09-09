package br.com.laiscouto.factsofchucknorris.service.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFacts private constructor()  {

    companion object{

        private lateinit var retrofit: Retrofit
        private val baseUrl = "https://api.chucknorris.io/jokes/search/"

        private fun getInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()
            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun <s>createService(serviceClass:Class <s>):s{
            return getInstance().create(serviceClass)
        }
    }

}