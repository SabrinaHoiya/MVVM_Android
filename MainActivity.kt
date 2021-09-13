package com.example.mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener {

            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if(TextUtils.isEmpty(username)){
                usernameInput.setError("Please enter username")
            } else if(TextUtils.isEmpty(password)) {
                passwordInput.setError("Please enter password")
            } else {

                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("user", username)
                intent.putExtra("password", password)

                startActivity(intent)
                finish()
            }
        }
    }
}