package br.com.laiscouto.factsofchucknorris.view.fragments
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import br.com.laiscouto.factsofchucknorris.view.main.MainActivity
import br.com.laiscouto.factsofchucknorris.R

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
        bt.setOnClickListener{
            saveSearch()
        }

    }
    private fun saveSearch(){
        val fact = view?.findViewById<EditText>(R.id.enter_fact)
        val saveFact = fact?.text
        val intent = Intent(context, MainActivity::class.java).apply {
            val bundle = Bundle()
            bundle.putString(KEY_FACTS, saveFact.toString())
            putExtras(bundle)
        }
        startActivity(intent)
    }
    companion object{
        const val KEY_FACTS = "facts"
    }

}

