package com.mukundgoel.memorynotes.framework

import android.content.Context
import com.mukundgoel.core.data.Note
import com.mukundgoel.core.repository.NoteDataSource
import com.mukundgoel.memorynotes.framework.db.DatabaseService
import com.mukundgoel.memorynotes.framework.db.NoteEntity

class RoomNoteDataSource(context: Context): NoteDataSource {
    val noteDao = DatabaseService.getInstance(context).noteDao();

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id)?.toNote()

    // "it" will give us all individual entity in the List and we are mapping it to a Note
    override suspend fun getAll(): List<Note> = noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}