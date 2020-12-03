package com.metin.storedata

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences;
    private lateinit var textView: TextView;
    var ageFromPreferences : Int? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView);
        sharedPreferences = this.getSharedPreferences("com.metin.storedata", Context.MODE_PRIVATE);
        ageFromPreferences = sharedPreferences.getInt("age", -1);
        if (ageFromPreferences == -1) {
            textView.text = "Your age:  ";
        } else {
            textView.text = "Your age: $ageFromPreferences";
        }
    }

    fun save(view: View) {
        val myAge = findViewById<EditText>(R.id.editTextNumber).text.toString().toIntOrNull()
        if (myAge != null) {
            sharedPreferences.edit().putInt("age", myAge).apply();
            textView.text = "Your age: $myAge";
        }
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun delete(view: View) {
        val ageFromPreferences = sharedPreferences.getInt("age", -1);
        if (ageFromPreferences != null) {
            sharedPreferences.edit().remove("age").apply();
            textView.text = "Your age:  ";
        }
    }
}