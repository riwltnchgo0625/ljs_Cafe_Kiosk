package com.ljs.ljs_cafe_kiosk

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.PopupWindow
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SelectPayMethodActivity : AppCompatActivity() {

    private var ljs_popupWindow: PopupWindow? = null

    @SuppressLint("MissingInflatedId", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectpaymethod)

        val ljs_card_pay_btn = findViewById<Button>(R.id.card_payment_btn)
        ljs_card_pay_btn.setOnClickListener {
            val ljs_inflater = LayoutInflater.from(this@SelectPayMethodActivity)
            val ljs_popupView = ljs_inflater.inflate(R.layout.popup_card_payment, null)

            val widthSize = 900
            val heightSize = 1200
            ljs_popupWindow = PopupWindow(ljs_popupView, widthSize, heightSize, true)
            ljs_popupWindow!!.elevation = 10f
            ljs_popupWindow!!.setBackgroundDrawable(ContextCompat.getDrawable(this@SelectPayMethodActivity,R.drawable.popup_round_background))
            ljs_popupWindow!!.showAtLocation(ljs_popupView, Gravity.CENTER, 0, 0)

            val ljs_cancel_btn = ljs_popupView.findViewById<Button>(R.id.card_cancel_btn)
            val ljs_ok_btn = ljs_popupView.findViewById<Button>(R.id.card_ok_btn)

            ljs_cancel_btn.setOnClickListener {
                ljs_popupWindow?.dismiss()
            }

            ljs_ok_btn.setOnClickListener {
                ljs_popupWindow?.dismiss()

                // 결제대기창 팝업
                val waitingPopupView = ljs_inflater.inflate(R.layout.popup_waiting_pay, null)
                val waitingPopupWindow = PopupWindow(waitingPopupView, widthSize, heightSize, true)
                waitingPopupWindow.elevation = 10f
                waitingPopupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
                waitingPopupWindow.showAtLocation(waitingPopupView, Gravity.CENTER, 0, 0)

                val waitingProgressBar =
                    waitingPopupView.findViewById<ProgressBar>(R.id.progressBar)

                Handler().postDelayed({
                    waitingPopupWindow.dismiss()

                    val completePopupView = ljs_inflater.inflate(R.layout.popup_complete, null)
                    val completePopupWindow =
                        PopupWindow(completePopupView, widthSize, heightSize, true)
                    completePopupWindow.elevation = 10f
                    ljs_popupWindow!!.setBackgroundDrawable(ContextCompat.getDrawable(this@SelectPayMethodActivity,R.drawable.popup_round_background))
                    completePopupWindow.showAtLocation(completePopupView, Gravity.CENTER, 0, 0)

                    val ljs_goMain_btn = completePopupView.findViewById<Button>(R.id.go_main)
                    ljs_goMain_btn.setOnClickListener {
                        val intent = Intent(this@SelectPayMethodActivity, MainActivity::class.java)
                        startActivity(intent)

                    }
                }, 3000)
            }

        }


        val ljs_barcode_pay_btn = findViewById<Button>(R.id.barcode_payment_btn)
        ljs_barcode_pay_btn.setOnClickListener {
            val inflater = LayoutInflater.from(this@SelectPayMethodActivity)
            val popupView = inflater.inflate(R.layout.popup_barcode_payment, null)

            val widthSize = 900
            val heightSize = 1200

            ljs_popupWindow = PopupWindow(popupView, widthSize, heightSize, true)
            ljs_popupWindow!!.elevation = 10f
            ljs_popupWindow!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
            ljs_popupWindow!!.showAtLocation(popupView, Gravity.CENTER, 0, 0)

            val ljs_cancel_btn = popupView.findViewById<Button>(R.id.barcode_cancel_btn)
            val ljs_ok_btn = popupView.findViewById<Button>(R.id.barcode_ok_btn)

            ljs_cancel_btn.setOnClickListener {
                ljs_popupWindow?.dismiss()
            }

            ljs_ok_btn.setOnClickListener {
                ljs_popupWindow?.dismiss()

                // waitingpopup
                val waitingPopupView = inflater.inflate(R.layout.popup_waiting_pay, null)
                val waitingPopupWindow = PopupWindow(waitingPopupView, widthSize, heightSize, true)
                waitingPopupWindow.elevation = 10f
                waitingPopupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
                waitingPopupWindow.showAtLocation(waitingPopupView, Gravity.CENTER, 0, 0)

                val waitingProgressBar =
                    waitingPopupView.findViewById<ProgressBar>(R.id.progressBar)

                Handler().postDelayed({
                    waitingPopupWindow.dismiss()

                    // Show the popup_complete.xml popup window
                    val completePopupView = inflater.inflate(R.layout.popup_complete, null)
                    val completePopupWindow =
                        PopupWindow(completePopupView, widthSize, heightSize, true)
                    completePopupWindow.elevation = 10f
                    completePopupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
                    completePopupWindow.showAtLocation(completePopupView, Gravity.CENTER, 0, 0)

                    val ljs_goMain_btn = completePopupView.findViewById<Button>(R.id.go_main)
                    ljs_goMain_btn.setOnClickListener {
                        // Navigate to MainActivity
                        val intent = Intent(this@SelectPayMethodActivity, MainActivity::class.java)
                        startActivity(intent)

                    }
                }, 3000)
            }
        }


    }


}
