package com.example.activitylifecycle

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

class MainActivity : AppCompatActivity() {
    private val lifecycleLogs = mutableListOf<String>()
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListviewConfig()
    }

    private fun setListviewConfig() {
        listView = findViewById(R.id.lifecycle_listview)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lifecycleLogs)
        listView.adapter = adapter

        findViewById<Button>(R.id.btn_next_screen).setOnClickListener {
            val intent = Intent(this, ActivityLifecycleGrid::class.java)
            intent.putStringArrayListExtra(getString(R.string.logs), ArrayList(lifecycleLogs))
            startActivity(intent)
        }

        logEvent(getString(R.string.app_created))
    }

    override fun onStart() {
        super.onStart()
        logEvent(getString(R.string.app_on_start))
    }

    override fun onResume() {
        super.onResume()
        logEvent(getString(R.string.app_on_resume))
    }

    override fun onPause() {
        super.onPause()
        logEvent(getString(R.string.app_on_pause))
    }

    override fun onStop() {
        super.onStop()
        logEvent(getString(R.string.app_on_stop))
    }

    override fun onDestroy() {
        super.onDestroy()
        logEvent(getString(R.string.app_on_destroy))
    }

    private fun logEvent(event: String) {
        val timestamp = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault()).format(Date())
        lifecycleLogs.add("$event at $timestamp")
        adapter.notifyDataSetChanged()
    }

}