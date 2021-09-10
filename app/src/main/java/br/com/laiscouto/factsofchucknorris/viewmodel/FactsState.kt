package br.com.laiscouto.factsofchucknorris.viewmodel

import br.com.laiscouto.factsofchucknorris.service.model.FactsModel

sealed class FactsState{
    object Loading: FactsState()
    data class Success(val resultsFacts: FactsModel): FactsState()
    object Error : FactsState()

}
