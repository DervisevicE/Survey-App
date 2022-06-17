package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Grupa(
    @PrimaryKey @SerializedName("id") val id : Int,
    @ColumnInfo(name = "Naziv") @SerializedName("naziv") val naziv: String,
    //val nazivIstrazivanja: String,
    @ColumnInfo(name = "IstrazivanjeId") @SerializedName("IstrazivanjeId") val istrazivanjeId : Int,
    var upisana : Int = 0
){
    override fun toString(): String {
        return "$naziv"
    }
}
