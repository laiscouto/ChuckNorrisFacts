package br.com.laiscouto.factsofchucknorris.view.ui.main.viewmodelmain

import br.com.laiscouto.factsofchucknorris.service.model.ResultOfFacts

sealed class FactsState{
    object Loading: FactsState()
    data class Success(val resultsFacts: List<ResultOfFacts>): FactsState()
    object Error : FactsState()

}
