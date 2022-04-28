package ba.etf.rma22.projekat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.view.*
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    companion object{
        lateinit var adapterZaVP : ViewPagerAdapter
        lateinit var viewPager : ViewPager2
        lateinit var fragments : ArrayList<Fragment>
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.pager)
         fragments = arrayListOf(
            FragmentAnkete(),
            FragmentIstrazivanje()
        )

        adapterZaVP = ViewPagerAdapter(fragments, this)
        viewPager.adapter = adapterZaVP

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if(viewPager.currentItem==0)
                    adapterZaVP.refreshFragment(1, FragmentIstrazivanje())
                super.onPageSelected(position)

            }

        })




    }
}