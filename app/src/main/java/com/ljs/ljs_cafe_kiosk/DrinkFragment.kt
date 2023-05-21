package com.ljs.ljs_cafe_kiosk

import Menu
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
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

    private lateinit var orderClickListener: OnOrderClickListener
    private lateinit var ljs_recyclerView: RecyclerView
    private lateinit var ljs_adapter: MenuAdapter
    private lateinit var ljs_menuList: MutableList<Menu>
    private lateinit var orderHistory: MutableList<OrderHistoryItem>


    interface OnOrderClickListener {
        fun onOrderClick(menu: Menu)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        orderClickListener = context as OnOrderClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val ljs_rootView = inflater.inflate(R.layout.fragment_drink_menu, container, false)
        ljs_recyclerView = ljs_rootView.findViewById(R.id.menu_drink_list)
        ljs_recyclerView.layoutManager = GridLayoutManager(activity, 2)

        //메뉴 리스트
        ljs_menuList = mutableListOf(
            Menu("자몽에이드", 2500, R.drawable.main_coffee_logo),
            Menu("레몬에이드", 2500, R.drawable.main_coffee_logo),
            Menu("딸기라떼", 3000, R.drawable.main_coffee_logo),
            Menu("고구마라떼", 3500, R.drawable.main_coffee_logo)
        )
        orderHistory = mutableListOf()

        ljs_adapter = MenuAdapter(activity, ljs_menuList, this)
        ljs_recyclerView.adapter = ljs_adapter
        return ljs_rootView
    }

    @SuppressLint("Range")
    override fun onItemClick(position: Int) {
        //메뉴 아이템 클릭시 이벤트
        val menu = ljs_menuList[position]
        orderClickListener.onOrderClick(menu)


    }
}