package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var edtUser: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: AppCompatButton
    private lateinit var btnSignUp: AppCompatButton

    private lateinit var mAuth:FirebaseAuth
    private lateinit var mDbRef: DatabaseReference





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        edtUser=findViewById(R.id.edt_user)
        edtEmail=findViewById(R.id.edt_email)
        edtPassword=findViewById(R.id.edt_password)
        btnSignUp=findViewById(R.id.btnsignup)

        mAuth = FirebaseAuth.getInstance()



        btnSignUp.setOnClickListener{
            val name= edtUser.text.toString()
           val email=edtEmail.text.toString()
            val password = edtPassword.text.toString()

            signup(name,email,password);

        }




    }

    private fun signup(name: String,email:String,password :String ){
        //login for signup user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //jumping to home activity
                    addUserToDataBase(name, email, mAuth.currentUser?.uid!!)

                    val intent= Intent(this@SignUp, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@SignUp,"Some error Occure",Toast.LENGTH_SHORT).show()


                }
            }
    }

    private fun addUserToDataBase(name: String , email: String, uid: String) {

        mDbRef =FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(name,email,uid)).addOnSuccessListener {
            print("Successfuly data store")
        }

    }
}