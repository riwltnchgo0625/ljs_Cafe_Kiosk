package com.ljs.ljs_cafe_kiosk

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class ImageSliderAdapter(private val context: Context, private val images: List<Int>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val ljs_imageView = ImageView(context)
        ljs_imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        ljs_imageView.setImageResource(images[position % images.size]) // 이미지 인덱스를 position % 2로 설정
        container.addView(ljs_imageView)
        return ljs_imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun getCount(): Int {
        return images.size // 이미지 개수를 2로 고정
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }
}
