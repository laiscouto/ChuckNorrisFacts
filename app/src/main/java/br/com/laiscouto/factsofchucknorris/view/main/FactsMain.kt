package br.com.laiscouto.factsofchucknorris.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.laiscouto.factsofchucknorris.R
import br.com.laiscouto.factsofchucknorris.service.model.ResultOfFacts
import br.com.laiscouto.factsofchucknorris.service.repository.RepositoryFacts
import br.com.laiscouto.factsofchucknorris.view.adapter.FactsAdapter
import br.com.laiscouto.factsofchucknorris.view.fragments.SearchFragment.Companion.KEY_FACTS
import br.com.laiscouto.factsofchucknorris.viewmodel.FactsState
import br.com.laiscouto.factsofchucknorris.viewmodel.FactsViewModel

class FactsMain : AppCompatActivity() {

    private val facts by lazy { intent.extras?.getString(KEY_FACTS) }
    private val factsAdapter = FactsAdapter()
    private val repository = RepositoryFacts()
    private val viewModel = FactsViewModel(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts_main)

        facts?.let { viewModel.fetchFacts(it) }
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = factsAdapter
        observeFactsViewModel()
    }

    private fun handlerSuccess(listFacts: List<ResultOfFacts>){
        factsAdapter.updateFacts(listFacts)
    }

    private fun observeFactsViewModel(){
        viewModel.observeState().observe(this,{
            when (it) {
                is FactsState.Loading -> {
                    Toast.makeText(this, "LOADING", Toast.LENGTH_SHORT).show()
                }

                is FactsState.Success ->{
                    handlerSuccess(it.resultsFacts)
                }
                is FactsState.Error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}