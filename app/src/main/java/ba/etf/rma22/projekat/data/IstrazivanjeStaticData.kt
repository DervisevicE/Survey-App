package ba.etf.rma22.projekat.data

import ba.etf.rma22.projekat.data.models.Istrazivanje

fun istrazivanja(): List<Istrazivanje>{
    /* val naziv: String,
    val godina: Int*/

    return listOf(
        Istrazivanje("Istrazivanje broj 1", 1),
        Istrazivanje("Istrazivanje broj 2",1),
        Istrazivanje("Istrazivanje broj 3", 2),
        Istrazivanje("Istrazivanje broj 4",3),
        Istrazivanje("Istrazivanje broj 5",2),
        Istrazivanje("Istrazivanje broj 6",2),
        Istrazivanje("Istrazivanje broj 7",1),
        Istrazivanje("Moje istrazivanje",1)
    )

}