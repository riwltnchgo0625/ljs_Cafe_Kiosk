package com.ljs.ljs_cafe_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var adapter: ImageSliderAdapter
    private val images = listOf(
        R.drawable.ad_banner,
        R.drawable.ad_banner2
    )
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setupButtons()

        // ViewPager 초기화
        viewPager = findViewById<ViewPager>(R.id.main_viewpager)
        adapter = ImageSliderAdapter(this, images)
        viewPager.adapter = adapter
        viewPager.pageMargin = resources.getDimensionPixelOffset(R.dimen.viewpager_margin)

        // 이미지 변경을 위한 핸들러와 Runnable 설정
        handler = Handler()
        runnable = Runnable { changeImage() }

        // 일정 시간마다 이미지 변경 시작
        handler.postDelayed(runnable, 4500)
    }

    // 버튼 클릭
    private fun setupButtons() {
        val ljs_go_takeout_btn = findViewById<Button>(R.id.ljs_takeout_btn)
        val ljs_go_store_btn = findViewById<Button>(R.id.ljs_store_btn)

        val intent = Intent(this, MenuActivity::class.java)

        ljs_go_takeout_btn.setOnClickListener {
            startActivity(intent)
        }

        ljs_go_store_btn.setOnClickListener {
            startActivity(intent)
        }
    }

    // 이미지 변경
    private fun changeImage() {
        val ljs_currentItem = viewPager.currentItem
        val ljs_nextItem = if (ljs_currentItem == images.size - 1) 0 else ljs_currentItem + 1

        // 마지막 이미지일 경우 첫 번째 이미지로 이동 시 애니메이션 적용
        viewPager.setCurrentItem(ljs_nextItem, ljs_currentItem == images.size - 1)

        // 일정 시간마다 이미지 변경 시작
        handler.postDelayed(runnable, 4500)
    }

    override fun onDestroy() {
        super.onDestroy()
        // 핸들러의 작업 취소
        handler.removeCallbacks(runnable)
    }
}
