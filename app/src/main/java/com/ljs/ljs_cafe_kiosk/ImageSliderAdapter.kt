package com.ljs.ljs_cafe_kiosk

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class ImageSliderAdapter(private val context: Context, private val images: List<Int>) : PagerAdapter() {

    //아이템 생성, 반환
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val ljs_imageView = ImageView(context)
        //이미지 센터로 크롭
        ljs_imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        ljs_imageView.setImageResource(images[position % images.size])
        container.addView(ljs_imageView)
        return ljs_imageView
    }

    //아이템 제거
    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    //이미지 개수 반한
    override fun getCount(): Int {
        return images.size
    }

    //뷰와 객체 사이 관계 확인
    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }
}
