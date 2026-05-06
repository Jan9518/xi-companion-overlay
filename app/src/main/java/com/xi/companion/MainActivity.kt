package com.xi.companion

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:com.xi.companion")
            )
            startActivityForResult(intent, 123)
        } else {
            startOverlay()
        }
    }

    private fun startOverlay() {
        val intent = Intent(this, XiOverlayService::class.java)
        startService(intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123 && Settings.canDrawOverlays(this)) {
            startOverlay()
        }
    }
}