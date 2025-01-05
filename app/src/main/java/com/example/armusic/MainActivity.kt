package com.example.armusic

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.sceneform.ux.ArFragment

class MainActivity : AppCompatActivity() {
    private lateinit var arFragment: ArFragment
    private lateinit var arSession: Session
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        if (!checkCameraPermission()) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        }
        
        arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment
        initializeARSession()
    }
    
    private fun initializeARSession() {
        try {
            if (ArCoreApk.getInstance().requestInstall(this, true) == ArCoreApk.InstallStatus.INSTALLED) {
                arSession = Session(this)
                val config = Config(arSession)
                config.updateMode = Config.UpdateMode.LATEST_CAMERA_IMAGE
                arSession.configure(config)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    companion object {
        private const val CAMERA_PERMISSION_CODE = 1001
    }
}
