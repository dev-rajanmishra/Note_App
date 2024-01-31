package com.rajanmishracse.noteapp


import com.rajanmishracse.noteapp.db.Dao
import com.rajanmishracse.noteapp.db.Note

class Repo(private val dao: Dao) {
    fun insert(note: Note) {
        dao.insert(note)
    }
    fun delete(note: Note) {
        dao.delete(note)
    }
    fun update(note: Note) {
        dao.update(note)
    }
     fun getAllNotes() = dao.getAllNotes()
}

