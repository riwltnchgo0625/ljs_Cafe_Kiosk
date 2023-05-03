package com.ljs.ljs_cafe_kiosk


import Menu
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//커피 메뉴 페이지
class CoffeeFragment : Fragment(), MenuAdapter.OnItemClickListener {


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MenuAdapter
    private lateinit var menuList: MutableList<Menu>



    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ljs_rootView = inflater.inflate(R.layout.fragment_coffee_menu, container, false)
        recyclerView = ljs_rootView.findViewById(R.id.menu_coffee_list)
        recyclerView.layoutManager = GridLayoutManager(activity,2)
        //메뉴 리스트
        menuList = mutableListOf(
            Menu("아메리카노(HOT)", 2500, R.drawable.main_coffee_logo),
            Menu("아메리카노(ICE)", 2500, R.drawable.main_coffee_logo),
            Menu("카페라떼(HOT)", 3000, R.drawable.main_coffee_logo),
            Menu("카페라떼(ICE)", 3000, R.drawable.main_coffee_logo),
            Menu("콜드브루", 4000, R.drawable.main_coffee_logo),
        )
        adapter = MenuAdapter(activity, menuList, this)
        recyclerView.adapter = adapter
        return ljs_rootView
    }

    override fun onItemClick(position : Int){
        //메뉴 아이템 클릭시 이벤트


    }
}



