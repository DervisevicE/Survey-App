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

class FragmentPitanje() : Fragment() {

    private lateinit var pitanja : TextView
    private lateinit var odgovori : ListView
    private lateinit var zaustavi : Button
    private lateinit var adapter : ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_pitanje, container, false)
        pitanja = view.findViewById(R.id.tekstPitanja)
        odgovori = view.findViewById(R.id.odgovoriLista)
        zaustavi = view.findViewById(R.id.dugmeZaustavi)

        val tekstPitanja = this.arguments?.getString("tekstPitanja")
        pitanja.text = tekstPitanja

        return view
    }
    companion object {
        fun newInstance(toString: String): FragmentPoruka = FragmentPoruka()
    }
}