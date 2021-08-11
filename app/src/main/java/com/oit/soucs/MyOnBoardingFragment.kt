package com.oit.soucs

import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.app.OnboardingSupportFragment
class MyOnBoardingFragment: OnboardingSupportFragment() {
    private lateinit var contentView: ImageView

    override fun getPageCount(): Int {
        return 3
    }

    override fun getPageTitle(pageIndex: Int): CharSequence {
        return when(pageIndex){
            0 -> "OnBoarding 1"
            1 -> "OnBoarding 2"
            2 -> "OnBoarding 3"
            else -> "OnBoarding 4"
        }
    }

    override fun getPageDescription(pageIndex: Int): CharSequence {
        return when(pageIndex){
            0 -> "OnBoarding One"
            1 -> "OnBoarding Two"
            2 -> "OnBoarding Three"
            else -> "OnBoarding Four"
        }
    }

    override fun onCreateBackgroundView(inflater: LayoutInflater?, container: ViewGroup?): View? {
        return null
    }

    override fun onCreateContentView(inflater: LayoutInflater?, container: ViewGroup?): View {
        return ImageView(context).apply {
            //layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setImageResource(R.drawable.ic_launcher_background)
            setPadding(0, 32, 0, 32)
            contentView = this
        }
    }

    override fun onCreateForegroundView(inflater: LayoutInflater?, container: ViewGroup?): View? {
        return null
    }
    override fun onFinishFragment() {
        super.onFinishFragment()
        // User has seen OnboardingSupportFragment, so mark our SharedPreferences
        // flag as completed so that we don't show our OnboardingSupportFragment
        // the next time the user launches the app.
        PreferenceManager.getDefaultSharedPreferences(context).edit().apply {
            putBoolean(COMPLETED_ONBOARDING_PREF_NAME, true)
            apply()
        }
    }
    companion object{
        const val COMPLETED_ONBOARDING_PREF_NAME = "soucs_preference"
    }
}