package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.R

class FragmentPoruka : Fragment() {

    private lateinit var poruka : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_poruka, container, false)
        poruka = view.findViewById(R.id.tvPoruka)

        val por = this.arguments?.getString("poruka")
        poruka.text = por.toString()
        return view
    }
    companion object {
        fun newInstance(toString: String, toString1: String): FragmentPoruka = FragmentPoruka()
    }
}