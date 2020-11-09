package com.example.cityreport.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cityreport.entities.Note

@Dao
interface NoteDao{
    @Query("SELECT * from note_table ORDER BY id ASC")
    fun getAlphabetizedNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note_table WHERE id LIKE '96%' ORDER BY id ASC")
    fun getNotesById(): LiveData<List<Note>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()


    @Query("UPDATE note_table SET note = :note WHERE id =:id")
    suspend fun updateNote(id: kotlin.Int, note: String)

}
