package ba.etf.rma22.projekat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.view.AnketeListAdapater
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var sveAnkete: RecyclerView
    private lateinit var sveAnketeAdapter: AnketeListAdapater
    private var anketaListViewModel = AnketaListViewModel()
    private lateinit var spiner: Spinner
    private lateinit var dugme: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sveAnkete = findViewById(R.id.listaAnketa)
        sveAnkete.layoutManager = GridLayoutManager(
            this, 2, GridLayoutManager.VERTICAL,false
        )
        sveAnketeAdapter = AnketeListAdapater(listOf())
        sveAnkete.adapter = sveAnketeAdapter
        sveAnketeAdapter.updateAnkete(anketaListViewModel.getAnkete())

        spiner = findViewById(R.id.filterAnketa)
        ArrayAdapter.createFromResource(
            this,
            R.array.vrijednostiZaSpinner,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spiner.adapter = adapter
        }

        spiner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(spiner.selectedItem.toString()=="Sve moje ankete")
                    sveAnketeAdapter.updateAnkete(anketaListViewModel.getMyAnkete())
                else if(spiner.selectedItem.toString()=="Sve ankete")
                    sveAnketeAdapter.updateAnkete(anketaListViewModel.getAnkete())
                else if(spiner.selectedItem.toString()=="Urađene ankete")
                    sveAnketeAdapter.updateAnkete(anketaListViewModel.getDone())
                else if(spiner.selectedItem.toString()=="Buduće ankete")
                    sveAnketeAdapter.updateAnkete(anketaListViewModel.getFuture())
                else
                    sveAnketeAdapter.updateAnkete(anketaListViewModel.getNotTaken())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        dugme = findViewById(R.id.upisDugme)
        dugme.setOnClickListener { showUpisIstrazivanje() }

    }

    private fun showUpisIstrazivanje() {
        val intent = Intent(this,UpisIstrazivanje::class.java)
        startActivity(intent)
    }

}