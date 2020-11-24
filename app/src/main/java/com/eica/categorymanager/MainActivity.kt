package com.eica.categorymanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var dataManager: DataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var addProductButton = findViewById<FloatingActionButton>(R.id.addProductButton)
        addProductButton.setOnClickListener {

        }
//        dataManager = DataManager(this)


    }
}