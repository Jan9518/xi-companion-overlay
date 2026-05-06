package com.xi.companion

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.TextView

class XiOverlayService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var overlayView: View

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = 100
            y = 100
        }

        overlayView = TextView(this).apply {
            text = "汐在陪着你❤️"
            setTextColor(Color.WHITE)
            setPadding(20, 10, 20, 10)
            setBackgroundColor(Color.parseColor("#80FFB6C1")) // Light pink transparent
        }

        windowManager.addView(overlayView, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        windowManager.removeView(overlayView)
    }
}