package ba.etf.rma22.projekat.data

import ba.etf.rma22.projekat.data.models.Istrazivanje

fun istrazivanja(): List<Istrazivanje>{
    /* val naziv: String,
    val godina: Int*/

    return listOf(
        Istrazivanje("Istrazivanje broj 1", 2021),
        Istrazivanje("Istrazivanje broj 2",2021),
        Istrazivanje("Istrazivanje broj 3", 2022),
        Istrazivanje("Istrazivanje broj 4",2023),
        Istrazivanje("Istrazivanje broj 5",2022),
        Istrazivanje("Istrazivanje broj 6",2022),
        Istrazivanje("Istrazivanje broj 7",2021)
    )

}