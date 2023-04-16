package com.ljs.ljs_cafe_kiosk

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

//메뉴 페이지

class MenuActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
// Set up the ViewPager2 and TabLayout
        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)
        viewPager2.adapter = MyPagerAdapter(this)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = when (position) {
                0 -> "커피"
                1 -> "음료"
                2 -> "디저트"
                else -> throw IllegalArgumentException("Invalid position")
            }
        }.attach()
    }

    class FirstFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.menu_one_fragment, container, false)
        }
    }

    class SecondFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.menu_two_fragment, container, false)
        }
    }

    class ThirdFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.menu_three_fragment, container, false)
        }
    }

    class MyPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 3 // number of screens to display

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> CoffeeFragment()
                1 -> DrinkFragment()
                2 -> BreadFragment()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }
    }
}