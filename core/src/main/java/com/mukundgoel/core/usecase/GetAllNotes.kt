package com.mukundgoel.core.usecase

import com.mukundgoel.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getAllNote();
}