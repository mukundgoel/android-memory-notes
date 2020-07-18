package com.mukundgoel.memorynotes.framework.db

import androidx.room.*

@Dao
interface NoteDao {
    // we need suspend here since we will be calling from coroutine
    // REPLACE will allow us to update as well using this method since it will conflict and it will replace
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNoteEntity(noteEntity: NoteEntity)

    // nullable since we might not have that id in the database
    @Query("SELECT * FROM note WHERE ID = :id")
    suspend fun getNoteEntity(id: Long) : NoteEntity?

    @Query("SELECT * FROM note")
    suspend fun getAllNoteEntities(): List<NoteEntity>

    @Delete
    suspend fun deleteNoteEntity(noteEntity: NoteEntity)
}