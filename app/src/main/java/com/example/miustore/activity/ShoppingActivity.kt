package com.example.miustore.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.miustore.R
import com.example.miustore.databinding.ActivityShoppingBinding
import com.example.miustore.helper.Constants

class ShoppingActivity : AppCompatActivity() {
    lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvWelcome.text = "Welcome" + " " + intent.getStringExtra(Constants.INTENT_KEY)

        binding.ivBooks.setOnClickListener { showToast(resources.getString(R.string.books)) }
        binding.ivClothing.setOnClickListener { showToast(resources.getString(R.string.clothing)) }
        binding.ivOrganic.setOnClickListener { showToast(resources.getString(R.string.organic_foods)) }
        binding.ivMedicines.setOnClickListener { showToast(resources.getString(R.string.medicines)) }
    }

    private fun showToast(message:String) {
        Toast.makeText(applicationContext, "You have chosen the $message category of shopping", Toast.LENGTH_LONG).show()
    }
}