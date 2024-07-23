//package com.example.my_salon.view.login
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.fragment.app.Fragment
//import com.example.my_salon.MainActivity
//import com.example.my_salon.R
//import com.example.my_salon.databinding.ActivityLoginBinding
//import com.example.my_salon.db.entity.UserData
//import com.example.my_salon.view.registration.RegisterFragment
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
//class ActivityLogin : AppCompatActivity() {
//
//    private lateinit var binding: ActivityLoginBinding
//    private lateinit var firebaseDatabase: FirebaseDatabase
//    private lateinit var databaseReference: DatabaseReference
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        title = getString(R.string.login)
//
//        setSupportActionBar(binding.loginToolbar)
//        binding.loginToolbar.setNavigationIcon(R.drawable.ic_backspace)
//        binding.loginToolbar.setNavigationOnClickListener {
//            onBackPressedDispatcher.onBackPressed()
//        }
//
//        firebaseDatabase = FirebaseDatabase.getInstance()
//        databaseReference = firebaseDatabase.reference.child("users")
//
//        binding.loginBtn.setOnClickListener {
//            val loginUsername = binding.loginName.text.toString()
//            val loginPhone = binding.loginPhone.text.toString()
//
//            if (loginUsername.isNotEmpty() && loginPhone.isNotEmpty()) {
//                login(loginUsername, loginPhone)
//            } else {
//                Toast.makeText(this, "All fields are empty!", Toast.LENGTH_SHORT).show()
//            }
//        }
//        binding.toRegistration.setOnClickListener{
//            viewFragment(RegisterFragment())
//        }
//
//    }
//
//    private fun login(username: String, phone: String) {
//        databaseReference.orderByChild("username").equalTo(username)
//            .addListenerForSingleValueEvent(object :
//                ValueEventListener {
//                override fun onDataChange(datasnapshot: DataSnapshot) {
//                    if (datasnapshot.exists()) {
//                        for (userSnapshot in datasnapshot.children) {
//                            val userData = userSnapshot.getValue(UserData::class.java)
//
//                            if (userData != null && userData.phoneNumber == phone) {
//                                Toast.makeText(this@ActivityLogin, "login Successful", Toast.LENGTH_SHORT)
//                                    .show()
//                                startActivity(Intent(this@ActivityLogin, MainActivity::class.java))
//                            }
//                        }
//                    }
//                    Toast.makeText(this@ActivityLogin, "login Failed", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onCancelled(databaseError: DatabaseError) {
//                    Toast.makeText(
//                        this@ActivityLogin,
//                        "Database Error: ${databaseError.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//
//                }
//            })
//    }
//
//    private fun viewFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
//            .commit()
//    }
//}