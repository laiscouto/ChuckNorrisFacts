package br.com.laiscouto.factsofchucknorris.service.remote

import br.com.laiscouto.factsofchucknorris.service.model.FactsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface FactsService {

    @GET("jokes/search/")
    suspend fun getFacts(
        @Query("query") query: String) : FactsModel
}