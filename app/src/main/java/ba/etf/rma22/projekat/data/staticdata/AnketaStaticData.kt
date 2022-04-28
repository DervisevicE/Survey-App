package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Anketa
import java.util.*

fun ankete(): List<Anketa>{

    var cal: Calendar = Calendar.getInstance()
    cal.set(2022,3,5)
    var datum1: Date = cal.time

    cal.set(2022,4,4)
    var datum2: Date = cal.time

    cal.set(2022,5,5)
    var datum3: Date = cal.time

    cal.set(2022,6,6)
    var datum4: Date = cal.time

    cal.set(2022,1,1)
    var datum5: Date = cal.time

    cal.set(2022,3,3)
    var datum6: Date = cal.time

    cal.set(2022,2,2)
    var datum7: Date = cal.time

    cal.set(2022,10,15)
    var datum8: Date = cal.time

    cal.set(2023,4,4)
    var datum9: Date = cal.time

    cal.set(2023,8,17)
    var datum10: Date = cal.time

    cal.set(2023,11,13)
    var datum11: Date = cal.time

    cal.set(2023,7,15)
    var datum12: Date = cal.time

    cal.set(2021,10,11)
    var datum13: Date = cal.time

    cal.set(2021,8,5)
    var datum14: Date = cal.time

    cal.set(2021,11,17)
    var datum15: Date = cal.time

    cal.set(2021,11,8)
    var datum16: Date = cal.time



    return listOf(
        Anketa("Anketa 3", "Istrazivanje broj 6", datum5, datum4, datum6,10, "G1", 0.6F), //moje

        Anketa("Anketa 4", "Istrazivanje broj 6", datum5, datum7,null,10, "G4", 0.5F), //moje

        Anketa("Anketa 5", "Istrazivanje broj 3", datum12, datum10,null,10, "G3", 0F), //moje

        Anketa("Anketa 1", "Istrazivanje broj 3", datum1, datum8,null,10, "G1", 0.0F), //moje

        Anketa("Anketa 2", "Istrazivanje broj 5", datum4, datum8,null,10, "G5", 0F),

        Anketa("Anketa 3", "Istrazivanje broj 6", datum5, datum4, datum6,10, "G1", 0F),

        Anketa("Anketa 4", "Istrazivanje broj 6", datum5, datum7,null,10, "G4", 0F),

        Anketa("Anketa 5", "Istrazivanje broj 3", datum12, datum10,null,10, "G3", 0F),

        Anketa("Anketa 6", "Istrazivanje broj 1", datum13, datum11,null,10, "G3", 0F),

        Anketa("Anketa 7", "Istrazivanje broj 5", datum2, datum10,null,10, "G2", 0F),

        Anketa("Anketa 8", "Istrazivanje broj 4", datum12, datum11, null,10, "G2", 0F),

        Anketa("Anketa 9", "Istrazivanje broj 7", datum14, datum1,datum5,10, "G3", 0F),

        Anketa("Anketa 9", "Istrazivanje broj 7", datum14, datum1,datum5,10, "G2", 0F),

        Anketa("Anketa 10", "Istrazivanje broj 2", datum16, datum15,null,10, "G4", 0F),

        Anketa("Anketa 10", "Istrazivanje broj 2", datum16, datum15,null,10, "G5", 0F),

        Anketa("Anketa 10", "Istrazivanje broj 2", datum16, datum15,null,10, "G6", 0F),

        Anketa("Anketa 1", "Istrazivanje broj 5", datum1, datum2,null,10, "G5", 0F),

        Anketa("Anketa 3", "Istrazivanje broj 6", datum14, datum4,null,10, "G5", 0F),

        Anketa("Anketa 5", "Istrazivanje broj 3", datum5, datum8, datum7,10, "G1", 0F),

        Anketa("Anketa 7", "Istrazivanje broj 5", datum5, datum6,null,10, "G2", 0F),

        Anketa("Anketa 9", "Istrazivanje broj 3", datum10, datum11,null,10, "G5", 0F),

        Anketa("Anketa 9", "Istrazivanje broj 3", datum10, datum11,null,10, "G3", 0F),


        Anketa("Anketa 2", "Istrazivanje broj 4", datum10, datum11,null,10, "G4", 0F),

        Anketa("Anketa 1", "Istrazivanje broj 4", datum10, datum11,null,10, "G6", 0F),

        Anketa("Anketa 4", "Istrazivanje broj 7", datum13, datum5,null,10, "G5", 0F),

        Anketa("Anketa 6", "Istrazivanje broj 5", datum12, datum11, null,10, "G6", 0F),

        Anketa("Anketa 8", "Istrazivanje broj 1", datum1, datum9,null,10, "G2", 0F),

        Anketa("Anketa 10", "Istrazivanje broj 1", datum1, datum11,null,10, "G1", 0F),

        Anketa("Moja anketa", "Moje istrazivanje", datum5, datum4, datum6,35, "G1",1F), //moja

        Anketa("Anketa 8", "Istrazivanje broj 6", datum5, datum4, datum6,10, "G1", 0F),

        Anketa("Anketa 8", "Istrazivanje broj 6", datum5, datum7,null,10, "G2", 0F),

        Anketa("Anketa 8", "Istrazivanje broj 3", datum12, datum10,null,10, "G3", 0F),

        Anketa("Anketa 9", "Istrazivanje broj 3", datum1, datum8,null,10, "G4", 0F),

        Anketa("Anketa 9", "Istrazivanje broj 6", datum5, datum4, datum6,10, "G5", 0F),

        Anketa("Anketa 9", "Istrazivanje broj 6", datum5, datum7,null,10, "G6", 0F),

        Anketa("Anketa 10", "Istrazivanje broj 3", datum12, datum10,null,10, "G6", 0F),

        Anketa("Anketa 10", "Istrazivanje broj 3", datum1, datum8,null,10, "G5", 0F),

        Anketa("Anketa 10", "Istrazivanje broj 6", datum5, datum4, datum6,10, "G4", 0F),

        Anketa("Anketa 11", "Istrazivanje broj 6", datum5, datum7,null,10, "G3", 0F),

        Anketa("Anketa 11", "Istrazivanje broj 3", datum12, datum10,null,10, "G2", 0F),

        Anketa("Anketa 11", "Istrazivanje broj 3", datum1, datum8,null,10, "G6", 0F),

        Anketa("Anketa 12", "Istrazivanje broj 6", datum5, datum4, datum6,10, "G1", 0F),

        Anketa("Anketa 12", "Istrazivanje broj 6", datum5, datum7,null,10, "G2", 0F),

        Anketa("Anketa 12", "Istrazivanje broj 3", datum12, datum10,null,10, "G5", 0F),

        Anketa("Anketa 13", "Istrazivanje broj 3", datum1, datum8,null,10, "G1", 0F),

        Anketa("Anketa 13", "Istrazivanje broj 6", datum5, datum4, datum6,10, "G3", 0F),

        Anketa("Anketa 13", "Istrazivanje broj 6", datum5, datum7,null,10, "G6", 0F),

        Anketa("Anketa 14", "Istrazivanje broj 3", datum12, datum10,null,10, "G2", 0F),

        Anketa("Anketa 14", "Istrazivanje broj 3", datum1, datum8,null,10, "G3", 0F),

        Anketa("Anketa 14", "Istrazivanje broj 3", datum1, datum8,null,10, "G6", 0F),

        )
}