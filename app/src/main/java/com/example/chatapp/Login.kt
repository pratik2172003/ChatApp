package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth

class Login: AppCompatActivity() {

    private lateinit var edtEmail:EditText
    private lateinit var edtPassword:EditText
    private lateinit var btnLogin:AppCompatButton
    private lateinit var btnSignUp: AppCompatButton

    private lateinit var mAuth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        edtEmail=findViewById(R.id.edt_email)
        edtPassword=findViewById(R.id.edt_password)
        btnLogin=findViewById(R.id.btnlogin)
        btnSignUp=findViewById(R.id.btnsignup)

        mAuth = FirebaseAuth.getInstance()


        btnSignUp.setOnClickListener{
            var intent = Intent(this , SignUp::class.java)
            startActivity(intent)
        }


        btnLogin.setOnClickListener{
            val email= edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email,password);
        }



    }

    private fun login(email:String,password :String ){
        //logic for login user

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent= Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)


                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(this@Login,"User Does not exist", Toast.LENGTH_SHORT).show()

                }
            }


    }





}