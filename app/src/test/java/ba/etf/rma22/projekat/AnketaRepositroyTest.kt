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
        assertEquals(mojeAnkete.size,1)
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("naziv", Is("Moja anketa"))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("nazivIstrazivanja",Is("Moje istrazivanje"))))
        assertThat(mojeAnkete,hasItem<Anketa>(hasProperty("trajanje",Is(35))))
        assertThat(mojeAnkete,hasItem<Anketa>(hasProperty("nazivGrupe",Is("G1"))))
        assertThat(mojeAnkete,not(hasItem<Anketa>(hasProperty("datumRada", Is(blankString())))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("progres", Is(1F))))


    }

    @Test
    fun testGetDone(){
        val uradjeneAnkete = AnketaRepository.getDone()
        assertEquals(uradjeneAnkete.size,1)
        assertThat(uradjeneAnkete, hasItem<Anketa>(hasProperty("naziv", Is("Moja anketa"))))
        assertThat(uradjeneAnkete, hasItem<Anketa>(hasProperty("nazivIstrazivanja",Is("Moje istrazivanje"))))
        assertThat(uradjeneAnkete,hasItem<Anketa>(hasProperty("trajanje",Is(35))))
        assertThat(uradjeneAnkete,hasItem<Anketa>(hasProperty("nazivGrupe",Is("G1"))))
        assertThat(uradjeneAnkete, hasItem<Anketa>(hasProperty("progres", Is(1F))))

    }

    @Test
    fun testGetFuture(){
        val buduceAnkete = AnketaRepository.getFuture()
        assertEquals(buduceAnkete.size,0)
        assertThat(buduceAnkete, not(hasItem<Anketa>(hasProperty("naziv", Is("Moja anketa")))))
        assertThat(buduceAnkete, not(hasItem<Anketa>(hasProperty("nazivIstrazivanja",Is("Moje istrazivanje")))))
        assertThat(buduceAnkete,not(hasItem<Anketa>(hasProperty("trajanje",Is(35)))))
        assertThat(buduceAnkete,not(hasItem<Anketa>(hasProperty("nazivGrupe",Is("G1")))))
        assertThat(buduceAnkete,not(hasItem<Anketa>(hasProperty("datumRada", Is(blankString())))))
        assertThat(buduceAnkete, not(hasItem<Anketa>(hasProperty("progres", Is(1F)))))

    }

    @Test
    fun testGetNotTaken(){
        val neuradjeneAnkete = AnketaRepository.getNotTaken()
        assertEquals(neuradjeneAnkete.size,0)
        assertThat(neuradjeneAnkete, not(hasItem<Anketa>(hasProperty("naziv", Is("Moja anketa")))))
        assertThat(neuradjeneAnkete, not(hasItem<Anketa>(hasProperty("nazivIstrazivanja",Is("Moje istrazivanje")))))
        assertThat(neuradjeneAnkete,not(hasItem<Anketa>(hasProperty("trajanje",Is(35)))))
        assertThat(neuradjeneAnkete,not(hasItem<Anketa>(hasProperty("naziGrupe",Is("G1")))))
        assertThat(neuradjeneAnkete,not(hasItem<Anketa>(hasProperty("datumRada", Is(blankString())))))
        assertThat(neuradjeneAnkete, not(hasItem<Anketa>(hasProperty("progres", Is(1F)))))


    }

}