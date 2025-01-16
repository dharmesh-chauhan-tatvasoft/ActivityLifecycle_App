package com.example.activitylifecycle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridView

class ActivityLifecycleGrid : AppCompatActivity() {
    private val lifecycleLogs = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_grid)
        setGridViewData()
    }

    private fun setGridViewData() {
        val gridView: GridView = findViewById(R.id.grid_view)
        val logs = intent.getStringArrayListExtra(Constants.LOGS) ?: ArrayList()
        lifecycleLogs.add(logs.toString())


        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, logs)
        gridView.adapter = adapter

        findViewById<Button>(R.id.shareButton).setOnClickListener {
            shareLogData()
        }
    }

    private fun shareLogData() {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = Constants.TYPE
            putExtra(Intent.EXTRA_TEXT, lifecycleLogs.joinToString("\n"))
        }
        startActivity(Intent.createChooser(shareIntent, Constants.SHARE_LOGS))
    }
}