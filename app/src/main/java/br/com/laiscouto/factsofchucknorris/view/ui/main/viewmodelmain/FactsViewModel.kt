package br.com.laiscouto.factsofchucknorris.view.ui.main.viewmodelmain

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
    private val resultEmpty = MutableLiveData<Boolean>()
    private val resultExcep = MutableLiveData<Boolean>()

    var textIsEmpty = resultEmpty
    var exeception = resultExcep


    fun observeState(): LiveData<FactsState> = state

    fun fetchFacts(facts:String){
        viewModelScope.launch(Dispatchers.Main){
            state.postValue(FactsState.Loading)
            try {
                val results = factsRepository.getFacts(facts)
                if(results.result.isEmpty()) {
                    textIsEmpty.value = true
                }else{
                    resultsValues.addAll(results.result)
                    handleSuccess(resultsValues)
                }
            } catch (time: TimeoutException){
                exeception.value = true
            } catch (unknown: UnknownHostException){
                exeception.value = true
            } catch (socket: SocketTimeoutException){
                exeception.value = true
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