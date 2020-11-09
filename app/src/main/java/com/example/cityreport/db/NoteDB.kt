package com.example.cityreport.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cityreport.dao.NoteDao
import com.example.cityreport.entities.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Note::class], version = 1, exportSchema = false)
    public abstract class NoteDB : RoomDatabase(){
    abstract fun noteDao(): NoteDao
    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    /* var contactDao = database.contactDao()
                     contactDao.deleteAll()
                     var name = Contact(1,"Gabriel", "963852741")
                     contactDao.insert(name)
                     name= Contact(2, "Edgar", "147258369")
                     contactDao.insert(name) */
                }
            }
        }
    }
    companion object{
        @Volatile
        private var INSTANCE: NoteDB? = null
        fun getDatabase(context: Context, scope: CoroutineScope): NoteDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDB::class.java,
                    "contacts_database"
                )
                    //.fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
















