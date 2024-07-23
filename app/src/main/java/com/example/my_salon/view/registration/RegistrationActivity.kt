package com.example.my_salon.view.registration

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.my_salon.MainActivity
import com.example.my_salon.R
import com.example.my_salon.databinding.ActivityRegistrationBinding
import com.example.my_salon.db.entity.UserData
import com.example.my_salon.view.login.LoginFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.register)

        setSupportActionBar(binding.regToolbar)
        binding.regToolbar.setNavigationIcon(R.drawable.ic_backspace)
        binding.regToolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        binding.regButton.setOnClickListener {
            val signupUsername = binding.editName.text.toString()
            val signupEmail = binding.editEmail.text.toString()
            val signupPhone = binding.editPhone.text.toString()

            if (signupUsername.isNotEmpty() && signupEmail.isNotEmpty() && signupPhone.isNotEmpty()) {
                signUpUser(signupUsername, signupEmail, signupPhone)
            } else {
                Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_SHORT).show()
            }
        }

        binding.toLogin.setOnClickListener {
            viewFragment(LoginFragment())
        }
    }

    private fun signUpUser(name: String, email: String, phone: String) {
        databaseReference.orderByChild("name").equalTo(name)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (!dataSnapshot.exists()) {
                        val id = databaseReference.push().key
                        val userData = UserData(name, email, phone)
                        databaseReference.child(id!!).setValue(userData)
                        Toast.makeText(this@RegistrationActivity, "Signup Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@RegistrationActivity, MainActivity::class.java))
                    } else {
                        Toast.makeText(this@RegistrationActivity, "User already exists", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Database Error: ${databaseError.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun viewFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }
}