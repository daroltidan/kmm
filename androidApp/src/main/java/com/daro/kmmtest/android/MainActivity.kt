package com.daro.kmmtest.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daro.kmmtest.Greeting
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val greeting: Greeting by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greeting.greeting()
    }
}
