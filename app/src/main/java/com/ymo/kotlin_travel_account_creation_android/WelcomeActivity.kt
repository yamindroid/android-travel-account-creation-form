package com.ymo.kotlin_travel_account_creation_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ymo.kotlin_travel_account_creation_android.databinding.ActivityAccountCreationBinding
import com.ymo.kotlin_travel_account_creation_android.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private var _binding: ActivityWelcomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUIs();
    }

    private fun setUpUIs() {
       binding.createAccountBtn.setOnClickListener {
           startActivity(Intent(this, AccountCreationActivity::class.java))
       }
    }
}