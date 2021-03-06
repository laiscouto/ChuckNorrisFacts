package br.com.laiscouto.factsofchucknorris.view.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import br.com.laiscouto.factsofchucknorris.R

class DirectSearchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_direct_search, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bt = view.findViewById<Button>(R.id.button_inicial)
        bt?.setOnClickListener{
            findNavController().navigate(R.id.action_firstDirectFragment_to_searchActivity)
        }

    }
}