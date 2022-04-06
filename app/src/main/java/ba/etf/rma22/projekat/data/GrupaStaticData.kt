package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Grupa

fun grupe(): List<Grupa>{

    /* val naziv: String,
    val nazivIstrazivanja: String*/

    return listOf(
        Grupa("G1", "Istrazivanje broj 1"),
        Grupa("G2","Istrazivanje broj 1"),
        Grupa("G3","Istrazivanje broj 1"),

        Grupa("G4", "Istrazivanje broj 2"),
        Grupa("G5","Istrazivanje broj 2"),
        Grupa("G6","Istrazivanje broj 2"),

        Grupa("G1", "Istrazivanje broj 3"),
        Grupa("G3","Istrazivanje broj 3"),
        Grupa("G5","Istrazivanje broj 3"),

        Grupa("G2", "Istrazivanje broj 4"),
        Grupa("G4","Istrazivanje broj 4"),
        Grupa("G6","Istrazivanje broj 4"),

        Grupa("G2", "Istrazivanje broj 5"),
        Grupa("G5","Istrazivanje broj 5"),
        Grupa("G6","Istrazivanje broj 5"),

        Grupa("G1", "Istrazivanje broj 6"),
        Grupa("G4","Istrazivanje broj 6"),
        Grupa("G5","Istrazivanje broj 6"),

        Grupa("G3", "Istrazivanje broj 7"),
        Grupa("G2","Istrazivanje broj 7"),
        Grupa("G5","Istrazivanje broj 7"),

        )
}