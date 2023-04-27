package com.ljs.ljs_cafe_kiosk


import Menu
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

// 디저트 메뉴 페이지

class DessertFragment : Fragment(),MenuAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MenuAdapter
    private lateinit var menuList: MutableList<Menu>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ljs_rootView = inflater.inflate(R.layout.fragment_dessert_menu, container, false)
        recyclerView = ljs_rootView.findViewById(R.id.menu_dessert_list)
        recyclerView.layoutManager = GridLayoutManager(activity,2)
        menuList = mutableListOf(
            Menu("치즈케이크", 4500,R.drawable.main_coffee_logo),
            Menu("초콜릿케이크", 5000,R.drawable.main_coffee_logo),
            Menu("브라우니", 4000,R.drawable.main_coffee_logo),
            Menu("마카롱", 3000,R.drawable.main_coffee_logo),
            Menu("와플", 3500,R.drawable.main_coffee_logo)
        )
        adapter = MenuAdapter(activity, menuList,this)
        recyclerView.adapter = adapter
        return ljs_rootView
    }

    override fun onItemClick(position : Int){

    }
}