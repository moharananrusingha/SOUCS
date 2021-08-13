package com.oit.soucs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.oit.soucs.adapters.OnBoardingAdapter
import com.oit.soucs.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : FragmentActivity() {
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        binding.viewPagerOnBoarding.adapter = OnBoardingAdapter(this)
        setContentView(binding.root)
    }
}