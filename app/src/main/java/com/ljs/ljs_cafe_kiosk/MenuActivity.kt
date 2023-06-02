package com.ljs.ljs_cafe_kiosk

import Menu
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
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
    private lateinit var ljs_recyclerView: RecyclerView
    private lateinit var ljs_adapter: OrderHistoryAdapter
    private lateinit var ljs_orderTotalTextView: TextView

    private var ljs_dialog: AlertDialog? = null

    @SuppressLint("ClickableViewAccessibility", "MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        ljs_orderHistory = mutableListOf()
        ljs_recyclerView = findViewById(R.id.selected_menu_list)
        ljs_recyclerView.layoutManager = LinearLayoutManager(this)
        ljs_adapter = OrderHistoryAdapter(ljs_orderHistory)
        ljs_recyclerView.adapter = ljs_adapter

        ljs_orderTotalTextView = findViewById(R.id.order_total_price)
        ljs_adapter.setTotalAmountTextView(ljs_orderTotalTextView)

        //주문 내역 리사이클
        val ljs_recyclerView = findViewById<RecyclerView>(R.id.selected_menu_list)
        ljs_recyclerView.layoutManager = LinearLayoutManager(this)

        //스크롤뷰 옵션
        val ljs_scrollView = findViewById<ScrollView>(R.id.scrollview)
        ljs_scrollView.post {
        }

        // 주문 리스트 초기화 버튼 초기화
        val ljs_allCancel_btn = findViewById<Button>(R.id.all_cancel_btn)
        ljs_allCancel_btn.setOnClickListener {
            if (ljs_orderHistory.isEmpty()) {
                Toast.makeText(this, "삭제할 주문이 없습니다!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "전체 삭제 되었습니다!", Toast.LENGTH_SHORT).show()
                cancelOrder()
            }
        }
        //주문 결제 버튼
        val ljs_allPay_Btn: Button = findViewById(R.id.all_pay_btn)
        ljs_allPay_Btn.setOnClickListener {
            if (ljs_orderHistory.isEmpty()) {
                Toast.makeText(this, "메뉴를 선택해 주세요!", Toast.LENGTH_SHORT).show()
            } else {
                showOrderDetailsDialog()
            }
        }


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
    @SuppressLint("ClickableViewAccessibility")
    private fun showOrderDetailsDialog() {
        val ljs_orderHistoryItems = ljs_adapter.getOrderHistory()
        val ljs_dialogView = LayoutInflater.from(this).inflate(R.layout.popup_order_details, null)
        val ljs_tableLayout = ljs_dialogView.findViewById<LinearLayout>(R.id.popup_table_layout)

        ljs_tableLayout.removeAllViews()

        val ljs_cancelBtn = ljs_dialogView.findViewById<Button>(R.id.cancel_btn)
        val ljs_paymentBtn = ljs_dialogView.findViewById<Button>(R.id.pay_method_btn)

        // 취소 버튼
        ljs_cancelBtn.setOnClickListener {
            ljs_dialog?.dismiss()
        }

        // 결제 버튼
        ljs_paymentBtn.setOnClickListener {
            var intent = Intent(this, SelectPayMethodActivity::class.java)
            startActivity(intent)
            ljs_dialog?.dismiss()
        }

        var ljs_totalAmount = 0

        // 텍스트뷰 커스텀 함수
        fun createTextView(
            text: String,
            weight: Float,
            gravity: Int,
            textColor: Int,
            textSize: Float,
            isBold: Boolean
        ): TextView {
            val ljs_textView = TextView(this)
            ljs_textView.layoutParams =
                TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, weight)
            ljs_textView.gravity = gravity
            ljs_textView.text = text
            ljs_textView.setTextColor(ContextCompat.getColor(this, textColor))
            ljs_textView.textSize = textSize
            if (isBold) {
                ljs_textView.setTypeface(null, Typeface.BOLD)
            }
            return ljs_textView
        }

        // 테이블 row추가
        for (orderItem in ljs_orderHistoryItems) {
            val ljs_tableRow = TableRow(this)
            ljs_tableRow.layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            )

            // 각 텍스트 선언 및 커스텀
            val ljs_productNameText = createTextView(
                orderItem.ljs_menuName,
                2f,
                Gravity.START,
                android.R.color.black,
                15f,
                true
            )
            val ljs_quantityText = createTextView(
                orderItem.ljs_quantity.toString(),
                1f,
                Gravity.CENTER,
                android.R.color.black,
                15f,
                true
            )
            val ljs_totalPriceText = createTextView(
                orderItem.ljs_totalPrice.toString() + '원',
                1f,
                Gravity.CENTER,
                R.color.main3,
                18f,
                true
            )

            // TableRow에 text추가
            ljs_tableRow.addView(ljs_productNameText)
            ljs_tableRow.addView(ljs_quantityText)
            ljs_tableRow.addView(ljs_totalPriceText)

            // 테이블에 행 추가
            ljs_tableLayout.addView(ljs_tableRow)

            ljs_totalAmount += orderItem.ljs_totalPrice
        }

        // 결제 총액
        val ljs_totalAmountTextView = ljs_dialogView.findViewById<TextView>(R.id.popup_total_amount)
        ljs_totalAmountTextView.text = ljs_totalAmount.toString()

        val ljs_dialogBuilder = AlertDialog.Builder(this)
            .setView(ljs_dialogView)
            .setCancelable(false)


        ljs_dialog = ljs_dialogBuilder.create()
        ljs_dialog?.window?.setBackgroundDrawableResource(R.drawable.popup_round_background)

        ljs_dialog?.show()

        // Override the positive button click listener to dismiss the dialog
        ljs_dialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setOnClickListener {
            // Handle positive button click if needed
            ljs_dialog?.dismiss()
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun cancelOrder() {
        // 주문 리스트 초기화
        ljs_orderHistory.clear()

        // 어댑터에 변경 내용을 알리고 새로고침
        ljs_adapter.notifyDataSetChanged()
        updateOrderTotal()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onOrderClick(menu: Menu) {
        val ljs_existingItem = ljs_orderHistory.find { it.ljs_menuName == menu.ljs_name }

        if (ljs_existingItem != null) {
            ljs_existingItem.ljs_quantity++
            ljs_existingItem.ljs_totalPrice =
                ljs_existingItem.ljs_menuPrice * ljs_existingItem.ljs_quantity
        } else {
            val ljs_orderItem = OrderHistoryItem(menu.ljs_name, menu.ljs_price, 1)
            ljs_orderHistory.add(ljs_orderItem)
        }

        ljs_adapter.notifyDataSetChanged()
        updateOrderTotal()
    }

    private fun updateOrderTotal() {
        val ljs_orderTotal = ljs_orderHistory.sumOf { it.ljs_totalPrice }
        ljs_orderTotalTextView.text = ljs_orderTotal.toString()
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