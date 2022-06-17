package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Account (
    @PrimaryKey @SerializedName("id") val id : Int,
    @ColumnInfo(name = "E-mail") @SerializedName("student") val email : String,
    @ColumnInfo(name = "Hash") @SerializedName("acHash") val acHash : String
)