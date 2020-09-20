package com.luckyhan.studio.admobhelper

import android.app.Activity
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.*

class BannerAdmobHelper(private val activity: Activity, private val holder: ViewGroup) :
    AdmobHelper() {

    private var adView: AdView = AdView(activity)
    override var admobId: String = "ca-app-pub-3940256099942544/6300978111" // test-id
    var admobListener: AdListener? = null
        set(listener: AdListener?) {
            field = listener
            adView.adListener = listener
        }

    private val adSize: AdSize
        get() {
            val display: Display? =
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                    activity.display
                } else {
                    activity.windowManager.defaultDisplay
                }
            val outMetrics = DisplayMetrics()
            display?.getMetrics(outMetrics)
            val density = outMetrics.density
            var adWidthPixels = holder.width.toFloat()
            if (adWidthPixels == 0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }
            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
        }

    init {
        adView.adSize = adSize
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                if (adView.visibility == View.GONE) {
                    adView.visibility = View.VISIBLE
                }
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                if (adView.visibility == View.VISIBLE) {
                    adView.visibility = View.GONE
                }
            }
        }

    }

    fun initializeAdmob() {
        MobileAds.initialize(activity.application) {
        }
    }

    override fun show() {
        adView.adUnitId = admobId
        holder.removeAllViews()
        adView.destroy()
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        holder.addView(adView)
    }

}