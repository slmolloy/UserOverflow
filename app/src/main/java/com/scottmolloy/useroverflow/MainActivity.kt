package com.scottmolloy.useroverflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scottmolloy.useroverflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
    }

    override fun setTitle(titleId: Int) {
        binding.actionbar.setTitle(titleId)
    }
}