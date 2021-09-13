package br.com.laiscouto.factsofchucknorris.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.laiscouto.factsofchucknorris.service.model.ResultOfFacts
import br.com.laiscouto.factsofchucknorris.service.repository.RepositoryFacts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FactsViewModel(private val factsRepository: RepositoryFacts) : ViewModel() {

    private val state = MutableLiveData<FactsState>()
    private val resultsValues = mutableListOf<ResultOfFacts>()


    fun observeState(): LiveData<FactsState> = state

    fun fetchFacts(facts:String){
        viewModelScope.launch(Dispatchers.Main){
            state.postValue(FactsState.Loading)
            try {
                val result = factsRepository.getFacts(facts)
                resultsValues.addAll(result.result)
                handleSuccess(resultsValues)
            }  catch (e:Exception){
                handleError()
            }
        }
    }

    private fun handleSuccess(list: List<ResultOfFacts>){
        state.postValue(FactsState.Success(list))
    }

    private fun handleError(){
        state.postValue(FactsState.Error)

    }

}