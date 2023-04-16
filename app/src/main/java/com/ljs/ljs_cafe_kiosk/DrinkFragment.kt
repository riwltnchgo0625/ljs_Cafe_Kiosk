package com.ljs.ljs_cafe_kiosk

import Menu
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//커피 외 음료 페이지

class DrinkFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MenuAdapter
    private lateinit var menuList: MutableList<Menu>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.menu_two_fragment, container, false)
        recyclerView = rootView.findViewById(R.id.menu_drink_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        menuList = mutableListOf(
            Menu("아이스티", 2000,R.drawable.main_coffee_logo),
            Menu("오렌지주스", 2500,R.drawable.main_coffee_logo),
            Menu("레몬에이드", 3000,R.drawable.main_coffee_logo),
            Menu("스무디", 3500,R.drawable.main_coffee_logo)
        )
        adapter = MenuAdapter(activity, menuList)
        recyclerView.adapter = adapter
        return rootView
    }
}
