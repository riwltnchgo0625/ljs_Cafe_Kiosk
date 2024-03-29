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

import androidx.recyclerview.widget.RecyclerView

//커피 외 음료 페이지

class DrinkFragment : Fragment(), MenuAdapter.OnItemClickListener {

    private lateinit var ljs_orderClickListener: OnOrderClickListener
    private lateinit var ljs_recyclerView: RecyclerView
    private lateinit var ljs_adapter: MenuAdapter
    private lateinit var ljs_menuList: MutableList<Menu>
    private lateinit var ljs_orderHistory: MutableList<OrderHistoryItem>

    interface OnOrderClickListener {
        fun onOrderClick(menu: Menu)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ljs_orderClickListener = context as OnOrderClickListener
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
            Menu("아이스티(레몬)", 3500, R.drawable.icetea_remon),
            Menu("아이스티(복숭아)", 3500, R.drawable.icetea_peach),
            Menu("에이드(자몽)", 3500, R.drawable.grapefruit_ade),
            Menu("라떼(녹차)", 3500, R.drawable.match_latte)
        )
        ljs_orderHistory = mutableListOf()

        ljs_adapter = MenuAdapter(activity, ljs_menuList, this)
        ljs_recyclerView.adapter = ljs_adapter
        return ljs_rootView
    }

    @SuppressLint("Range")
    override fun onItemClick(position: Int) {
        //메뉴 아이템 클릭시 이벤트
        val ljs_menu = ljs_menuList[position]
        ljs_orderClickListener.onOrderClick(ljs_menu)


    }
}