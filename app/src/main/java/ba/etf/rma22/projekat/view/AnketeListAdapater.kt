package ba.etf.rma22.projekat.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel
import java.time.LocalDate
import java.util.*
import java.util.stream.Collectors
import kotlin.math.roundToInt

class AnketeListAdapater(
    private var ankete: List<Anketa>,
    private val onItemClicked : (anketa: Anketa) -> Unit
    ): RecyclerView.Adapter<AnketeListAdapater.AnketeViewHolder>() {

    private var anketaListViewModel = AnketaListViewModel()

    inner class AnketeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val anketaName: TextView = itemView.findViewById(R.id.nazivAnkete)
        val istrazivanjeName: TextView = itemView.findViewById(R.id.nazivIstrazivanja)
        val anketaImage: ImageView = itemView.findViewById(R.id.statusAnkete)
        val progresbar: ProgressBar = itemView.findViewById(R.id.progresZavrsetka)
        val datum: TextView = itemView.findViewById(R.id.datum)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnketeViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.element_anketa,parent,false)
        return AnketeViewHolder(view)
    }


    override fun onBindViewHolder(holder: AnketeViewHolder, position: Int) {
        holder.anketaName.text = ankete[position].naziv
        holder.istrazivanjeName.text = ankete[position].nazivIstrazivanja

        val context: Context = holder.anketaImage.context
        var statusBoja: String = dajStatus(ankete[position])
        //val statusBoja: String = "zelena"

        var id: Int = context.resources.getIdentifier(statusBoja, "drawable", context.packageName)
        holder.anketaImage.setImageResource(id)

        //if (ankete[position].nazivIstrazivanja.isNullOrEmpty())
            holder.istrazivanjeName.text = "Nepoznato"

        if(ankete[position].datumKraj!=null) {
            if (statusBoja == "plava") holder.datum.text =
                "Anketa urađena"
            else if (statusBoja == "zelena") holder.datum.text =
                "Vrijeme zatvaranja: " + formatirajDatum(ankete[position].datumKraj)
            else if (statusBoja == "zuta") holder.datum.text =
                "Vrijeme otvaranja: " + formatirajDatum(ankete[position].datumPocetak)
            else holder.datum.text =
                "Anketa zatvorena: " + formatirajDatum(ankete[position].datumKraj)
        } else{
            holder.datum.text ="Datum pocetka ankete je: " + formatirajDatum(ankete[position].datumPocetak)
        }
       /* var vrijednost: Int = (ankete[position].progres*10).roundToInt()
        if(vrijednost%2!=0) vrijednost+=1
        holder.progresbar.setProgress(vrijednost)
*/
        holder.itemView.setOnClickListener{onItemClicked(ankete[position])}
    }

    private fun formatirajDatum(datum: Date?): String {
        var dan = datum!!.date
        var mjesec = datum!!.month+1
        var godina=datum!!.year
        godina+=1900
        var godinaIspis: String = godina.toString()
        var danIspis: String
        var mjesecIspis: String

        if(dan<10)  danIspis = "0"+dan.toString()+"."
        else danIspis = dan.toString()+"."
        if(mjesec<10)  mjesecIspis = "0"+mjesec.toString()+"."
        else mjesecIspis = mjesec.toString()+"."

        return danIspis+mjesecIspis+godinaIspis

    }

    private fun dajStatus(anketa: Anketa): String {
        var cal: Calendar = Calendar.getInstance()
        cal.set(LocalDate.now().year, LocalDate.now().monthValue, LocalDate.now().dayOfMonth)
        var date: Date = cal.time
       // if(anketa.datumRada==null){
            if(anketa.datumKraj!=null) {
                if (anketa.datumPocetak!!.before(date) && anketa.datumKraj!!.after(date)) return "zelena"
                else if (anketa.datumKraj!!.before(date) && anketa.datumKraj!!.before(date)) return "crvena"
                else if (anketa.datumPocetak!!.after(date) && anketa.datumKraj!!.after(date)) return "zuta"
            }
        //}
        /*if(anketa.datumRada!=null)
            return "plava"*/
        return "zelena"
    }

    fun updateAnkete(ankete: List<Anketa>){
        this.ankete = ankete
        this.ankete = this.ankete.stream().sorted { prvi, drugi -> uporediDatume(prvi,drugi)  }.collect(
            Collectors.toList())
        notifyDataSetChanged()
    }

    private fun uporediDatume(prvi: Anketa?, drugi: Anketa?) : Int {
        if (prvi!!.datumPocetak!!.before(drugi!!.datumPocetak)) return -1
        else if (prvi!!.datumPocetak!!.after(drugi!!.datumPocetak))return 1
        else return 0
    }


    override fun getItemCount(): Int = ankete.size

}