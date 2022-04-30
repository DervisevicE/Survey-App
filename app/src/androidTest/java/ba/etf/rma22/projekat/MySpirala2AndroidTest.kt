package ba.etf.rma22.projekat

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep


@RunWith(AndroidJUnit4::class)
class MySpirala2AndroidTest {
    @get:Rule
    val intentsTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun prvi(){
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToFirst())
        onView(withId(R.id.filterAnketa)).perform(click())
        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("Sve moje ankete"))).perform(click())
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToLast())
        var odaberiGodinu = 1
        onView(withId(R.id.odabirGodina)).perform(click())
        sleep(1000)
        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`(odaberiGodinu.toString()))
        ).perform(click())

        onView(withId(R.id.dodajIstrazivanjeDugme)).perform(click())
        onView(withSubstring("Uspješno ste upisani"))


    }
}