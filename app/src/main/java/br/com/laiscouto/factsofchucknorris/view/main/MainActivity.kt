package br.com.laiscouto.factsofchucknorris.view.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.laiscouto.factsofchucknorris.R
import br.com.laiscouto.factsofchucknorris.constants.Constants.Companion.KEY_FACTS
import br.com.laiscouto.factsofchucknorris.service.model.ResultOfFacts
import br.com.laiscouto.factsofchucknorris.view.adapter.FactsAdapter
import br.com.laiscouto.factsofchucknorris.viewmodel.FactsState
import br.com.laiscouto.factsofchucknorris.viewmodel.FactsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.droidsonroids.gif.GifImageView

class MainActivity : AppCompatActivity() {


    private val facts by lazy { intent.extras?.getString(KEY_FACTS) }
    private val factsAdapter = FactsAdapter()
    private val viewModelFacts : FactsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts_main)

        observeFactsViewModel()
        facts?.let { viewModelFacts.fetchFacts(it) }
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = factsAdapter
        infoFactsEmpty()

    }


    private fun handlerSuccess(listFacts: List<ResultOfFacts>) {
        factsAdapter.updateFacts(listFacts)
    }

    private fun observeFactsViewModel() {
        viewModelFacts.observeState().observe(this, {
            when (it) {
                is FactsState.Loading -> {
                    Toast.makeText(this, "LOADING", Toast.LENGTH_SHORT).show()
                }

                is FactsState.Success -> {
                    handlerSuccess(it.resultsFacts)
                }
                is FactsState.Error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun infoFactsEmpty(){
        val text = findViewById<TextView>(R.id.facts_empty)
        val chuck = findViewById<GifImageView>(R.id.chuck)
        chuck.visibility = View.VISIBLE
        text.visibility = View.VISIBLE
        viewModelFacts.textIsEmpty.observe(this,{
            text.text = it
        })
    }

}