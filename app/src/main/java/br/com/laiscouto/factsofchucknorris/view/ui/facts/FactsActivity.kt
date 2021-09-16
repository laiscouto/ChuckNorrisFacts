package br.com.laiscouto.factsofchucknorris.view.ui.facts


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.laiscouto.factsofchucknorris.R
import br.com.laiscouto.factsofchucknorris.constants.Constants.Companion.KEY_FACTS
import br.com.laiscouto.factsofchucknorris.view.ui.search.SearchHostActivity
import br.com.laiscouto.factsofchucknorris.service.model.ResultOfFacts
import org.koin.androidx.viewmodel.ext.android.viewModel

class FactsActivity : AppCompatActivity() {


    private val facts by lazy { intent.extras?.getString(KEY_FACTS) }
    private val factsAdapter = FactsAdapter()
    private val viewModelFacts: FactsViewModel by viewModel()
    private val loading by lazy{findViewById<ProgressBar>(R.id.loading)}
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts_main)

        observeFactsViewModel()
        facts?.let { viewModelFacts.fetchFacts(it) }
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = factsAdapter

    }


    private fun handlerSuccess(listFacts: List<ResultOfFacts>) {
        factsAdapter.updateFacts(listFacts)
    }

    private fun observeFactsViewModel() {
        viewModelFacts.observeState().observe(this, {
            when (it) {
                is FactsState.Loading -> {
                    handlerLoading(true)
                }

                is FactsState.Success -> {
                    handlerLoading(false)
                    handlerSuccess(it.resultsFacts)

                }
                is FactsState.Error -> {
                    handlerLoading(false)
                    renderError()
                }
                is FactsState.Empty ->{
                    handlerLoading(false)
                    renderEmptyState()
                }
            }
        })
    }

    private fun renderEmptyState() {
        val btBack = findViewById<Button>(R.id.empty)
        val chuck = findViewById<ImageView>(R.id.chuck_no)
        chuck.visibility = View.VISIBLE
        btBack.visibility = View.VISIBLE
        btBack.setOnClickListener {
            val intent = Intent(this, SearchHostActivity::class.java)
            startActivity(intent)
        }
    }

    private fun renderError() {
        val btBack = findViewById<Button>(R.id.empty)
        val chuck = findViewById<ImageView>(R.id.chuck_ex)

        chuck.visibility = View.VISIBLE
        btBack.visibility = View.VISIBLE
        btBack.setOnClickListener {
            val intent = Intent(this, SearchHostActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handlerLoading(visible: Boolean){
        if(visible){
            loading.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }else{
            loading.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

}