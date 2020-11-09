package com.example.cityreport.db

import androidx.lifecycle.LiveData
import com.example.cityreport.dao.NoteDao
import com.example.cityreport.entities.Note

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAlphabetizedNotes()

    fun getNotesById(): LiveData<List<Note>> {
        return noteDao.getNotesById()
    }

    suspend fun deleteAll() {
        noteDao.deleteAll()
    }

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun updateNote(id: Int, note: String) {
            noteDao.updateNote(id, note)
    }
}