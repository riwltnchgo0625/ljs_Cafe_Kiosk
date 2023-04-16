package com.ljs.ljs_cafe_kiosk


import Menu
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class MenuAdapter(private val context: Context?, private val menuList: List<Menu>) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuImage: ImageView = itemView.findViewById(R.id.menu_image)
        val menuName: TextView = itemView.findViewById(R.id.menu_name)
        val menuPrice: TextView = itemView.findViewById(R.id.menu_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menu = menuList[position]
        holder.menuImage.setImageResource(menu.imageResource)
        holder.menuName.text = menu.name
        holder.menuPrice.text = menu.price.toString()
    }

    override fun getItemCount(): Int {
        return menuList.size
    }
}
