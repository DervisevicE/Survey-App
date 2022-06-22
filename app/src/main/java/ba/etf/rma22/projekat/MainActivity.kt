package ba.etf.rma22.projekat

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.repositories.*
import ba.etf.rma22.projekat.view.*
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    companion object{
        lateinit var adapterZaVP : ViewPagerAdapter
        lateinit var viewPager : ViewPager2
        lateinit var fragments : ArrayList<Fragment>
        var connection:Boolean = true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AnketaRepository.context = this
       /* AccountRepository.context = this
        PitanjeAnketaRepository.context = this
        IstrazivanjeIGrupaRepository.context = this
        TakeAnketaRepository.context = this*/

        connection = isOnline(this)


        val payload = intent.getStringExtra("payload")
        if(payload !=null) {
            val toast = Toast.makeText(this, payload, Toast.LENGTH_SHORT)
            toast.show()
        }

        viewPager = findViewById(R.id.pager)
         fragments = arrayListOf(
            FragmentAnkete(),
            FragmentIstrazivanje()
        )

        adapterZaVP = ViewPagerAdapter(fragments, this)
        viewPager.adapter = adapterZaVP

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if(viewPager.currentItem==0 && adapterZaVP.getItem(1) is FragmentPoruka)
                    adapterZaVP.refreshFragment(1, FragmentIstrazivanje())
                super.onPageSelected(position)

            }

        })

        var db = AppDatabase.getInstance(this)
        runBlocking {
            db.anketaDao().getAll()
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}