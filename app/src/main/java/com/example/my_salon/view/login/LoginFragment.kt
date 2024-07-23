package com.example.my_salon.view.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.my_salon.MainActivity
import com.example.my_salon.R
import com.example.my_salon.databinding.FragmentLoginBinding
import com.example.my_salon.db.entity.UserData
import com.example.my_salon.view.registration.RegistrationActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = getString(R.string.login)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        binding.loginBtn.setOnClickListener {
            val loginUsername = binding.loginName.text.toString()
            val loginPhone = binding.loginPhone.text.toString()

            if (loginUsername.isNotEmpty() && loginPhone.isNotEmpty()) {
                login(loginUsername, loginPhone)
            } else {
                Toast.makeText(context, "All fields are empty!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.toRegistration.setOnClickListener {
            startActivity(Intent(context, RegistrationActivity::class.java))
        }
    }

    private fun login(username: String, phone: String) {
        databaseReference.orderByChild("username").equalTo(username)
            .addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(datasnapshot: DataSnapshot) {
                    if (datasnapshot.exists()) {
                        for (userSnapshot in datasnapshot.children) {
                            val userData = userSnapshot.getValue(UserData::class.java)

                            if (userData != null && userData.phoneNumber == phone) {
                                Toast.makeText(context, "login Successful", Toast.LENGTH_SHORT)
                                    .show()
                                startActivity(Intent(context, MainActivity::class.java))
                            }
                        }
                    }
                    Toast.makeText(context, "login Failed", Toast.LENGTH_SHORT).show()
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(
                        context,
                        "Database Error: ${databaseError.message}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            })
    }
}