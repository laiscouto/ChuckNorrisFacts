package br.com.laiscouto.factsofchucknorris.view.main.adaptermain


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import br.com.laiscouto.factsofchucknorris.R
import br.com.laiscouto.factsofchucknorris.service.model.ResultOfFacts
import br.com.laiscouto.factsofchucknorris.view.main.adaptermain.viewholdermain.ViewHolderFacts

class FactsAdapter(): RecyclerView.Adapter<ViewHolderFacts>() {

    private var facts: List<ResultOfFacts> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFacts {
        val item = LayoutInflater.from(parent.context)
                .inflate(R.layout.form_recycler, parent, false)
        return ViewHolderFacts(item)
    }

    override fun onBindViewHolder(holder: ViewHolderFacts, position: Int) {
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