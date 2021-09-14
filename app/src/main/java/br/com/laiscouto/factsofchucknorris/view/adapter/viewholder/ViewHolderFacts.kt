package br.com.laiscouto.factsofchucknorris.view.adapter.viewholder

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.laiscouto.factsofchucknorris.R
import br.com.laiscouto.factsofchucknorris.service.model.ResultOfFacts
import br.com.laiscouto.factsofchucknorris.view.fragments.SearchFragment.Companion.KEY_FACTS

class ViewHolderFacts(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(resultFacts: ResultOfFacts) {

        val facts = itemView.findViewById<TextView>(R.id.fact)
            facts.text = resultFacts.value

        val factsCategory = itemView.findViewById<TextView>(R.id.category)
        factsCategory.text = resultFacts.categories.toString()

        val urlCard = resultFacts.url
        val linkAction = itemView.findViewById<ImageView>(R.id.img_link_direcion)
        linkAction.setOnClickListener{

            val shareBody = "Look is fact about Chuck Norris: $urlCard"
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"

            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody)

           startActivity(itemView.context, shareIntent, null)
        }
    }
}