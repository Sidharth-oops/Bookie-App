package com.example.bookie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.bookie.R
import com.google.android.material.navigation.NavigationView


class HomeFragment : Fragment() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        drawerLayout = view.findViewById(R.id.drawer_layout)
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(view.findViewById(R.id.toolbar))
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigationView = view.findViewById(R.id.nav_view)
        toggle = ActionBarDrawerToggle(
            getActivity(),
            drawerLayout,
            view.findViewById(R.id.toolbar),
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = view.findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item clicks here
            when (menuItem.itemId) {
                R.id.nav_welcome -> {
                    // Handle home item click
                    Toast.makeText(getActivity(), "Home clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true

                    true
                }
                R.id.nav_feedback->{
                val dialogView=LayoutInflater.from(requireContext()).inflate(R.layout.feedback_custom_dialog,null)
                    val builder = AlertDialog.Builder(requireContext())
                  builder.setView(dialogView)
                    val dialog=builder.create()
                    dialog.show()
                }


                else -> false
            }
            true
        }




        return view
    }


}