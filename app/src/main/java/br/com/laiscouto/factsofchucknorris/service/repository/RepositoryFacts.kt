package br.com.laiscouto.factsofchucknorris.service.repository

import br.com.laiscouto.factsofchucknorris.service.model.FactsModel
import br.com.laiscouto.factsofchucknorris.service.remote.FactsService
import br.com.laiscouto.factsofchucknorris.service.remote.RetrofitFacts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryFacts {
    private val factsService = RetrofitFacts.createService(FactsService::class.java)

    suspend fun getFacts(facts: String): FactsModel {
        return withContext(Dispatchers.IO){
            factsService.getFacts(facts)
        }

    }
}