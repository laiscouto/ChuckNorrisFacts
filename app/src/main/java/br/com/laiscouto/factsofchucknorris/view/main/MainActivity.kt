package br.com.laiscouto.factsofchucknorris.view.main


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.laiscouto.factsofchucknorris.R
import br.com.laiscouto.factsofchucknorris.constants.Constants.Companion.KEY_FACTS
import br.com.laiscouto.factsofchucknorris.homescreens.HostActivity
import br.com.laiscouto.factsofchucknorris.service.model.ResultOfFacts
import br.com.laiscouto.factsofchucknorris.view.main.adaptermain.FactsAdapter
import br.com.laiscouto.factsofchucknorris.view.main.viewmodelmain.FactsState
import br.com.laiscouto.factsofchucknorris.view.main.viewmodelmain.FactsViewModel
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
        infoException()

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
        val btBack = findViewById<Button>(R.id.empty)
        val chuck = findViewById<ImageView>(R.id.chuck_no)

        viewModelFacts.textIsEmpty.observe(this,{
            chuck.visibility = View.VISIBLE
            btBack.visibility = View.VISIBLE
            btBack.setOnClickListener {
                val intent = Intent(this, HostActivity::class.java)
                startActivity(intent)
            }
        })
    }
    private fun infoException(){
        val btBye = findViewById<Button>(R.id.exception)
        val chuck = findViewById<ImageView>(R.id.chuck_ex)
        viewModelFacts.exeception.observe(this,{
            chuck.visibility = View.VISIBLE
            btBye.visibility = View.VISIBLE
            btBye.setOnClickListener {  }
        })
    }

}