package com.ljs.ljs_cafe_kiosk

import Menu
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
    private var popupWindow: PopupWindow? = null

    @SuppressLint("ClickableViewAccessibility", "MissingInflatedId")
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

        //주문 내역 리사이클
        val recyclerView = findViewById<RecyclerView>(R.id.selected_menu_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //스크롤뷰 옵션
        val scrollView = findViewById<ScrollView>(R.id.scrollview)
        scrollView.post {
            // 스크롤 아래 포커스(아니 왜 안됨 수정 필요)
            scrollView.fullScroll(View.FOCUS_DOWN)

        }

        // 주문 리스트 초기화 버튼 초기화
        val allCancelButton = findViewById<Button>(R.id.all_cancel_btn)
        allCancelButton.setOnClickListener { cancelOrder() }

        //주문 결제 버튼
        val allPay_Btn: Button = findViewById(R.id.all_pay_btn)
        allPay_Btn.setOnClickListener { showOrderDetailsPopup() }


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


    //최종 주문내역 팝업
    private fun showOrderDetailsPopup() {
        val orderHistoryItems = adapter.getOrderHistory()
        val popupView = LayoutInflater.from(this).inflate(R.layout.popup_order_details, null)
        val tableLayout = popupView.findViewById<LinearLayout>(R.id.popup_table_layout)

        tableLayout.removeAllViews()

        val cancelButton = popupView.findViewById<Button>(R.id.cancel_btn)
        val payMethodButton = popupView.findViewById<Button>(R.id.pay_method_btn)


        cancelButton.setOnClickListener {
            popupWindow?.dismiss() // Close the popup window
        }

        payMethodButton.setOnClickListener {
            var intent = Intent(this, SelectPayMethodActivity::class.java)
            startActivity(intent)
        }

        var totalAmount = 0

        // 텍스트뷰 커스텀 함수
        fun createTextView(
            text: String,
            weight: Float,
            gravity: Int,
            textColor: Int,
            textSize: Float,
            isBold: Boolean
        ): TextView {
            val textView = TextView(this)
            textView.layoutParams =
                TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, weight)
            textView.gravity = gravity
            textView.text = text
            textView.setTextColor(ContextCompat.getColor(this, textColor))
            textView.textSize = textSize
            if (isBold) {
                textView.setTypeface(null, Typeface.BOLD)
            }
            return textView
        }

        // Populate the order details in the TableLayout
        for (orderItem in orderHistoryItems) {
            val tableRow = TableRow(this)
            tableRow.layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            )

            // 각 텍스트 선언 및 커스텀
            val productNameTextView = createTextView(
                orderItem.menuName,
                2f,
                Gravity.START,
                android.R.color.black,
                15f,
                true
            )
            val quantityTextView = createTextView(
                orderItem.quantity.toString(),
                1f,
                Gravity.CENTER,
                android.R.color.black,
                15f,
                true
            )
            val totalPriceTextView = createTextView(
                orderItem.totalPrice.toString(),
                1f,
                Gravity.CENTER,
                R.color.main3,
                18f,
                true
            )

            // TableRow에 text추가
            tableRow.addView(productNameTextView)
            tableRow.addView(quantityTextView)
            tableRow.addView(totalPriceTextView)

            // 테이블에 행 추가
            tableLayout.addView(tableRow)

            totalAmount += orderItem.totalPrice
        }

        // Find the TextView for the total amount
        val totalAmountTextView = popupView.findViewById<TextView>(R.id.popup_total_amount)
        totalAmountTextView.text = totalAmount.toString()
        val widthSize = 900
        val heightSize = 1200

        // Create the PopupWindow
        popupWindow = PopupWindow(popupView, widthSize, heightSize, true)
        popupWindow!!.elevation = 10f
        popupWindow!!.setBackgroundDrawable(ColorDrawable(Color.WHITE)) // Optionally, set the background drawable

        // Show the PopupWindow
        popupWindow!!.showAtLocation(popupView, Gravity.CENTER, 0, 0)
    }

    override fun onDestroy() {
        // Dismiss the PopupWindow if it is showing
        popupWindow?.dismiss()
        super.onDestroy()
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