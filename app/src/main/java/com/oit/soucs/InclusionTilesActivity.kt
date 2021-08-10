package com.oit.soucs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.oit.soucs.viewmodels.TileListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InclusionTilesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inclusion_tiles)
    }
}