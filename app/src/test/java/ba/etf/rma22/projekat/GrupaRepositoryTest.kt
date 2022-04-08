package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.CoreMatchers.`is` as Is
import org.junit.Test

class GrupaRepositoryTest {

    @Test
    fun dajGrupePoIstrazivanju(){
        var grupe = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje broj 1")
        assertEquals(grupe.size,3)
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G1"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G2"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G3"))))

        grupe = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje broj 2")
        assertEquals(grupe.size,3)
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G4"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G5"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G6"))))

        grupe = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje broj 3")
        assertEquals(grupe.size,3)
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G1"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G5"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G5"))))

        grupe = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje broj 4")
        assertEquals(grupe.size,3)
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G2"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G4"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G6"))))

        grupe = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje broj 5")
        assertEquals(grupe.size,3)
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G2"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G5"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G6"))))

        grupe = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje broj 6")
        assertEquals(grupe.size,3)
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G1"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G4"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G5"))))

        grupe = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje broj 7")
        assertEquals(grupe.size,3)
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G3"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G2"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G5"))))

        grupe = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje broj 8")
        assertEquals(grupe.size,3)
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G1"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G2"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G3"))))

        grupe = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje broj 9")
        assertEquals(grupe.size,3)
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G4"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G5"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G6"))))

        grupe = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje broj 10")
        assertEquals(grupe.size,2)
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G4"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G6"))))

        grupe = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje broj 11")
        assertEquals(grupe.size,3)
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G3"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G2"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G6"))))

        grupe = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje broj 12")
        assertEquals(grupe.size,3)
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G1"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G2"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G5"))))

        grupe = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje broj 13")
        assertEquals(grupe.size,3)
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G1"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G6"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G3"))))

        grupe = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje broj 14")
        assertEquals(grupe.size,3)
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G2"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G3"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("G6"))))

    }
}