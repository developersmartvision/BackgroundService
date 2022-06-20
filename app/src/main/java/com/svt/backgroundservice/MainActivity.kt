package com.svt.backgroundservice

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager


class MainActivity : AppCompatActivity() {
    lateinit var btnClick: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = OneTimeWorkRequest.Builder(MyWork::class.java)

            .build()
        WorkManager.getInstance(this).enqueue(request)


        btnClick = findViewById(R.id.btnClick)
        btnClick.setOnClickListener {
            WorkManager.getInstance(this).enqueue(request)
        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
            .observe(this, Observer {

                val status: String = it.state.name
                Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
            })

    }
}