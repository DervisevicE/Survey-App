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
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R

class FragmentPredaj : Fragment(){

    private lateinit var progres : TextView
    private lateinit var  predaj : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_predaj, container, false)
        progres = view.findViewById(R.id.progresTekst)
        predaj = view.findViewById(R.id.dugmePredaj)

        val nazivAnkete = this.arguments?.getString("nazivAnkete")
        val nazivIstrazivanja = this.arguments?.getString("nazivIstrazivanja")
        val pozicija = this.arguments?.getString("pozicija")
        val pozicijaInt = pozicija?.toInt()

        predaj.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("poruka", "Završili ste anketu " + nazivAnkete + " u okviru istraživanja " + nazivIstrazivanja + ".")
            val porukaFragment = FragmentPoruka()
            porukaFragment.arguments=bundle
            if (pozicijaInt != null) {
                MainActivity.adapterZaVP.refreshFragment(pozicijaInt, porukaFragment)
            }
        }



        return view
    }
    companion object {
        fun newInstance(toString: String): FragmentPoruka = FragmentPoruka()
    }
}