package com.example.pokemonmaster.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemonmaster.R
import com.example.pokemonmaster.databinding.ActivityLoginBinding
import com.example.pokemonmaster.ui.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        initSignupButton()
    }

    private fun initSignupButton() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}