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
    @ColumnInfo(name = "NazivIstrazivanja")var nazivIstrazivanja: String?,
    @ColumnInfo(name = "NazivAnkete") @SerializedName("naziv") var naziv: String,
    @ColumnInfo(name = "DatumPocetak")@SerializedName("datumPocetak") var datumPocetak: Date?,
    @ColumnInfo(name = "DatumKraj")@SerializedName("datumKraj") var datumKraj: Date?,
    @ColumnInfo(name = "DatumRada") var datumRada: Date?,
    @ColumnInfo(name = "Trajanje") @SerializedName("trajanje") var trajanje: Int,
    @ColumnInfo(name = "NazivGrupe") var nazivGrupe: String?,
    @ColumnInfo(name = "Progres") var progres: Float?,
    @ColumnInfo(name = "Upisana") var upisana : Int = 0
    )
{
   /* constructor() : this(-1, "","", null, "", null, "",
    null, "", 0, "",0F)*/
}