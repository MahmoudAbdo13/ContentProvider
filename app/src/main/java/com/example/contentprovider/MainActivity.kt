package com.example.contentprovider

import android.content.ContentProvider
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.contentprovider.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSMS()
    }

    private fun getSMS(){
        val uri = Uri.parse("content://sms//inbox")
        val projection = arrayOf(SMS_SENDER, SMS_BODY)
        val cursor: Cursor = contentResolver.query(uri, projection, null ,null, null)!!
        while (cursor.moveToNext()){
            for (i in 0 until cursor.columnCount ){
                Log.e(LOG_TAG, "$i -> ${cursor.getColumnName(i)} - text ${cursor.getString(i)}")
                binding.text.text = "$i -> ${cursor.getColumnName(i)} - text ${cursor.getString(i)}"


            }
        }
    }

    companion object{
        private const val LOG_TAG = "MainActivity"
        private const val SMS_SENDER = "address"
        private const val SMS_BODY = "body"

    }
}