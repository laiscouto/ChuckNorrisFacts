package br.com.laiscouto.factsofchucknorris.view.ui.facts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.laiscouto.factsofchucknorris.service.model.ResultOfFacts
import br.com.laiscouto.factsofchucknorris.service.repository.RepositoryFacts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class FactsViewModel(private val factsRepository: RepositoryFacts) : ViewModel() {

    private val state = MutableLiveData<FactsState>()
    private val resultsValues = mutableListOf<ResultOfFacts>()

    fun observeState(): LiveData<FactsState> = state

    fun fetchFacts(facts: String) {
        viewModelScope.launch(Dispatchers.Main) {
            state.postValue(FactsState.Loading)
            try {
                val results = factsRepository.getFacts(facts)
                if (results.result.isEmpty()) {
                    handleEmpty()
                } else {
                    resultsValues.addAll(results.result)
                    handleSuccess(resultsValues)
                }
            } catch (time: TimeoutException) {
                handleError()
            } catch (unknown: UnknownHostException) {
                handleError()
            } catch (socket: SocketTimeoutException) {
                handleError()
            }
        }
    }

    private fun handleSuccess(list: List<ResultOfFacts>) {
        state.postValue(FactsState.Success(list))
    }

    private fun handleError() {
        state.postValue(FactsState.Error)

    }
    private fun handleEmpty(){
        state.postValue(FactsState.Empty)
    }


}