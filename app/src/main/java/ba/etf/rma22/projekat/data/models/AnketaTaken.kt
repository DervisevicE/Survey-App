package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class AnketaTaken(
    @PrimaryKey @SerializedName("id") val id : Int,
    @ColumnInfo(name="Student") @SerializedName("student") val student : String,
    @ColumnInfo(name="Proges") @SerializedName("progres") val progres : Float?,
    @ColumnInfo(name="DatumRada") @SerializedName("datumRada") val datumRada : Date?,
    @ColumnInfo(name="AnketumId") @SerializedName("AnketumId") val AnketumId : Int
)
