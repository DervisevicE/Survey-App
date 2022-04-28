package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel
import ba.etf.rma22.projekat.viewmodel.GrupaViewModel
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeViewModel

class FragmentIstrazivanje : Fragment(){

    private lateinit var odabirGodina: Spinner
    private lateinit var odabirIstrazivanja: Spinner
    private lateinit var odabirGrupa: Spinner
    private lateinit var upisDugme: Button
    private var istrazivanjeViewModel = IstrazivanjeViewModel()
    private var grupaViewModel = GrupaViewModel()
    private var anketaViewModel = AnketaListViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_istrazivanje, container, false)
        odabirGodina = view.findViewById(R.id.odabirGodina)
        odabirIstrazivanja = view.findViewById(R.id.odabirIstrazivanja)
        odabirGrupa =  view.findViewById(R.id.odabirGrupa)
        upisDugme =  view.findViewById(R.id.dodajIstrazivanjeDugme)


        activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.godine,
                android.R.layout.simple_spinner_item
            ).also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                odabirGodina.adapter = adapter
            }
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

        upisDugme.setOnClickListener{
            anketaViewModel.dodajUMojeAnkete(odabirIstrazivanja.selectedItem.toString(),odabirGrupa.selectedItem.toString())
            istrazivanjeViewModel.dodajUMojaIstrazivanja(odabirIstrazivanja.selectedItem.toString(),odabirGodina.selectedItem.toString().toInt())
            var bundle = Bundle()
            bundle.putString("poruka", "Uspješno ste upisani u grupu " + odabirGrupa.selectedItem.toString() + " istraživanja "
                    + odabirIstrazivanja.selectedItem.toString() + "!" )
            val porukaFragment = FragmentPoruka()
            porukaFragment.arguments=bundle
            MainActivity.adapterZaVP.refreshFragment(1, porukaFragment)
        }
                return view;
    }

    private fun updateGrupa(spinner: Spinner) {
        val grupePoIstrazivanju = grupaViewModel.getGroupsByIstrazivanje(odabirIstrazivanja.selectedItem.toString())
        val adapter = activity?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                grupePoIstrazivanju
            )
        }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter=adapter
    }

    private fun updateIstrazivanja(spinner: Spinner) {
        val istrazivanjaPoGodini = istrazivanjeViewModel.getIstrazivanjeByGodina(odabirGodina.selectedItem.toString().toInt())
        val adapter = activity?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                istrazivanjaPoGodini
            )
        }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=adapter
    }

    private fun postaviFunkcionalnostDugmica(){
        upisDugme.isEnabled = odabirGodina.selectedItem.toString() != ""
                && odabirIstrazivanja.selectedItem.toString() != ""
                && odabirGrupa.selectedItem.toString() != ""
    }

    companion object {
        fun newInstance(): FragmentIstrazivanje = FragmentIstrazivanje()
    }
}