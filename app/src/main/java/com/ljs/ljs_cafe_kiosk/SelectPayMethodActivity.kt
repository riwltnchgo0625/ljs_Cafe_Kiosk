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

            val ljs_widthSize = 900
            val ljs_heightSize = 1200
            ljs_popupWindow = PopupWindow(ljs_popupView, ljs_widthSize, ljs_heightSize, true)
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
                val ljs_waitingPopupView = ljs_inflater.inflate(R.layout.popup_waiting_pay, null)
                val waitingPopupWindow = PopupWindow(ljs_waitingPopupView, ljs_widthSize, ljs_heightSize, true)
                waitingPopupWindow.elevation = 10f
                waitingPopupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
                waitingPopupWindow.showAtLocation(ljs_waitingPopupView, Gravity.CENTER, 0, 0)

                val waitingProgressBar =
                    ljs_waitingPopupView.findViewById<ProgressBar>(R.id.progressBar)

                Handler().postDelayed({
                    waitingPopupWindow.dismiss()

                    val ljs_completePopupView = ljs_inflater.inflate(R.layout.popup_complete, null)
                    val ljs_completePopupWindow =
                        PopupWindow(ljs_completePopupView, ljs_widthSize, ljs_heightSize, true)
                    ljs_completePopupWindow.elevation = 10f
                    ljs_completePopupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
                    ljs_completePopupWindow.showAtLocation(ljs_completePopupView, Gravity.CENTER, 0, 0)

                    val ljs_goMain_btn = ljs_completePopupView.findViewById<Button>(R.id.go_main)
                    ljs_goMain_btn.setOnClickListener {
                        val ljs_intent = Intent(this@SelectPayMethodActivity, MainActivity::class.java)
                        startActivity(ljs_intent)

                    }
                }, 3000)
            }

        }


        val ljs_barcode_pay_btn = findViewById<Button>(R.id.barcode_payment_btn)
        ljs_barcode_pay_btn.setOnClickListener {
            val ljs_inflater = LayoutInflater.from(this@SelectPayMethodActivity)
            val ljs_popupView = ljs_inflater.inflate(R.layout.popup_barcode_payment, null)

            val ljs_widthSize = 900
            val ljs_heightSize = 1200

            ljs_popupWindow = PopupWindow(ljs_popupView, ljs_widthSize, ljs_heightSize, true)
            ljs_popupWindow!!.elevation = 10f
            ljs_popupWindow!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
            ljs_popupWindow!!.showAtLocation(ljs_popupView, Gravity.CENTER, 0, 0)

            val ljs_cancel_btn = ljs_popupView.findViewById<Button>(R.id.barcode_cancel_btn)
            val ljs_ok_btn = ljs_popupView.findViewById<Button>(R.id.barcode_ok_btn)

            ljs_cancel_btn.setOnClickListener {
                ljs_popupWindow?.dismiss()
            }

            ljs_ok_btn.setOnClickListener {
                ljs_popupWindow?.dismiss()

                // waitingpopup
                val ljs_waitingPopupView = ljs_inflater.inflate(R.layout.popup_waiting_pay, null)
                val ljs_waitingPopupWindow = PopupWindow(ljs_waitingPopupView, ljs_widthSize, ljs_heightSize, true)
                ljs_waitingPopupWindow.elevation = 10f
                ljs_waitingPopupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
                ljs_waitingPopupWindow.showAtLocation(ljs_waitingPopupView, Gravity.CENTER, 0, 0)

                val waitingProgressBar =
                    ljs_waitingPopupView.findViewById<ProgressBar>(R.id.progressBar)

                Handler().postDelayed({
                    ljs_waitingPopupWindow.dismiss()

                    // Show the popup_complete.xml popup window
                    val ljs_completePopupView = ljs_inflater.inflate(R.layout.popup_complete, null)
                    val ljs_completePopupWindow =
                        PopupWindow(ljs_completePopupView, ljs_widthSize, ljs_heightSize, true)
                    ljs_completePopupWindow.elevation = 10f
                    ljs_completePopupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
                    ljs_completePopupWindow.showAtLocation(ljs_completePopupView, Gravity.CENTER, 0, 0)

                    val ljs_goMain_btn = ljs_completePopupView.findViewById<Button>(R.id.go_main)
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
