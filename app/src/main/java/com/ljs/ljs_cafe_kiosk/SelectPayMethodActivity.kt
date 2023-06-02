package com.ljs.ljs_cafe_kiosk

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SelectPayMethodActivity : AppCompatActivity() {

    private var ljs_dialog: Dialog? = null
    private var ljs_waitingDialog: Dialog? = null

    @SuppressLint("MissingInflatedId", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectpaymethod)

        val ljs_card_pay_btn = findViewById<Button>(R.id.card_payment_btn)
        ljs_card_pay_btn.setOnClickListener {
            showCardPaymentDialog()
        }

        val ljs_barcode_pay_btn = findViewById<Button>(R.id.barcode_payment_btn)
        ljs_barcode_pay_btn.setOnClickListener {
            showBarcodePaymentDialog()
        }
    }

    private fun showCardPaymentDialog() {
        // Set the background of the activity to a dark color
        val ljs_layoutParams = window.attributes
        ljs_layoutParams.dimAmount = 0.8f
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        window.attributes = ljs_layoutParams

        val ljs_dialogView = LayoutInflater.from(this).inflate(R.layout.popup_card_payment, null)
        ljs_dialog = Dialog(this)
        ljs_dialog?.setContentView(ljs_dialogView)
        ljs_dialog?.setCanceledOnTouchOutside(false)

        // Set dialog size
        val ljs_window = ljs_dialog?.window
        ljs_window?.setLayout(900, 1200)

        // Set dialog background shape
        ljs_window?.setBackgroundDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.popup_round_background
            )
        )

        // Show dialog
        ljs_dialog?.show()

        // Handle button click in card payment popup
        val ljs_cancel_btn = ljs_dialogView.findViewById<Button>(R.id.card_cancel_btn)
        val ljs_ok_btn = ljs_dialogView.findViewById<Button>(R.id.card_ok_btn)

        ljs_cancel_btn.setOnClickListener {
            ljs_dialog?.dismiss()
        }

        ljs_ok_btn.setOnClickListener {
            ljs_dialog?.dismiss()
            showWaitingPopup()

            Handler().postDelayed({
                ljs_waitingDialog?.dismiss()
                showCompletePopup()
            }, 3000)
        }
    }

    private fun showBarcodePaymentDialog() {
        val ljs_layoutParams = window.attributes
        ljs_layoutParams.dimAmount = 0.8f
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        window.attributes = ljs_layoutParams

        val ljs_dialogView = LayoutInflater.from(this).inflate(R.layout.popup_barcode_payment, null)
        ljs_dialog = Dialog(this)
        ljs_dialog?.setContentView(ljs_dialogView)
        ljs_dialog?.setCanceledOnTouchOutside(false)


        // Set dialog size
        val ljs_window = ljs_dialog?.window
        ljs_window?.setLayout(900, 1200)

        ljs_dialog?.window?.setBackgroundDrawableResource(R.drawable.popup_round_background)

        // Show dialog
        ljs_dialog?.show()

        // Handle button click in barcode payment popup
        val ljs_cancel_btn = ljs_dialogView.findViewById<Button>(R.id.barcode_cancel_btn)
        val ljs_ok_btn = ljs_dialogView.findViewById<Button>(R.id.barcode_ok_btn)

        ljs_cancel_btn.setOnClickListener {
            ljs_dialog?.dismiss()
        }

        ljs_ok_btn.setOnClickListener {
            ljs_dialog?.dismiss()
            showWaitingPopup()

            Handler().postDelayed({
                ljs_waitingDialog?.dismiss()
                showCompletePopup()
            }, 3000)
        }
    }

    private fun showWaitingPopup() {

        val layoutParams = window.attributes
        layoutParams.dimAmount = 0.8f
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        window.attributes = layoutParams

        val ljs_dialogView = LayoutInflater.from(this).inflate(R.layout.popup_waiting_pay, null)
        ljs_waitingDialog = Dialog(this)
        ljs_waitingDialog?.setContentView(ljs_dialogView)
        ljs_waitingDialog?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        )


        val window = ljs_waitingDialog?.window
        window?.setLayout(900, 800)

        ljs_waitingDialog?.window?.setBackgroundDrawableResource(R.drawable.popup_round_background)

        // Show dialog
        ljs_waitingDialog?.show()
    }

    private fun showCompletePopup() {

        val layoutParams = window.attributes
        layoutParams.dimAmount = 0.8f
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        window.attributes = layoutParams

        val ljs_dialogView = LayoutInflater.from(this).inflate(R.layout.popup_complete, null)
        ljs_dialog = Dialog(this)
        ljs_dialog?.setContentView(ljs_dialogView)
        ljs_dialog?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        )


        // Set dialog size
        val window = ljs_dialog?.window
        window?.setLayout(900, 800)

        ljs_dialog?.window?.setBackgroundDrawableResource(R.drawable.popup_round_background)

        // Show dialog
        ljs_dialog?.show()

        // Handle button click in complete popup
        val ljs_goMain_btn = ljs_dialogView.findViewById<Button>(R.id.go_main)
        ljs_goMain_btn.setOnClickListener {
            val intent = Intent(this@SelectPayMethodActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ljs_dialog?.dismiss()
        ljs_waitingDialog?.dismiss()
    }
}
