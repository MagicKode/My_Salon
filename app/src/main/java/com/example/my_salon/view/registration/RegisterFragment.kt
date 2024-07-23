//package com.example.my_salon.view.registration
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import com.example.my_salon.view.login.ActivityLogin
//import com.example.my_salon.MainActivity
//import com.example.my_salon.R
//import com.example.my_salon.databinding.FragmentRegistrationBinding
//import com.example.my_salon.db.entity.UserData
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
//class RegisterFragment : Fragment() {
//
//    private var _binding: FragmentRegistrationBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var firebaseDatabase: FirebaseDatabase
//    private lateinit var databaseReference: DatabaseReference
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        activity?.title = getString(R.string.register)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        firebaseDatabase = FirebaseDatabase.getInstance()
//        databaseReference = firebaseDatabase.reference.child("users")
//
//        binding.regButton.setOnClickListener {
//            val signupUsername = binding.editName.text.toString()
//            val signupEmail = binding.editEmail.text.toString()
//            val signupPhone = binding.editPhone.text.toString()
//
//            if (signupUsername.isNotEmpty() && signupEmail.isNotEmpty() && signupPhone.isNotEmpty()) {
//                signUpUser(signupUsername, signupEmail, signupPhone)
//            } else {
//                Toast.makeText(context, "All fields are mandatory", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        binding.toLoginBtn.setOnClickListener {
//            startActivity(Intent(context, ActivityLogin::class.java))
//        }
//    }
//
//    private fun signUpUser(name: String, email: String, phone: String) {
//        databaseReference.orderByChild("name").equalTo(name)
//            .addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    if (!dataSnapshot.exists()) {
//                        val id = databaseReference.push().key
//                        val userData = UserData(name, email, phone)
//                        databaseReference.child(id!!).setValue(userData)
//                        Toast.makeText(context, "Signup Successful", Toast.LENGTH_SHORT).show()
//                        startActivity(Intent(context, MainActivity::class.java))
//                    } else {
//                        Toast.makeText(context, "User already exists", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onCancelled(databaseError: DatabaseError) {
//                    Toast.makeText(
//                        context,
//                        "Database Error: ${databaseError.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            })
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//}