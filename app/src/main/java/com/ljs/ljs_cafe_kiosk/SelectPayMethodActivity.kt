package com.ljs.ljs_cafe_kiosk

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SelectPayMethodActivity : AppCompatActivity() {

    private var popupWindow: PopupWindow? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectpaymethod)



        var card_pay_btn = findViewById<Button>(R.id.card_payment_btn)
        card_pay_btn.setOnClickListener {
            val inflater = LayoutInflater.from(this@SelectPayMethodActivity)
            val popupView = inflater.inflate(R.layout.popup_card_payment, null)

            // Create and display the popup window
            val widthSize = 900
            val heightSize = 1200
            popupWindow = PopupWindow(popupView, widthSize, heightSize, true)
            popupWindow!!.elevation = 10f
            popupWindow!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
            popupWindow!!.showAtLocation(popupView, Gravity.CENTER, 0, 0)


        }


        var barcode_pay_btn = findViewById<Button>(R.id.barcode_payment_btn)
        barcode_pay_btn.setOnClickListener {
            val inflater = LayoutInflater.from(this@SelectPayMethodActivity)
            val popupView = inflater.inflate(R.layout.popup_barcode_payment, null)


            // Create and display the popup window
            val widthSize = 900
            val heightSize = 1200
            popupWindow = PopupWindow(popupView, widthSize, heightSize, true)
            popupWindow!!.elevation = 10f
            popupWindow!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
            popupWindow!!.showAtLocation(popupView, Gravity.CENTER, 0, 0)

            val cancle_btn = findViewById<Button>(R.id.cancel_btn)
            val ok_btn = findViewById<Button>(R.id.ok_btn)


        }


    }
}