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

// 디저트 메뉴 페이지

class DessertFragment : Fragment(), MenuAdapter.OnItemClickListener {

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

        val ljs_rootView = inflater.inflate(R.layout.fragment_dessert_menu, container, false)

        ljs_recyclerView = ljs_rootView.findViewById(R.id.menu_dessert_list)
        ljs_recyclerView.layoutManager = GridLayoutManager(activity, 2)


        //메뉴 리스트
        ljs_menuList = mutableListOf(
            Menu("초코케이크", 3500, R.drawable.choco_cake),
            Menu("호두케이크", 4500, R.drawable.walnut_cake),
            Menu("샌드위치", 4000, R.drawable.sandwhich),
            Menu("머핀", 3000, R.drawable.muffin),
            Menu("크루아상", 1500, R.drawable.croissant),
            Menu("사과파이", 1200, R.drawable.applepie),
        )
        orderHistory = mutableListOf() // 주문 내역 리스트 초기화

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
