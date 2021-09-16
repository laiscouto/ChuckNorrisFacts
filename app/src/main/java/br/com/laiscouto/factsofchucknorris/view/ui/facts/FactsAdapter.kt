package br.com.laiscouto.factsofchucknorris.view.ui.facts


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import br.com.laiscouto.factsofchucknorris.R
import br.com.laiscouto.factsofchucknorris.service.model.ResultOfFacts

class FactsAdapter(): RecyclerView.Adapter<FactsViewHolder>() {

    private var facts: List<ResultOfFacts> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val item = LayoutInflater.from(parent.context)
                .inflate(R.layout.form_recycler, parent, false)
        return FactsViewHolder(item)
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        holder.bind(facts[position])
    }

    override fun getItemCount(): Int {
        return facts.count()
    }

    fun updateFacts(newListFacts: List<ResultOfFacts>){
        facts = newListFacts
        notifyDataSetChanged()
    }
}