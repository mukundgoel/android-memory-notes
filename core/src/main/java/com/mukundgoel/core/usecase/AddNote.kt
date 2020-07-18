package com.mukundgoel.core.usecase

import com.mukundgoel.core.data.Note
import com.mukundgoel.core.repository.NoteRepository

// we want an operator since we can invoke this class as an operator on an object
class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note);


}