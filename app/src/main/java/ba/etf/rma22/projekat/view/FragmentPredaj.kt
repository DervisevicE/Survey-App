package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.R

class FragmentPredaj : Fragment(){

    private lateinit var progres : TextView
    private lateinit var  predaj : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_predaj, container, false)
        progres = view.findViewById(R.id.progresTekst)
        predaj = view.findViewById(R.id.dugmePredaj)

        predaj.setOnClickListener {

        }


        return view
    }
    companion object {
        fun newInstance(toString: String): FragmentPoruka = FragmentPoruka()
    }
}