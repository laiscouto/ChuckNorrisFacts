package br.com.laiscouto.factsofchucknorris.homescreens.fragments
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.laiscouto.factsofchucknorris.view.main.MainActivity
import br.com.laiscouto.factsofchucknorris.R
import br.com.laiscouto.factsofchucknorris.constants.Constants.Companion.KEY_FACTS

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bt = view.findViewById<Button>(R.id.bt_fact)
        bt.setOnClickListener {
            saveSearch()
        }
        val search = view.findViewById<ImageView>(R.id.search)
        search.setOnClickListener {
            saveSearch()
        }
    }

    private fun saveSearch() {
        val receivingOfFact = view?.findViewById<EditText>(R.id.enter_fact)
        val saveFact = receivingOfFact?.text

        if (saveFact.isNullOrEmpty()) {
            Toast.makeText(context, "Hey, enter a fact", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(context, MainActivity::class.java).apply {
                val bundle = Bundle()
                bundle.putString(KEY_FACTS, saveFact.toString())
                putExtras(bundle)
            }

            startActivity(intent)
        }
    }
}

