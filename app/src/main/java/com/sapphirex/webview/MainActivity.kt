package com.sapphirex.webview

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Button Functions
        findViewById<Button>(R.id.button_google).setOnClickListener{ requestLink("google") }
        findViewById<Button>(R.id.button_youtube).setOnClickListener { requestLink("youtube") }

    }

    //For Link request to redirect in openwebview
    private fun requestLink(name: String) {
        db.collection("websites")
            .whereEqualTo("name", name)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}") // Logging purposes

                    openWebView(document.getString("link"))
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

    private fun openWebView(link: String?) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("link", link) // Pass the link as an extra to the WebViewActivity
        startActivity(intent)
    }
}