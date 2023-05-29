package com.ljs.ljs_cafe_kiosk

import Menu
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(
    private val ljs_context: FragmentActivity?,
    private val ljs_menuList: List<Menu>,
    private val ljs_itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        //메뉴 이미지, 이름, 가격
        val ljs_menuImage: ImageView = itemView.findViewById(R.id.menu_image)
        val ljs_menuName: TextView = itemView.findViewById(R.id.menu_name)
        val ljs_menuPrice: TextView = itemView.findViewById(R.id.menu_price)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            ljs_itemClickListener.onItemClick(adapterPosition)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val ljs_view = LayoutInflater.from(ljs_context).inflate(R.layout.item_menu, parent, false)
        return ViewHolder(ljs_view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ljs_menu = ljs_menuList[position]
        holder.ljs_menuImage.setImageResource(ljs_menu.ljs_imageResource)
        holder.ljs_menuName.text = ljs_menu.ljs_name
        holder.ljs_menuPrice.text = ljs_menu.ljs_price.toString()
    }

    override fun getItemCount(): Int {
        return ljs_menuList.size
    }
}
