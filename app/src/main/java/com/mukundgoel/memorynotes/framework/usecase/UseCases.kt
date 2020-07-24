package com.mukundgoel.memorynotes.framework.usecase

import com.mukundgoel.core.usecase.AddNote
import com.mukundgoel.core.usecase.GetAllNotes
import com.mukundgoel.core.usecase.GetNote
import com.mukundgoel.core.usecase.RemoveNote

// allows us to instantiate our Use Cases and allows them to be used in our view model
// allow us to inject our use cases where we will need them

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote
)