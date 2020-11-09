package com.example.cityreport.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cityreport.db.NoteDB
import com.example.cityreport.db.NoteRepository
import com.example.cityreport.entities.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>
    init {
        val notesDao = NoteDB.getDatabase(application, viewModelScope).noteDao()
        repository = NoteRepository(notesDao)
        allNotes = repository.allNotes
    }

    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun deleteAll()= viewModelScope.launch(Dispatchers.IO){
        repository.deleteAll()
    }

    fun getNotesById():LiveData<List<Note>> {
        return repository.getNotesById()
    }


    fun updateNote(id: Int, note: String) = viewModelScope.launch{
        repository.updateNote(id, note)
    }
}


