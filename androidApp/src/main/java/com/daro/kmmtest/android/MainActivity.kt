package com.daro.kmmtest.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {
    private val breedsListViewModel: BreedsListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        lifecycleScope.launchWhenResumed {
            breedsListViewModel.breedsList.collect {
                tv.text = "there are: ${it.size} dog breeds"
            }
        }
    }
}
