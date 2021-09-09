package br.com.laiscouto.factsofchucknorris.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.laiscouto.factsofchucknorris.service.model.FactsModel
import br.com.laiscouto.factsofchucknorris.service.model.ResultOfFacts
import br.com.laiscouto.factsofchucknorris.service.repository.RepositoryFacts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FactsViewModel(private val factsRepository: RepositoryFacts) : ViewModel() {
    private val facts: String = ""
    private val resultsValues = mutableListOf<ResultOfFacts>()
    private val state = MutableLiveData<FactsState>()

    fun observeState(): LiveData<FactsState> = state

    fun fetchFacts(){
        viewModelScope.launch(Dispatchers.Main){
            state.postValue(FactsState.Loading)
            try {
                val result = factsRepository.getFacts(facts)
                resultsValues.addAll(result.results)
                handleSuccess(result)
            }  catch (e:Exception){
                handleError()
            }
        }
    }

    private fun handleSuccess(result: FactsModel){
        state.postValue(FactsState.Success(result))
    }

    private fun handleError(){
        state.postValue(FactsState.Error)

    }

}