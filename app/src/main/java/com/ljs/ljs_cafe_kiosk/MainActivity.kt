package com.ljs.ljs_cafe_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //테이크아웃 버튼 선택시
        val ljs_go_takeout_btn = findViewById<Button>(R.id.ljs_takeout_btn)
        
        ljs_go_takeout_btn.setOnClickListener {
            var intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        //매장 버튼 선택시
        val ljs_go_store_btn = findViewById<Button>(R.id.ljs_store_btn)

        ljs_go_store_btn.setOnClickListener {
            var intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }


    }
}