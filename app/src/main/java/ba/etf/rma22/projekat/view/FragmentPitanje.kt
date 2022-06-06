package ba.etf.rma22.projekat.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
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
        //val opcijeOdgovora = this.arguments?.getString("odgovori")
        Log.v("opcije odg su ", opcijeOdgovora.toString())
        //brojOpcija = opcijeOdgovora.size

        /*adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1,opcijeOdgovora) }!!
        odgovori.adapter = adapter
        odgovori.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val odabrani = view.findViewById(android.R.id.text1) as TextView
                odabrani.setTextColor(Color.parseColor("#0000FF"))
                brojOdgovorenih++
            }
*/
        zaustavi.setOnClickListener {
            MainActivity.adapterZaVP.removeAll()
            MainActivity.adapterZaVP.add(0,FragmentAnkete())
            MainActivity.adapterZaVP.add(1, FragmentIstrazivanje())
            MainActivity.viewPager.currentItem=0
        }

        return view
    }
    companion object {
        fun newInstance(): FragmentPitanje = FragmentPitanje()
        var brojOdgovorenih : Int = 0
        var brojOpcija : Int = 0
    }
}
