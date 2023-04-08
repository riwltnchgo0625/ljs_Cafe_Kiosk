package com.ljs.ljs_cafe_kiosk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Set up the ViewPager and TabLayout
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = MyPagerAdapter(supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)
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

        class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getCount(): Int = 3 // number of screens to display

            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> FirstFragment()
                    1 -> SecondFragment()
                    2 -> ThirdFragment()
                    else -> throw IllegalArgumentException("Invalid position")
                }
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return when (position) {
                    0 -> "First"
                    1 -> "Second"
                    2 -> "Third"
                    else -> null
                }
            }
        }
    }
