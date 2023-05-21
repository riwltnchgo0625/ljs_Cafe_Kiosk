package com.ljs.ljs_cafe_kiosk

import Menu
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

//메뉴 페이지

class MenuActivity : AppCompatActivity(), CoffeeFragment.OnOrderClickListener,
    DrinkFragment.OnOrderClickListener, DessertFragment.OnOrderClickListener {
    private lateinit var ljs_orderHistory: MutableList<OrderHistoryItem>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrderHistoryAdapter
    private lateinit var orderTotalTextView: TextView
    private lateinit var allCancelButton: Button

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        ljs_orderHistory = mutableListOf()
        recyclerView = findViewById(R.id.selected_menu_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = OrderHistoryAdapter(ljs_orderHistory)
        recyclerView.adapter = adapter

        orderTotalTextView = findViewById(R.id.order_total_price)
        adapter.setTotalAmountTextView(orderTotalTextView)

        val recyclerView = findViewById<RecyclerView>(R.id.selected_menu_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 주문 리스트 초기화 버튼 초기화
        allCancelButton = findViewById(R.id.all_cancel_btn)
        allCancelButton.setOnClickListener { cancelOrder() }

// 뷰페이저, 탭 레이아웃
        val ljs_viewPager2 = findViewById<ViewPager2>(R.id.ljs_viewPager2)
        ljs_viewPager2.adapter = MyPagerAdapter(this)

        val ljs_tabLayout = findViewById<TabLayout>(R.id.ljs_tabLayout)
        TabLayoutMediator(ljs_tabLayout, ljs_viewPager2) { tab, position ->
            tab.text = when (position) {
                0 -> "커피"
                1 -> "음료"
                2 -> "디저트"
                else -> throw IllegalArgumentException("Invalid position")
            }
        }.attach()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun cancelOrder() {
        // 주문 리스트 초기화
        ljs_orderHistory.clear()

        // 어댑터에 변경 내용을 알리고 새로고침
        adapter.notifyDataSetChanged()
        updateOrderTotal()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onOrderClick(menu: Menu) {
        val existingItem = ljs_orderHistory.find { it.menuName == menu.name }

        if (existingItem != null) {
            existingItem.quantity++
            existingItem.totalPrice = existingItem.menuPrice * existingItem.quantity
        } else {
            val orderItem = OrderHistoryItem(menu.name, menu.price, 1)
            ljs_orderHistory.add(orderItem)
        }

        adapter.notifyDataSetChanged()
        updateOrderTotal()
    }

    private fun updateOrderTotal() {
        val orderTotal = ljs_orderHistory.sumOf { it.totalPrice }
        orderTotalTextView.text = orderTotal.toString()
    }

    class FirstFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_coffee_menu, container, false)
        }
    }

    class SecondFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_drink_menu, container, false)
        }
    }

    class ThirdFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_dessert_menu, container, false)
        }
    }

    class MyPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 3 // number of screens to display

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> CoffeeFragment()
                1 -> DrinkFragment()
                2 -> DessertFragment()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }
    }


}