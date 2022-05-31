package ba.etf.rma22.projekat.view

import android.os.Bundle
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
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.util.*

class FragmentAnkete : Fragment() {

    private lateinit var sveAnkete: RecyclerView
    private lateinit var sveAnketeAdapter: AnketeListAdapater
    private var anketaListViewModel = AnketaListViewModel()
    private var pitanjeAnketaViewModel = PitanjeAnketaViewModel()
    private lateinit var spiner: Spinner

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
                    //sveAnketeAdapter.updateAnkete(anketaListViewModel.getAnkete())
                else if(spiner.selectedItem.toString()=="Sve moje ankete")
                    anketaListViewModel.getMyAnkete(onSuccess = ::onSuccess,
                        onError = ::onError)
                else if(spiner.selectedItem.toString()=="Urađene ankete")
                    sveAnketeAdapter.updateAnkete(anketaListViewModel.getDone())
                else if(spiner.selectedItem.toString()=="Buduće ankete")
                    sveAnketeAdapter.updateAnkete(anketaListViewModel.getFuture())
                else
                    sveAnketeAdapter.updateAnkete(anketaListViewModel.getNotTaken())
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


    private fun prikaziAnketu(anketa: Anketa) {
    /*var fragmenti : MutableList<Fragment> = arrayListOf()
        val statusAnkete = dajStatus(anketa)
        if(statusAnkete!="zuta"  && anketaListViewModel.getMyAnkete().contains(anketa)){
            val pitanjaZaPrikazat = pitanjeAnketaViewModel.getPitanja(anketa.naziv, anketa.nazivIstrazivanja)
            if(pitanjaZaPrikazat.isNotEmpty()){
                MainActivity.adapterZaVP.removeAll()
                for(i in pitanjaZaPrikazat){
                    var bundle = Bundle()
                    bundle.putString("tekstPitanja", i.tekst)

                    val odgovoriNaPitanje = pitanjeAnketaViewModel.getOdgovori(i.naziv)
                    bundle.putStringArrayList("odgovori", odgovoriNaPitanje)

                    val fragmentPitanje = FragmentPitanje()
                    fragmentPitanje.arguments = bundle
                    fragmenti.add(fragmentPitanje)
                }
                val nazivAnkete = anketa.naziv
                val nazivIstrazivanja = anketa.nazivIstrazivanja
                val fragmentPredaj = FragmentPredaj()

                var bundleZaPredaj = Bundle()
                bundleZaPredaj.putString("nazivAnkete", nazivAnkete)
                bundleZaPredaj.putString("nazivIstrazivanja", nazivIstrazivanja)
                fragmentPredaj.arguments = bundleZaPredaj

                fragmenti.add(fragmentPredaj)
                var pomBrojac=0
                for(i in fragmenti){
                    MainActivity.adapterZaVP.add(pomBrojac,i)
                    pomBrojac++
                }
            }
        }*/
    }

    private fun dajStatus(anketa: Anketa): String {
        var cal: Calendar = Calendar.getInstance()
        cal.set(LocalDate.now().year, LocalDate.now().monthValue, LocalDate.now().dayOfMonth)
        var date: Date = cal.time
        if(anketa.datumRada==null){
            if(anketa.datumPocetka.before(date) && anketa.datumKraja.after(date)) return "zelena"
            else if(anketa.datumKraja.before(date) && anketa.datumKraja.before(date)) return "crvena"
            else if (anketa.datumPocetka.after(date) && anketa.datumKraja.after(date)) return "zuta"
        }
        return "plava"
    }

    companion object {
        fun newInstance(): FragmentAnkete = FragmentAnkete()
    }
}