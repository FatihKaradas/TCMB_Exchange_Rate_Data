package com.example.tcmbexchangeratedata

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        val btnCreateAccount = findViewById<Button>(R.id.btnCreateAccount)
        btnCreateAccount.setOnClickListener{
            validateFields()
        }
    }
    private fun validateFields(){
        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etEmailAddress = findViewById<EditText>(R.id.etEmailAddress)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)

        //kullanıcının girdiği bilgiler alınıdı
        val fullName = etFullName.text.toString().trim()
        val email = etEmailAddress.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmpassword = etConfirmPassword.text.toString().trim()

        val fields = listOf(
            etFullName to fullName,
            etEmailAddress to email,
            etPassword to password,
            etConfirmPassword to confirmpassword
        )

        for ((editText, value) in fields) {
            if (value.isEmpty()) {
                editText.error = "${editText.hint} is required"
                return
            }
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmailAddress.error = "Invalid Email Address"
            return
        }

        // Validate password length
        if (password.length < 6) {
            etPassword.error = "Password must be at least 6 characters"
            return
        }

        if (password != confirmpassword) {
            etConfirmPassword.error = "Passwords do not match"
            return
        }



        FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val signInMethods = task.result?.signInMethods
                    if (!signInMethods.isNullOrEmpty()) {
                        // User with the provided email already exists
                        etEmailAddress.error = "Email address is already registered"
                        Toast.makeText(applicationContext, "Email address is already registered", Toast.LENGTH_SHORT).show()
                        return@addOnCompleteListener
                    }
                }
            }
        authenticateUser(email, password, fullName)
    }
    private fun authenticateUser(email: String, password: String, fullName: String) {

        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etEmailAddress = findViewById<EditText>(R.id.etEmailAddress)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val kullanıcıadı = etFullName.text.toString().trim()
        // Firebase Authentication kullanarak kullanıcıyı kaydet
        // Yeni kullanıcı kaydı ve displayName güncelleme
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Kullanıcı başarılı bir şekilde oluşturuldu
                    val user = auth.currentUser

                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(kullanıcıadı)
                        .build()

                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { updateTask ->
                            if (updateTask.isSuccessful) {
                                Log.d("Firebase", "User profile updated.")
                            }
                        }
                    startActivity(Intent(this,ExchanceRateData::class.java))
                    finish()
                } else {
                    // Kullanıcı oluşturulamadı
                    Log.w("Firebase", "createUserWithEmail:failure", task.exception)
                }
            }

    }
}