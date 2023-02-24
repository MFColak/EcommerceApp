package com.android.mfcolak.ecommerceapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.mfcolak.ecommerceapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}