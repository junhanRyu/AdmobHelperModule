package com.luckyhan.studio.demo.admobhelperdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.luckyhan.studio.admobhelper.BannerAdmobHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val banner = BannerAdmobHelper(
            this, findViewById(
                R.id.holder
            )
        )
        //banner.admobId = "your banner admob id"
        banner.initializeAdmob()
        banner.show()
    }
}