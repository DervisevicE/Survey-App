package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity
data class Anketa(
    @PrimaryKey @SerializedName("id") var id: Int,
    var nazivIstrazivanja: String?,
    @ColumnInfo @SerializedName("naziv") var naziv: String,
    @SerializedName("datumPocetak") var datumPocetak: Date?,
    //@ColumnInfo var datumPocetakStr: String,
    @SerializedName("datumKraj") var datumKraj: Date?,
    //@ColumnInfo var datumKrajStr: String,
    var datumRada: Date?,
    //@ColumnInfo var datumRadaStr: String,
    @ColumnInfo @SerializedName("trajanje") var trajanje: Int,
    var nazivGrupe: String?,
    var progres: Float?,
    var upisana : Int = 0
    )
{
   /* constructor() : this(-1, "","", null, "", null, "",
    null, "", 0, "",0F)*/
}