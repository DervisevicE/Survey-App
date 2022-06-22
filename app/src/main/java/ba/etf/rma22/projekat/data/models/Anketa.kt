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
    @ColumnInfo(name = "naziv") @SerializedName("naziv") var naziv: String,
    @ColumnInfo(name = "datumPocetak")@SerializedName("datumPocetak") var datumPocetak: Date?,
    @ColumnInfo(name = "datumKraj")@SerializedName("datumKraj") var datumKraj: Date?,
    @ColumnInfo(name = "DatumRada") var datumRada: Date?,
    @ColumnInfo(name = "trajanje") @SerializedName("trajanje") var trajanje: Int,
    @ColumnInfo(name = "NazivGrupe") var nazivGrupe: String?,
    @ColumnInfo(name = "Progres") var progres: Float?,
    @ColumnInfo(name = "Upisana") var upisana : Int = 0
    )
{
    override fun hashCode() : Int{
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return other is Anketa && this.naziv == other.naziv && this.nazivIstrazivanja == other.nazivIstrazivanja
    }
}