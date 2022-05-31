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
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel
import java.time.LocalDate
import java.util.*
import kotlin.math.roundToInt

class FragmentPredaj : Fragment(){

    private lateinit var progres : TextView
    private lateinit var  dugmePredaj : Button
    private var anketaListViewModel = AnketaListViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_predaj, container, false)
        progres = view.findViewById(R.id.progresTekst)
        dugmePredaj = view.findViewById(R.id.dugmePredaj)

        val nazivAnkete = this.arguments?.getString("nazivAnkete")
        val nazivIstrazivanja = this.arguments?.getString("nazivIstrazivanja")

        progres.setText("")
        var odgovorena = FragmentPitanje.brojOdgovorenih
        var brojMogucihOdgovora = FragmentPitanje.brojOpcija

        var vrijednostZaSvakiOdgovor : Float = 100.toFloat()/brojMogucihOdgovora
        var pomocnaVrijednost = vrijednostZaSvakiOdgovor/100
        var vrijednost : Int = (pomocnaVrijednost*odgovorena*10).roundToInt()
        if(vrijednost%2!=0) vrijednost+=1
        vrijednost*=10
        progres.setText(vrijednost.toString()+"%")

        dugmePredaj.setOnClickListener {

            var bundle = Bundle()
            bundle.putString("poruka", "Završili ste anketu" + nazivAnkete + " u okviru istraživanja " + nazivIstrazivanja + ".")
            val porukaFragment = FragmentPoruka()
            porukaFragment.arguments=bundle
            MainActivity.adapterZaVP.removeAll()
            MainActivity.adapterZaVP.add(0, FragmentAnkete())
            MainActivity.adapterZaVP.add(1, porukaFragment)
            MainActivity.viewPager.currentItem=1

            /*anketaListViewModel.getMyAnkete().filter { anketa -> anketa.naziv==nazivAnkete && anketa.nazivIstrazivanja==nazivIstrazivanja }.first().progres =
                vrijednost.toFloat()
*/
            var cal: Calendar = Calendar.getInstance()
            cal.set(LocalDate.now().year, LocalDate.now().monthValue, LocalDate.now().dayOfMonth)
            var date: Date = cal.time

           // anketaListViewModel.getMyAnkete().filter { anketa -> anketa.naziv==nazivAnkete && anketa.nazivIstrazivanja==nazivIstrazivanja }.first().datumRada = date

        }
        return view
    }
    companion object {
        fun newInstance(): FragmentPredaj = FragmentPredaj()
    }
}