package com.ljs.ljs_cafe_kiosk

import Menu
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//커피 메뉴 페이지
class CoffeeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MenuAdapter
    private lateinit var menuList: MutableList<Menu>

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.menu_one_fragment, container, false)
        recyclerView = rootView.findViewById(R.id.menu_coffee_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        menuList = mutableListOf(
            Menu("아메리카노", 2500, R.drawable.main_coffee_logo),
            Menu("카페라떼", 3000, R.drawable.main_coffee_logo),
            Menu("카푸치노", 3000, R.drawable.main_coffee_logo)
        )
        adapter = MenuAdapter(activity, menuList)
        recyclerView.adapter = adapter
        return rootView
    }

}

