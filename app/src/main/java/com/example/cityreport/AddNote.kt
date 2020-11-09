package com.example.cityreport

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText


class AddNote : AppCompatActivity() {

    private lateinit var editWordView: EditText
    private lateinit var editnumberView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        editWordView = findViewById(R.id.edit_word)
        editnumberView = findViewById(R.id.edit_number)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val id = editnumberView.text.toString()
                val note = editWordView.text.toString()
                replyIntent.putExtra(EXTRA1_REPLY, id)
                replyIntent.putExtra(EXTRA_REPLY, note)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
        const val EXTRA1_REPLY = "com.example.android.wordlistsql.REPLY1"
    }
}