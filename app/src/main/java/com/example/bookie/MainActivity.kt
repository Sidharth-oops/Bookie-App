package com.example.bookie

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bookie.databinding.ActivityMainBinding
import com.example.bookie.fragments.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
       replaceFragment(HomeFragment())
        viewBinding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->replaceFragment(HomeFragment())
                R.id.chat ->replaceFragment(ChatFragment())
                R.id.sell->replaceFragment(AddFragment())
                R.id.search->replaceFragment(SearchFragment())
                R.id.account->replaceFragment(ProfileFragment())


            }
            true
        }



    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout,fragment)
            commit()
        }

    }
}