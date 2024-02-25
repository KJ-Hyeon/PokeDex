package com.example.pokemonmaster.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pokemonmaster.MainActivity
import com.example.pokemonmaster.R
import com.example.pokemonmaster.databinding.ActivityLoginBinding
import com.example.pokemonmaster.ui.signup.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val auth: FirebaseAuth by lazy { Firebase.auth }
    private val userPref by lazy { getSharedPreferences("userPref", Context.MODE_PRIVATE) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        if (userPref.getString("user", null) != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        initSignupButton()
        initLoginButton()
    }

    private fun initSignupButton() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initLoginButton() {
        with(binding) {
            btnLogin.setOnClickListener {
                val email = editId.text.toString().trim()
                val pw = editPw.text.toString().trim()
                login(email, pw)
            }
        }
    }

    private fun login(email: String, pw: String) {
        auth.signInWithEmailAndPassword(email, pw)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    userPref.edit().putString("user","${auth.currentUser?.email}").apply()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

}