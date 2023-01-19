package com.example.finalproject2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPageActivity : AppCompatActivity() {
    private lateinit var viewpager2 : ViewPager2
    private lateinit var tabLayout : TabLayout
    private lateinit var viewAdapter : ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)
        viewpager2 = findViewById(R.id.viewPager2)
        tabLayout = findViewById(R.id.tab_layot)
        viewAdapter = ViewPagerAdapter(this)
        viewpager2.adapter = viewAdapter
        TabLayoutMediator(tabLayout,viewpager2){ tab, position ->
            when(position){
                0->{
                    tab.text = "Add Photo"
                }
                1->{
                    tab.text = "Gallery"
                }
            }
        }.attach()
    }


}