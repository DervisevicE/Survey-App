package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.ApiAdapter
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeIGrupaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentIstrazivanje : Fragment(){

    private lateinit var odabirGodina: Spinner
    private lateinit var odabirIstrazivanja: Spinner
    private lateinit var odabirGrupa: Spinner
    private lateinit var upisDugme: Button
    //private var istrazivanjeViewModel = IstrazivanjeViewModel()
    //private var grupaViewModel = GrupaViewModel()
    private var istrazivanjeIGrupaViewModel = IstrazivanjeIGrupaViewModel()
    private var anketaViewModel = AnketaListViewModel()

    private lateinit var viewPom : View
    private lateinit var adapter1 : ArrayAdapter<Istrazivanje>
    private lateinit var adapter2 : ArrayAdapter<Grupa>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewPom =  inflater.inflate(R.layout.fragment_istrazivanje, container, false)
        odabirGodina = viewPom.findViewById(R.id.odabirGodina)
        odabirIstrazivanja = viewPom.findViewById(R.id.odabirIstrazivanja)
        odabirGrupa =  viewPom.findViewById(R.id.odabirGrupa)
        upisDugme =  viewPom.findViewById(R.id.dodajIstrazivanjeDugme)

        ArrayAdapter.createFromResource(
            viewPom.context,
            R.array.godine,
            android.R.layout.simple_spinner_item
        ).also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        odabirGodina.adapter=adapter
        }

        adapter1 = ArrayAdapter(
            viewPom.context, android.R.layout.simple_spinner_item, ArrayList<Istrazivanje>()
        )
        odabirIstrazivanja.adapter = adapter1


         adapter2 = ArrayAdapter(
            viewPom.context,
            android.R.layout.simple_spinner_item,
            ArrayList<Grupa>()
        )
        odabirGrupa.adapter = adapter2

        odabirGodina.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (odabirGodina.selectedItem.toString()!=""){
                    odabirIstrazivanja.isEnabled=true
                    updateIstrazivanja()
                } else{
                    odabirIstrazivanja.isEnabled=false
                    odabirGrupa.isEnabled=false
                    upisDugme.isEnabled=false
                    return;
                }
                updateGrupa()
                postaviFunkcionalnostDugmica()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                updateIstrazivanja()
                updateGrupa()
            }
        }

        odabirIstrazivanja.onItemSelectedListener= object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(odabirIstrazivanja.selectedItem.toString()!=""){
                    odabirGrupa.isEnabled=true
                    updateGrupa()
                } else {
                    odabirGrupa.isEnabled=false
                    //upisDugme.isEnabled=false
                }
                postaviFunkcionalnostDugmica()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                updateGrupa()
                //upisDugme.isEnabled=false

            }
        }

       odabirGrupa.onItemSelectedListener= object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                postaviFunkcionalnostDugmica()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //upisDugme.isEnabled=false
            }
        }

        upisDugme.setOnClickListener{
            anketaViewModel.dodajUMojeAnkete(odabirIstrazivanja.selectedItem.toString(),odabirGrupa.selectedItem.toString())
            //istrazivanjeViewModel.dodajUMojaIstrazivanja(odabirIstrazivanja.selectedItem.toString(),odabirGodina.selectedItem.toString().toInt())
            var bundle = Bundle()
            bundle.putString("poruka", "Uspješno ste upisani u grupu " + odabirGrupa.selectedItem.toString() + " istraživanja "
                    + odabirIstrazivanja.selectedItem.toString() + "!" )
            val porukaFragment = FragmentPoruka()
            porukaFragment.arguments=bundle
            MainActivity.adapterZaVP.refreshFragment(1, porukaFragment)
        }
                return viewPom;
    }

    private fun updateIstrazivanja() {
        istrazivanjeIGrupaViewModel.getIstrazivanjeByGodina(odabirGodina.selectedItem.toString().toInt(), onSuccess = ::onSuccessZaIstrazivanja,
        onError = ::onError)
    }

    private fun onSuccessZaIstrazivanja(istrazivanja: List<Istrazivanje>){
        //GlobalScope.launch(Dispatchers.Main){
            adapter1.addAll(istrazivanja)
            adapter1.notifyDataSetChanged()
        //}
    }

    private fun updateGrupa(){
        if(odabirIstrazivanja.selectedItem == null){
            val grupe = emptyArray<String>()
            val adapater = ArrayAdapter(
                viewPom.context,
                android.R.layout.simple_spinner_item,
                grupe
            )
            adapater.setDropDownViewResource(android.R.layout.simple_spinner_item)
            odabirGrupa.adapter=adapater
        }else
            istrazivanjeIGrupaViewModel.getGrupeZaIstrazivanje((odabirIstrazivanja.selectedItem as Istrazivanje).id, onSuccess = ::onSuccessZaGrupe,
                onError = ::onError)
    }

    private fun onSuccessZaGrupe(grupe : List<Grupa>){
        adapter2.addAll(grupe)
        adapter2.notifyDataSetChanged()
    }

    private fun onError(){
        GlobalScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                val toast = Toast.makeText(context, "Error", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }




    private fun postaviFunkcionalnostDugmica(){
        /*upisDugme.isEnabled = odabirGodina.selectedItem.toString() != ""
                && odabirIstrazivanja.selectedItem.toString() != ""
                && odabirGrupa.selectedItem.toString() != ""*/
    }

    companion object {
        fun newInstance(): FragmentIstrazivanje = FragmentIstrazivanje()
    }
}