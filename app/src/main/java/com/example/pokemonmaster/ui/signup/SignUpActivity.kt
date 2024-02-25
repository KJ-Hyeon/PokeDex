package com.example.pokemonmaster.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemonmaster.R
import com.example.pokemonmaster.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}