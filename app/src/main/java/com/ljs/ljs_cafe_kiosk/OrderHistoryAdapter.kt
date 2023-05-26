package com.ljs.ljs_cafe_kiosk


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class OrderHistoryAdapter(private val orderHistory: MutableList<OrderHistoryItem>) :
    RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder>() {

    var orderTotalPrice: Int = 0 // variable to keep track of the total price of the entire order
    private lateinit var totalAmountTextView: TextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = orderHistory[position]
        holder.menuNameTextView.text = item.menuName
        holder.menuPriceTextView.text = item.menuPrice.toString()
        holder.priceTextView.text = item.menuPrice.toString()
        holder.menuQuantityTextView.text = item.quantity.toString()
        holder.menuPriceTextView.text = item.totalPrice.toString() // Update with the total price

        holder.plusButton.setOnClickListener {
            increaseQuantity(position, holder)

        }
        holder.minusButton.setOnClickListener {
            decreaseQuantity(position, holder)

        }
        holder.cancelButton.setOnClickListener {
            removeItem(position)

        }

    }

    fun getOrderHistory(): List<OrderHistoryItem> {
        return orderHistory
    }

    override fun getItemCount(): Int {
        return orderHistory.size
    }

    private fun increaseQuantity(position: Int, holder: ViewHolder) {
        val item = orderHistory[position]
        item.quantity++
        item.totalPrice = item.menuPrice * item.quantity // update the total price of the item
        holder.menuQuantityTextView.text = item.quantity.toString()
        holder.menuPriceTextView.text = item.totalPrice.toString()
        updateOrderTotal()
    }

    private fun decreaseQuantity(position: Int, holder: ViewHolder) {
        val item = orderHistory[position]
        if (item.quantity > 1) {
            item.quantity--
            item.totalPrice -= item.menuPrice  // Accumulate the product value
            holder.menuQuantityTextView.text = item.quantity.toString()
            holder.menuPriceTextView.text = item.totalPrice.toString()
            updateOrderTotal()
        }
    }

    private fun removeItem(position: Int) {
        orderTotalPrice -= orderHistory[position].totalPrice // subtract the price of the item being removed from the order total
        orderHistory.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, orderHistory.size)
        updateOrderTotal()
    }


    private fun updateOrderTotal() {
        if (::totalAmountTextView.isInitialized) {
            val totalAmount = orderHistory.sumBy { it.totalPrice }
            totalAmountTextView.text = totalAmount.toString()
        }
    }


    fun setTotalAmountTextView(textView: TextView) {
        totalAmountTextView = textView
        updateOrderTotal()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuNameTextView: TextView = itemView.findViewById(R.id.ljs_order_list_name)
        val menuPriceTextView: TextView = itemView.findViewById(R.id.ljs_order_list_price)
        val plusButton: Button = itemView.findViewById(R.id.ljs_order_list_plus)
        val minusButton: Button = itemView.findViewById(R.id.ljs_order_list_minus)
        val cancelButton: Button = itemView.findViewById(R.id.ljs_order_list_cancel)
        val priceTextView: TextView = itemView.findViewById(R.id.ljs_order_list_price)
        val menuQuantityTextView: TextView = itemView.findViewById(R.id.ljs_order_list_qua)
    }
}
