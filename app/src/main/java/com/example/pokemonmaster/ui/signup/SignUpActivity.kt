package com.example.pokemonmaster.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pokemonmaster.R
import com.example.pokemonmaster.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val auth: FirebaseAuth by lazy { Firebase.auth }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        initEdits()
    }

    private fun initEdits() {
        with(binding) {
            btnSignup.setOnClickListener {
                val email = editId.text.toString().trim()
                val pw = editPw.text.toString().trim()
                signup(email, pw)
            }
        }
    }

    private fun signup(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // 회원가입 성공
                    Toast.makeText(this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    // 회원가입 실패
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}