package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentAnkete : Fragment() {

    private lateinit var sveAnkete: RecyclerView
    private lateinit var sveAnketeAdapter: AnketeListAdapater
    private var anketaListViewModel = AnketaListViewModel()
    private lateinit var spiner: Spinner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_ankete, container, false)
        sveAnkete = view.findViewById(R.id.listaAnketa)
       /* sveAnkete.layoutManager = GridLayoutManager(
            this, 2, GridLayoutManager.VERTICAL,false
        )*/
        sveAnkete.layoutManager = GridLayoutManager(activity, 2)
        sveAnketeAdapter = AnketeListAdapater(listOf())
        sveAnkete.adapter = sveAnketeAdapter
        sveAnketeAdapter.updateAnkete(anketaListViewModel.getAnkete())

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
                    sveAnketeAdapter.updateAnkete(anketaListViewModel.getAnkete())
                else if(spiner.selectedItem.toString()=="Sve moje ankete")
                    sveAnketeAdapter.updateAnkete(anketaListViewModel.getMyAnkete())
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
    companion object {
        fun newInstance(): FragmentIstrazivanje = FragmentIstrazivanje()
    }
}