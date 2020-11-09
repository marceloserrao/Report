package com.example.cityreport

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cityreport.adapter.NoteAdapter
import com.example.cityreport.entities.Note
import com.example.cityreport.viewModel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

private lateinit var noteViewModel: NoteViewModel

class MainActivity : AppCompatActivity() {
    private val AddNoteRequestCode = 1
    private val UpdateActivityRequestCode = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NoteAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.allNotes.observe(this, { notes ->
            notes?.let{adapter.setNotes(it)}
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNote::class.java)
            startActivityForResult(intent, AddNoteRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AddNoteRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(AddNote.EXTRA_REPLY)?.let {
                val note = it
                data?.getStringExtra(AddNote.EXTRA1_REPLY)?.let {
                    val note = Note(note = (note))

                    noteViewModel.insert(note)
                }
            }
            }
        else if(requestCode == AddNoteRequestCode) {
            Toast.makeText(
                    applicationContext,
                    "Campo nao inserido",
                    Toast.LENGTH_LONG).show()
        }
        if (requestCode == UpdateActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(Update.EXTRA_REPLY)?.let {
                val note = it
                data?.getStringExtra(AddNote.EXTRA1_REPLY)?.let {

                    noteViewModel.updateNote(note)
                }
            }
        }

        else if(requestCode == UpdateActivityRequestCode) {
            Toast.makeText(
                applicationContext,
                "Campo nao editado",
                Toast.LENGTH_LONG).show()
        }
        }

    




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater=menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.all -> {
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
                val adapter = NoteAdapter(this)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this)

                noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
                noteViewModel.allNotes.observe(this, { notes ->
                    notes?.let { adapter.setNotes(it) }
                })
                true
            }
            R.id.s96 ->{

                val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
                val adapter = NoteAdapter(this)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this)
                noteViewModel= ViewModelProvider(this).get(NoteViewModel::class.java)
                noteViewModel.getNotesById().observe(this,{ notes ->
                    notes?.let {adapter.setNotes(it) }
                })
                true
            }
            R.id.updatecon ->
                {
                    val intent = Intent(this@MainActivity, Update::class.java)
                    startActivityForResult(intent, UpdateActivityRequestCode)
                    true
                }

            R.id.deleteall -> {
                noteViewModel.deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    }