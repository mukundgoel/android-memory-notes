package com.mukundgoel.core.usecase

import com.mukundgoel.core.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = noteRepository.getNote(id);
}