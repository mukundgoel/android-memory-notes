package com.mukundgoel.memorynotes.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mukundgoel.core.data.Note

@Entity(tableName = "Note")
data class NoteEntity(
    val title: String,
    val content: String,

    @ColumnInfo(name = "creation_date")
    val creationTime: Long,

    @ColumnInfo(name = "update_time")
    val updateTime: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) {
    companion object {
        fun fromNote(note: Note) = NoteEntity(note.title, note.content, note.creationTime, note.updateTime)
    }

    // this is outside of companion object since we need instance of NoteEntity to convert
    fun toNote() = Note(title, content, creationTime, updateTime, id)
}