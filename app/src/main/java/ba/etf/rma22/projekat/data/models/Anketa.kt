package ba.etf.rma22.projekat.data.models

import java.util.*

data class Anketa(
    val naziv: String,
    val nazivIstrazivanja: String,
    val datumPocetka: Date,
    val datumKraja: Date,
    var datumRada: Date?,
    val trajanje: Int,
    val nazivGrupe: String,
    var progres: Float
)