package com.huni.maretnamechecksample

import android.content.pm.InstallSourceInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.huni.maretnamechecksample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvPackageName.text = checkPackageName()?: "null"
    }

    private fun checkPackageName() : String? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            var info: InstallSourceInfo? = null
            try {
                info = packageManager.getInstallSourceInfo(applicationContext.packageName)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            info?.initiatingPackageName
        } else {
            packageManager.getInstallerPackageName(applicationContext.packageName)
        }
    }
}