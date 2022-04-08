package ba.etf.rma22.projekat.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import java.time.LocalDate
import java.util.*
import java.util.stream.Collectors
import kotlin.math.roundToInt

class AnketeListAdapater(
    private var ankete: List<Anketa>
    ): RecyclerView.Adapter<AnketeListAdapater.AnketeViewHolder>() {

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
        val statusBoja: String = dajStatus(ankete[position])
        var id: Int = context.resources.getIdentifier(statusBoja,"drawable", context.packageName)
        holder.anketaImage.setImageResource(id)

        if(statusBoja=="plava") holder.datum.text="Anketa urađena: " + formatirajDatum(ankete[position].datumRada)
        else if(statusBoja=="zelena") holder.datum.text="Vrijeme zatvaranja: " + formatirajDatum(ankete[position].datumKraja)
        else if(statusBoja=="zuta") holder.datum.text ="Vrijeme otvaranja: " + formatirajDatum(ankete[position].datumPocetka)
        else holder.datum.text="Anketa zatvorena: "+ formatirajDatum(ankete[position].datumKraja)

        var vrijednost: Int = (ankete[position].progres*10).roundToInt()
        if(vrijednost%2!=0) vrijednost+=1
        holder.progresbar.setProgress(vrijednost)

    }

    private fun formatirajDatum(datum: Date?): String {
        var dan = datum!!.date
        var mjesec = datum!!.month
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
        cal.set(LocalDate.now().year,LocalDate.now().monthValue, LocalDate.now().dayOfMonth)
        var date: Date = cal.time
        //var date: Date = Calendar.getInstance().time

        if(anketa.datumRada==null){
            if(anketa.datumPocetka.before(date) && anketa.datumKraja.after(date)) return "zelena"
            else if(anketa.datumKraja.before(date) && anketa.datumKraja.before(date)) return "crvena"
            else if (anketa.datumPocetka.after(date) && anketa.datumKraja.after(date)) return "zuta"
        }
        return "plava"
    }

    fun updateAnkete(ankete: List<Anketa>){
        this.ankete = ankete
        this.ankete = this.ankete.stream().sorted { prvi, drugi -> uporediDatume(prvi,drugi)  }.collect(
            Collectors.toList())
        notifyDataSetChanged()
    }

    private fun uporediDatume(prvi: Anketa?, drugi: Anketa?) : Int {
        if (prvi!!.datumPocetka.before(drugi!!.datumPocetka)) return -1
        else if (prvi!!.datumPocetka.after(drugi!!.datumPocetka))return 1
        else return 0
    }


    override fun getItemCount(): Int = ankete.size

}