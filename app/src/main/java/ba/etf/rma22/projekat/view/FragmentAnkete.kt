package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.repositories.ApiAdapter
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class FragmentAnkete : Fragment() {

    private lateinit var sveAnkete: RecyclerView
    private lateinit var sveAnketeAdapter: AnketeListAdapater
    private var anketaListViewModel = AnketaListViewModel()
    private var pitanjeAnketaViewModel = PitanjeAnketaViewModel()
    private lateinit var spiner: Spinner
    private var fragmentPredaj = FragmentPredaj()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_ankete, container, false)
        sveAnkete = view.findViewById(R.id.listaAnketa)
        sveAnkete.layoutManager = GridLayoutManager(activity, 2)
        sveAnketeAdapter = AnketeListAdapater(arrayListOf()){
                prikaziAnketu(it)
        }
        sveAnkete.adapter = sveAnketeAdapter
        //sveAnketeAdapter.updateAnkete(anketaListViewModel.getAnkete())

        spiner = view.findViewById(R.id.filterAnketa)
        activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.vrijednostiZaSpinner,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spiner.adapter = adapter
            }
        }

        spiner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(spiner.selectedItem.toString()=="Sve ankete")
                    anketaListViewModel.getAnkete(onSuccess = ::onSuccess,
                        onError = ::onError)
                else if(spiner.selectedItem.toString()=="Sve moje ankete")
                    anketaListViewModel.getMyAnkete(onSuccess = ::onSuccess,
                        onError = ::onError)
                else if(spiner.selectedItem.toString()=="Urađene ankete")
                    anketaListViewModel.getDone(onSuccess = ::onSuccess,
                        onError = ::onError)
                else if(spiner.selectedItem.toString()=="Buduće ankete")
                    anketaListViewModel.getFuture(onSuccess = ::onSuccess,
                        onError = ::onError)
                else
                    anketaListViewModel.getNotTaken(onSuccess = ::onSuccess,
                        onError = ::onError)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        return view;
    }

    fun onSuccess(ankete : List<Anketa>){
        GlobalScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                sveAnketeAdapter.updateAnkete(ankete)
                sveAnketeAdapter.notifyDataSetChanged()
            }
        }
    }

    fun onError(){
        GlobalScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                val toast = Toast.makeText(context, "Error", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    private fun prikaziAnketu(anketa: Anketa){
         pitanjeAnketaViewModel.getPitanja(anketa.id, onSuccess = ::onSuccessZaPitanja,
            onError = ::onError)
        Log.v("ime", anketa.naziv)
        var bundle = Bundle()
        bundle.putString("nazivAnkete", anketa.naziv)
        fragmentPredaj.arguments = bundle
    }

    private fun onSuccessZaPitanja(pitanjaZaPrikazat: List<Pitanje>){
        var fragmenti : MutableList<Fragment> = arrayListOf()
        if(spiner.selectedItem!="Sve ankete") {
            Log.v("pitanja su", pitanjaZaPrikazat.toString())
            var id: Int = pitanjaZaPrikazat.get(1).anketumId
            if (pitanjaZaPrikazat.isNotEmpty()) {
                MainActivity.adapterZaVP.removeAll()
                for (i in pitanjaZaPrikazat) {
                    var bundle = Bundle()
                    bundle.putString("tekstPitanja", i.tekstPitanja)
                    var odgovoriNaPitanje = i.opcije as ArrayList
                    bundle.putStringArrayList("odgovori", odgovoriNaPitanje)
                    val fragmentPitanje = FragmentPitanje()
                    fragmentPitanje.arguments = bundle
                    fragmenti.add(fragmentPitanje)
                }
                fragmenti.add(fragmentPredaj)
                var pomBrojac = 0
                for (i in fragmenti) {
                    MainActivity.adapterZaVP.add(pomBrojac, i)
                    pomBrojac++
                }
            }
        }
    }

    private fun dajStatus(anketa: Anketa): String {
        var cal: Calendar = Calendar.getInstance()
        cal.set(LocalDate.now().year, LocalDate.now().monthValue, LocalDate.now().dayOfMonth)
        var date: Date = cal.time
        if(anketa.datumRada==null){
            if(anketa.datumPocetak!!.before(date) && anketa.datumKraj!!.after(date)) return "zelena"
            else if(anketa.datumKraj!!.before(date) && anketa.datumKraj!!.before(date)) return "crvena"
            else if (anketa.datumPocetak!!.after(date) && anketa.datumKraj!!.after(date)) return "zuta"
        }
        return "plava"
    }

    companion object {
        fun newInstance(): FragmentAnkete = FragmentAnkete()
    }
}