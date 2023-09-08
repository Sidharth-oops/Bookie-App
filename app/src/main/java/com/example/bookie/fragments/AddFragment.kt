package com.example.bookie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.Toolbar
import com.example.bookie.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var spinner= arrayOf("Agriculture","Architecture","Art and Photography","Drama","Engineering-Civil","Banking","Biographies","Business and Finance")
        val activity = requireActivity() as AppCompatActivity
        val view= inflater.inflate(R.layout.fragment_add, container, false)
        val customToolBar=view.findViewById<Toolbar>(R.id.custom_toolbar)
       val spinnerInterest=view.findViewById<AppCompatSpinner>(R.id.spinner_interest)
        val adapter=ArrayAdapter<String>(activity,android.R.layout.simple_spinner_item,spinner)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
         spinnerInterest.adapter=adapter

        activity.setSupportActionBar(customToolBar)

        return view
    }


}