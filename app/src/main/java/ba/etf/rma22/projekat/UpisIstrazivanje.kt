package ba.etf.rma22.projekat

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ba.etf.rma22.projekat.data.istrazivanja
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository
import ba.etf.rma22.projekat.viewmodel.GrupaViewModel
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeViewModel
import java.util.stream.Collectors

class UpisIstrazivanje: AppCompatActivity() {

    private lateinit var odabirGodina: Spinner
    private lateinit var odabirIstrazivanja: Spinner
    private lateinit var odabirGrupa: Spinner
    private lateinit var upisDugme: Button
    private var istrazivanjeViewModel = IstrazivanjeViewModel()
    private var grupaViewModel = GrupaViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upis_istrazivanje)

        odabirGodina = findViewById(R.id.odabirGodina)
        odabirIstrazivanja = findViewById(R.id.odabirIstrazivanja)
        odabirGrupa = findViewById(R.id.odabirGrupa)
        upisDugme = findViewById(R.id.dodajIstrazivanjeDugme)

        ArrayAdapter.createFromResource(
            this,
            R.array.godine,
            android.R.layout.simple_spinner_item
        ).also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        odabirGodina.adapter = adapter
        }

        odabirGodina.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (odabirGodina.selectedItem.toString()!=""){
                    odabirIstrazivanja.isEnabled=true
                    updateIstrazivanja(odabirIstrazivanja)
                } else{
                    odabirIstrazivanja.isEnabled=false
                    odabirGrupa.isEnabled=false
                    upisDugme.isEnabled=false
                    return;
                }
                updateGrupa(odabirGrupa)
                postaviFunkcionalnostDugmica()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                updateIstrazivanja(odabirIstrazivanja)
                updateGrupa(odabirGrupa)
            }
        }

        odabirIstrazivanja.onItemSelectedListener= object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(odabirIstrazivanja.selectedItem.toString()!=""){
                    odabirGrupa.isEnabled=true
                    updateGrupa(odabirGrupa)
                } else {
                    odabirGrupa.isEnabled=false
                    //upisDugme.isEnabled=false
                }
                postaviFunkcionalnostDugmica()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                updateGrupa(odabirGrupa)
                //upisDugme.isEnabled=false

            }
        }

        odabirGrupa.onItemSelectedListener= object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                postaviFunkcionalnostDugmica()
                /*if(odabirGrupa.selectedItem.toString()!="")
                    upisDugme.isEnabled=true*/
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //upisDugme.isEnabled=false
            }
        }
    }

    private fun updateGrupa(spinner: Spinner) {
        val grupePoIstrazivanju = grupaViewModel.getGroupsByIstrazivanje(odabirIstrazivanja.selectedItem.toString())
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            grupePoIstrazivanju
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter=adapter
    }

    private fun updateIstrazivanja(spinner: Spinner) {
        val istrazivanjaPoGodini = istrazivanjeViewModel.getIstrazivanjeByGodina(odabirGodina.selectedItem.toString().toInt())
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            istrazivanjaPoGodini
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=adapter
    }

    private fun postaviFunkcionalnostDugmica(){
        upisDugme.isEnabled = odabirGodina.selectedItem.toString() != ""
                && odabirIstrazivanja.selectedItem.toString() != ""
                && odabirGrupa.selectedItem.toString() != ""
    }
}
