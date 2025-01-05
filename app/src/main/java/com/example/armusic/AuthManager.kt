package com.example.armusic

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthManager {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    
    fun signIn(email: String, password: String, callback: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }
    
    fun createUser(email: String, password: String, callback: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    saveUserData(email)
                }
                callback(task.isSuccessful)
            }
    }
    
    private fun saveUserData(email: String) {
        val user = hashMapOf(
            "email" to email,
            "preferences" to hashMapOf<String, Any>()
        )
        db.collection("users").document(auth.currentUser?.uid ?: "")
            .set(user)
    }
}
