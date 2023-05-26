package com.ljs.ljs_cafe_kiosk


import Menu
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

//커피 메뉴 페이지
class CoffeeFragment : Fragment(), MenuAdapter.OnItemClickListener {
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

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ljs_rootView = inflater.inflate(R.layout.fragment_coffee_menu, container, false)

        ljs_recyclerView = ljs_rootView.findViewById(R.id.menu_coffee_list)
        ljs_recyclerView.layoutManager = GridLayoutManager(activity, 2)


        //메뉴 리스트
        ljs_menuList = mutableListOf(
            Menu("아메리카노(HOT)", 2500, R.drawable.main_coffee_logo),
            Menu("아메리카노(ICE)", 2500, R.drawable.americano_ice),
            Menu("카페라떼(HOT)", 3000, R.drawable.main_coffee_logo),
            Menu("카페라떼(ICE)", 3000, R.drawable.latte_hot),

        )
        orderHistory = mutableListOf()

        ljs_adapter = MenuAdapter(activity, ljs_menuList, this)
        ljs_recyclerView.adapter = ljs_adapter
        return ljs_rootView
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onItemClick(position: Int) {
        val menu = ljs_menuList[position]
        orderClickListener.onOrderClick(menu)
    }
}



