package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Anketa(
    /*val naziv: String,
    val nazivIstrazivanja: String,
    val datumPocetka: Date,
    val datumKraja: Date,
    var datumRada: Date?,
    val trajanje: Int,
    val nazivGrupe: String,
    var progres: Float*/
    @SerializedName("id") val id : Int,
    val nazivIstrazivanja: String,
    @SerializedName("naziv") val naziv : String,
    @SerializedName("datumPocetak") val datumPocetka : Date,
    @SerializedName("datumKraj") val datumKraja : Date,
    var datumRada: Date?,
    @SerializedName("trajanje") val trajanje : Int,
    val nazivGrupe: String,
    var progres: Float
    )