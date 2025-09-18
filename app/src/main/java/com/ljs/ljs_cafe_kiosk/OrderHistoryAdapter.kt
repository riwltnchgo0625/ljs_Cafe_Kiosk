package com.ljs.ljs_cafe_kiosk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class OrderHistoryAdapter(
    private val orderHistory: MutableList<OrderHistoryItem>
) : RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder>() {

    private lateinit var ljs_totalAmountTextView: TextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu_order, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = orderHistory[position]

        // 초기 렌더링(이 시점에 totalPrice가 0이라면 계산해서 보정)
        if (item.ljs_totalPrice == 0) {
            item.ljs_totalPrice = item.ljs_menuPrice * item.ljs_quantity
        }

        holder.ljs_menuNameText.text = item.ljs_menuName
        holder.ljs_menuQuantityText.text = item.ljs_quantity.toString()

        // 가격(합계) 표기: 쉼표 + "원"
        holder.ljs_menuPriceText.text = String.format("%,d", item.ljs_totalPrice)

        // 버튼 핸들러
        holder.ljs_plus_btn.setOnClickListener { increaseQuantity(position, holder) }
        holder.ljs_minus_btn.setOnClickListener { decreaseQuantity(position, holder) }
        holder.ljs_cancle_btn.setOnClickListener { removeItem(position) }
    }

    fun getOrderHistory(): List<OrderHistoryItem> = orderHistory

    override fun getItemCount(): Int = orderHistory.size

    // 수량 증가 (최대 10잔)
    private fun increaseQuantity(position: Int, holder: ViewHolder) {
        val item = orderHistory[position]
        if (item.ljs_quantity < 10) {
            item.ljs_quantity++
            item.ljs_totalPrice = item.ljs_menuPrice * item.ljs_quantity
            holder.ljs_menuQuantityText.text = item.ljs_quantity.toString()
            holder.ljs_menuPriceText.text = String.format("%,d", item.ljs_totalPrice)
            updateOrderTotal()
        } else {
            Toast.makeText(
                holder.itemView.context,
                "주문 가능한 수량은 10까지 입니다.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // 수량 감소 (최소 1잔)
    private fun decreaseQuantity(position: Int, holder: ViewHolder) {
        val item = orderHistory[position]
        if (item.ljs_quantity > 1) {
            item.ljs_quantity--
            item.ljs_totalPrice = item.ljs_menuPrice * item.ljs_quantity
            holder.ljs_menuQuantityText.text = item.ljs_quantity.toString()
            holder.ljs_menuPriceText.text = String.format("%,d", item.ljs_totalPrice)
            updateOrderTotal()
        } else {
            Toast.makeText(
                holder.itemView.context,
                "최소 주문수량은 1 이상 입니다.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // 삭제
    private fun removeItem(position: Int) {
        orderHistory.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, orderHistory.size)
        updateOrderTotal()
    }

    // 하단 합계 업데이트 (쉼표 + 원)
    private fun updateOrderTotal() {
        if (::ljs_totalAmountTextView.isInitialized) {
            val total = orderHistory.sumOf { it.ljs_totalPrice }
            ljs_totalAmountTextView.text = String.format("%,d", total)
        }
    }

    // 하단 합계 TextView 주입
    fun setTotalAmountTextView(textView: TextView) {
        ljs_totalAmountTextView = textView
        updateOrderTotal()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ljs_menuNameText: TextView = itemView.findViewById(R.id.ljs_order_list_name)
        val ljs_menuPriceText: TextView = itemView.findViewById(R.id.ljs_order_list_price) // 합계 표시용
        val ljs_plus_btn: Button = itemView.findViewById(R.id.ljs_order_list_plus)
        val ljs_minus_btn: Button = itemView.findViewById(R.id.ljs_order_list_minus)
        val ljs_cancle_btn: Button = itemView.findViewById(R.id.ljs_order_list_cancel)
        val ljs_menuQuantityText: TextView = itemView.findViewById(R.id.ljs_order_list_qua)
    }
}
