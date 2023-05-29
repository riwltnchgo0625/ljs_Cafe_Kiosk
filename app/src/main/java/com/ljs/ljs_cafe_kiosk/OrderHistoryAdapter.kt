package com.ljs.ljs_cafe_kiosk


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class OrderHistoryAdapter(private val orderHistory: MutableList<OrderHistoryItem>) :
    RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder>() {

    var ljs_orderTotalPrice: Int = 0 // 결제 전체 값
    private lateinit var ljs_totalAmountTextView: TextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val ljs_view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu_order, parent, false)
        return ViewHolder(ljs_view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ljs_item = orderHistory[position]
        holder.ljs_menuNameText.text = ljs_item.ljs_menuName
        holder.ljs_menuPriceText.text = ljs_item.ljs_menuPrice.toString()
        holder.ljs_priceText.text = ljs_item.ljs_menuPrice.toString()
        holder.ljs_menuQuantityText.text = ljs_item.ljs_quantity.toString()
        holder.ljs_menuPriceText.text = ljs_item.ljs_totalPrice.toString() // Update with the total price

        holder.ljs_plus_btn.setOnClickListener {
            increaseQuantity(position, holder)

        }
        holder.ljs_minus_btn.setOnClickListener {
            decreaseQuantity(position, holder)

        }
        holder.ljs_cancle_btn.setOnClickListener {
            removeItem(position)

        }

    }

    fun getOrderHistory(): List<OrderHistoryItem> {
        return orderHistory
    }

    override fun getItemCount(): Int {
        return orderHistory.size
    }

    //수량 증가 버튼
    private fun increaseQuantity(position: Int, holder: ViewHolder) {
        val ljs_item = orderHistory[position]
        ljs_item.ljs_quantity++
        ljs_item.ljs_totalPrice = ljs_item.ljs_menuPrice * ljs_item.ljs_quantity // update the total price of the item
        holder.ljs_menuQuantityText.text = ljs_item.ljs_quantity.toString()
        holder.ljs_menuPriceText.text = ljs_item.ljs_totalPrice.toString()
        updateOrderTotal()
    }

    //수량 감소버튼
    private fun decreaseQuantity(position: Int, holder: ViewHolder) {
        val ljs_item = orderHistory[position]
        if (ljs_item.ljs_quantity > 1) {
            ljs_item.ljs_quantity--
            ljs_item.ljs_totalPrice -= ljs_item.ljs_menuPrice  // Accumulate the product value
            holder.ljs_menuQuantityText.text = ljs_item.ljs_quantity.toString()
            holder.ljs_menuPriceText.text = ljs_item.ljs_totalPrice.toString()
            updateOrderTotal()
        }
    }

    //삭제버튼 눌렀을때
    private fun removeItem(position: Int) {
        ljs_orderTotalPrice -= orderHistory[position].ljs_totalPrice // subtract the price of the item being removed from the order total
        orderHistory.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, orderHistory.size)
        updateOrderTotal()
    }


    //주문 총액
    private fun updateOrderTotal() {
        if (::ljs_totalAmountTextView.isInitialized) {
            val ljs_totalAmount = orderHistory.sumBy { it.ljs_totalPrice }
            ljs_totalAmountTextView.text = ljs_totalAmount.toString()
        }
    }


    fun setTotalAmountTextView(textView: TextView) {
        ljs_totalAmountTextView = textView
        updateOrderTotal()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ljs_menuNameText: TextView = itemView.findViewById(R.id.ljs_order_list_name)
        val ljs_menuPriceText: TextView = itemView.findViewById(R.id.ljs_order_list_price)
        val ljs_plus_btn: Button = itemView.findViewById(R.id.ljs_order_list_plus)
        val ljs_minus_btn: Button = itemView.findViewById(R.id.ljs_order_list_minus)
        val ljs_cancle_btn: Button = itemView.findViewById(R.id.ljs_order_list_cancel)
        val ljs_priceText: TextView = itemView.findViewById(R.id.ljs_order_list_price)
        val ljs_menuQuantityText: TextView = itemView.findViewById(R.id.ljs_order_list_qua)
    }
}
