package com.ljs.ljs_cafe_kiosk

import Menu
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//커피 외 음료 페이지

class DrinkFragment : Fragment(), MenuAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MenuAdapter
    private lateinit var menuList: MutableList<Menu>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ljs_rootView = inflater.inflate(R.layout.fragment_drink_menu, container, false)
        recyclerView = ljs_rootView.findViewById(R.id.menu_drink_list)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        menuList = mutableListOf(
            Menu("자몽에이드", 2500, R.drawable.main_coffee_logo),
            Menu("레몬에이드", 2500, R.drawable.main_coffee_logo),
            Menu("딸기라떼", 3000, R.drawable.main_coffee_logo),
            Menu("고구마라떼", 3500, R.drawable.main_coffee_logo)
        )
        adapter = MenuAdapter(activity, menuList, this)
        recyclerView.adapter = adapter
        return ljs_rootView
    }

    @SuppressLint("Range")
    override fun onItemClick(position: Int) {
        val inflater = LayoutInflater.from(activity)

    }
}