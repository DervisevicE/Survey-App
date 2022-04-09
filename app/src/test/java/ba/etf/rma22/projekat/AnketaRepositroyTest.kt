package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.CoreMatchers.`is` as Is
import org.junit.Test

class AnketaRepositroyTest {

    @Test
    fun testSveAnkete(){
        val ankete = AnketaRepository.getAll()
        assertEquals(ankete.size,50)
        assertThat(ankete, hasItem<Anketa>(hasProperty("naziv", Is("Anketa 1"))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Istrazivanje broj 1"))))
    }

    @Test
    fun testMojeAnkete(){
        val mojeAnkete = AnketaRepository.getMyAnkete()
        assertEquals(mojeAnkete.size,5)
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("naziv", Is("Moja anketa"))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("nazivIstrazivanja",Is("Moje istrazivanje"))))
        assertThat(mojeAnkete,hasItem<Anketa>(hasProperty("trajanje",Is(35))))
        assertThat(mojeAnkete,hasItem<Anketa>(hasProperty("nazivGrupe",Is("G1"))))
        assertThat(mojeAnkete,not(hasItem<Anketa>(hasProperty("datumRada", Is(blankString())))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("progres", Is(1F))))

        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("naziv", Is("Anketa 3"))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("nazivIstrazivanja",Is("Istrazivanje broj 6"))))
        assertThat(mojeAnkete,hasItem<Anketa>(hasProperty("trajanje",Is(10))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("nazivGrupe",Is("G1"))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("progres", Is(0.6F))))

        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("naziv", Is("Anketa 4"))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("nazivIstrazivanja",Is("Istrazivanje broj 6"))))
        assertThat(mojeAnkete,hasItem<Anketa>(hasProperty("trajanje",Is(10))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("nazivGrupe",Is("G4"))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("progres", Is(0.5F))))

        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("naziv", Is("Anketa 5"))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("nazivIstrazivanja",Is("Istrazivanje broj 3"))))
        assertThat(mojeAnkete,hasItem<Anketa>(hasProperty("trajanje",Is(10))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("nazivGrupe",Is("G3"))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("progres", Is(0.3F))))

        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("naziv", Is("Anketa 5"))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("nazivIstrazivanja",Is("Istrazivanje broj 3"))))
        assertThat(mojeAnkete,hasItem<Anketa>(hasProperty("trajanje",Is(10))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("nazivGrupe",Is("G1"))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("progres", Is(0.4F))))


    }

    @Test
    fun testGetDone(){
        val uradjeneAnkete = AnketaRepository.getDone()
        assertEquals(uradjeneAnkete.size,2)
        assertThat(uradjeneAnkete, hasItem<Anketa>(hasProperty("naziv", Is("Moja anketa"))))
        assertThat(uradjeneAnkete, hasItem<Anketa>(hasProperty("nazivIstrazivanja",Is("Moje istrazivanje"))))
        assertThat(uradjeneAnkete,hasItem<Anketa>(hasProperty("trajanje",Is(35))))
        assertThat(uradjeneAnkete,hasItem<Anketa>(hasProperty("nazivGrupe",Is("G1"))))
        assertThat(uradjeneAnkete, hasItem<Anketa>(hasProperty("progres", Is(1F))))

        assertThat(uradjeneAnkete, hasItem<Anketa>(hasProperty("naziv", Is("Anketa 3"))))
        assertThat(uradjeneAnkete, hasItem<Anketa>(hasProperty("nazivIstrazivanja",Is("Istrazivanje broj 6"))))
        assertThat(uradjeneAnkete,hasItem<Anketa>(hasProperty("trajanje",Is(10))))
        assertThat(uradjeneAnkete, hasItem<Anketa>(hasProperty("nazivGrupe",Is("G1"))))
        assertThat(uradjeneAnkete, hasItem<Anketa>(hasProperty("progres", Is(0.6F))))
    }

    @Test
    fun testGetFuture(){
        val buduceAnkete = AnketaRepository.getFuture()
        assertEquals(buduceAnkete.size,1)
        assertThat(buduceAnkete, not(hasItem<Anketa>(hasProperty("naziv", Is("Moja anketa")))))
        assertThat(buduceAnkete, not(hasItem<Anketa>(hasProperty("nazivIstrazivanja",Is("Moje istrazivanje")))))
        assertThat(buduceAnkete,not(hasItem<Anketa>(hasProperty("trajanje",Is(35)))))
        assertThat(buduceAnkete,not(hasItem<Anketa>(hasProperty("nazivGrupe",Is("G1")))))
        assertThat(buduceAnkete,not(hasItem<Anketa>(hasProperty("datumRada", Is(blankString())))))
        assertThat(buduceAnkete, not(hasItem<Anketa>(hasProperty("progres", Is(1F)))))

        assertThat(buduceAnkete, hasItem<Anketa>(hasProperty("naziv", Is("Anketa 5"))))
        assertThat(buduceAnkete, hasItem<Anketa>(hasProperty("nazivIstrazivanja",Is("Istrazivanje broj 3"))))
        assertThat(buduceAnkete,hasItem<Anketa>(hasProperty("trajanje",Is(10))))
        assertThat(buduceAnkete, hasItem<Anketa>(hasProperty("nazivGrupe",Is("G3"))))
        assertThat(buduceAnkete, hasItem<Anketa>(hasProperty("progres", Is(0.3F))))
    }

    @Test
    fun testGetNotTaken(){
        val neuradjeneAnkete = AnketaRepository.getNotTaken()
        assertEquals(neuradjeneAnkete.size,1)
        assertThat(neuradjeneAnkete, not(hasItem<Anketa>(hasProperty("naziv", Is("Moja anketa")))))
        assertThat(neuradjeneAnkete, not(hasItem<Anketa>(hasProperty("nazivIstrazivanja",Is("Moje istrazivanje")))))
        assertThat(neuradjeneAnkete,not(hasItem<Anketa>(hasProperty("trajanje",Is(35)))))
        assertThat(neuradjeneAnkete,not(hasItem<Anketa>(hasProperty("naziGrupe",Is("G1")))))
        assertThat(neuradjeneAnkete,not(hasItem<Anketa>(hasProperty("datumRada", Is(blankString())))))
        assertThat(neuradjeneAnkete, not(hasItem<Anketa>(hasProperty("progres", Is(1F)))))

        assertThat(neuradjeneAnkete, hasItem<Anketa>(hasProperty("naziv", Is("Anketa 4"))))
        assertThat(neuradjeneAnkete, hasItem<Anketa>(hasProperty("nazivIstrazivanja",Is("Istrazivanje broj 6"))))
        assertThat(neuradjeneAnkete,hasItem<Anketa>(hasProperty("trajanje",Is(10))))
        assertThat(neuradjeneAnkete, hasItem<Anketa>(hasProperty("nazivGrupe",Is("G4"))))
        assertThat(neuradjeneAnkete, hasItem<Anketa>(hasProperty("progres", Is(0.5F))))
    }

}