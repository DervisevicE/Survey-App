package ba.etf.rma22.projekat.view

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R


class FragmentPitanje : Fragment() {

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

       val opcijeOdgovora : ArrayList<String> = this.arguments?.getStringArrayList("odgovori") as ArrayList<String>

        adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1,opcijeOdgovora) }!!
        odgovori.adapter = adapter
        //odgovori.setOnItemClickListener(AdapterView.OnItemClickListener())
        odgovori.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val odabrani = opcijeOdgovora[position]
                //view.setBackgroundColor(Color.parseColor("#0000FF"))
                val spanString = SpannableString(odabrani)
                spanString.setSpan(
                    ForegroundColorSpan(Color.parseColor("#0000FF")),0,spanString.length,0
                )

            }

        zaustavi.setOnClickListener {
            //MainActivity.adapterZaVP.removeAll()
            MainActivity.adapterZaVP.add(0,FragmentAnkete())
            MainActivity.adapterZaVP.add(1, FragmentIstrazivanje())
            MainActivity.viewPager.currentItem=0
        }

        return view
    }
    companion object {
        fun newInstance(toString: String): FragmentPoruka = FragmentPoruka()
    }
}