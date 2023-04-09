package com.example.miustore.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.miustore.databinding.ActivityMainBinding
import com.example.miustore.helper.Constants
import com.example.miustore.helper.User

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private var userList = ArrayList<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        generateList()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignin.setOnClickListener {
            val uName = binding.etName.text.toString()
            val password = binding.etPassword.text.toString()
            if (doesUserExists(uName, password)) {
                var intent = Intent(this, ShoppingActivity::class.java)
                intent.putExtra(Constants.INTENT_KEY, uName)
                startActivity(intent)
            } else {
                showToast("User doesn't exist")
            }
        }

        var resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                var user =  (it.data?.getSerializableExtra(Constants.INTENT_KEY)) as User
                userList.add(user)
                showToast("Account created successfully")
            } else {
                showToast("Account creation unsuccessful")
            }
        }
        binding.btnCreate.setOnClickListener {
            resultContracts.launch(Intent(this, CreateAccountActivity::class.java))
        }

        binding.tvForgot.setOnClickListener{
            sendPasswordThroughEmail(binding.etName.text.toString())
        }
    }

    private fun generateList() {
        userList.add(User("Tom", "Hanks", "tomh@abc.com", "123"))
        userList.add(User("Tom", "cruise", "tomc@abc.com", "123"))
        userList.add(User("Joe", "Biden", "joe@abc.com", "abc"))
        userList.add(User("Lionel", "Messy", "lio@abc.com", "xyz"))
        userList.add(User("Mashihur", "Rahman", "mashihur123@gmail.com", "asd"))
    }

    private fun doesUserExists(uName:String, password:String) : Boolean {
        return userList.filter { it.uName.equals(uName) && it.password.equals(password) }.size != 0
    }

    private fun sendPasswordThroughEmail(email : String) {
        var foundUser = userList.filter { it.uName.equals(email) }
        if (foundUser.size == 1) {
            var user = foundUser.get(0)
            var queryString = "mailto:${user.uName}?subject=Forgot Password&body=${user.password}"
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(
                queryString))
            startActivity(intent)
        } else {
            showToast("No user found")
        }
    }

    fun showToast(message:String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

}