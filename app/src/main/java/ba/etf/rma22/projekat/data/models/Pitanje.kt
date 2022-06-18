package ba.etf.rma22.projekat.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Pitanje(
    @PrimaryKey @SerializedName("id") val id : Int,
    @SerializedName("naziv") val naziv : String,
    @SerializedName("tekstPitanja") val tekstPitanja: String,
    @SerializedName("opcije") val opcije : List<String>,
    @SerializedName("AnketumId") var anketumId : Int,
    @SerializedName("tacan") val tacan: Int
){}
