package com.ljs.ljs_cafe_kiosk

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class BlankFragment : Fragment() {

        @SuppressLint("MissingInflatedId")
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.menu_one_fragment, container, false)
            val textView = view.findViewById<TextView>(R.id.text)
            textView.text = "11111"
            return view
        }


    }
