package com.example.miustore.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.miustore.R
import com.example.miustore.databinding.ActivityCreateAccountBinding
import com.example.miustore.helper.Constants
import com.example.miustore.helper.User

class CreateAccountActivity : AppCompatActivity() {
    lateinit var binding : ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreateAcc.setOnClickListener {
            var user = User(binding.etFname.text.toString(), binding.etLname.text.toString(), binding.etEmail.text.toString(), binding.etPw.text.toString())
            setResult(RESULT_OK, Intent().putExtra(Constants.INTENT_KEY, user))
            finish()
        }
    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED)
        finish()
    }
}