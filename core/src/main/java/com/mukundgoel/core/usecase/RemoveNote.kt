package com.mukundgoel.core.usecase

import com.mukundgoel.core.data.Note
import com.mukundgoel.core.repository.NoteRepository

class RemoveNote(private val noteRepository: NoteRepository) {
    suspend operator fun  invoke(note: Note) = noteRepository.removeNote(note);
}