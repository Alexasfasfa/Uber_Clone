package com.example.uberclone

import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.uberclone.services.MyFirebaseMessagingService
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

object UserUtils {
    fun updateUser(
        view: View?,
        updateData: Map<String, Any>
    ) {
        FirebaseDatabase.getInstance()
            .getReference(Constants.DRIVER_INFO_REFERENCE)
            .child(FirebaseAuth.getInstance().currentUser?.uid!!)
            .updateChildren(updateData)
            .addOnSuccessListener {
                Snackbar.make(view!!,"Information updated successfully!",Snackbar.LENGTH_LONG).show()
            }.addOnFailureListener { e ->
                Snackbar.make(view!!,e.message!!,Snackbar.LENGTH_LONG).show()
            }
    }

    fun updateToken(context: Context, token: String) {
        val tokenModel = TokenModel()
        tokenModel.token = token

        FirebaseDatabase.getInstance()
            .getReference(Constants.TOKEN_REFERENCE)
            .child(FirebaseAuth.getInstance().currentUser?.uid!!)
            .setValue(tokenModel)
            .addOnFailureListener { e -> Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show() }
            .addOnSuccessListener {  }
    }
}