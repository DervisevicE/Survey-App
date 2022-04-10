package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.CoreMatchers.`is` as Is
import org.junit.Test


class IstrazivanjeRepositoryTest {

    @Test
    fun upisanaIstrazivanja(){
        val istrazivanja = IstrazivanjeRepository.getUpisani()
        assertEquals(istrazivanja.size,5)
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv",Is("Moje istrazivanje"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("godina", Is(1))))

        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv",Is("Istrazivanje broj 6"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("godina", Is(2))))

        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv",Is("Istrazivanje broj 6"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("godina", Is(2))))

        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv",Is("Istrazivanje broj 3"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("godina", Is(2))))

        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv",Is("Istrazivanje broj 3"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("godina", Is(2))))

        IstrazivanjeRepository.dodajUMojaIstrazivanja(IstrazivanjeRepository.getAll().get(0).naziv, IstrazivanjeRepository.getAll().get(0).godina)
        IstrazivanjeRepository.dodajUMojaIstrazivanja("Istrazivanje broj 9",4)
        assertEquals(istrazivanja.size,7)
    }

    @Test
    fun svaIstrazivanja(){
        val istrazivanja = IstrazivanjeRepository.getAll()
        assertEquals(istrazivanja.size,14)
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv",Is("Istrazivanje broj 1"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv",Is("Istrazivanje broj 2"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv",Is("Istrazivanje broj 3"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv",Is("Istrazivanje broj 4"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv",Is("Istrazivanje broj 5"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv",Is("Istrazivanje broj 13"))))
        assertThat(istrazivanja, not(hasItem<Istrazivanje>(hasProperty("naziv",Is("Istrazivanje broj 14")))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("godina",Is(1))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("godina",Is(2))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("godina",Is(3))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("godina",Is(4))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("godina",Is(5))))
    }

    @Test
    fun izdvojiIstrazivanje(){
        val istrazivanje = IstrazivanjeRepository.izdvojiIstrazivanje("Istrazivanje broj 3", 2)
        assertThat(istrazivanje,hasProperty("godina",Is(2)))
        assertThat(istrazivanje,hasProperty("naziv",Is("Istrazivanje broj 3")))
    }


}