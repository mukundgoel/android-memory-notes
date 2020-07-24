package com.mukundgoel.memorynotes.framework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mukundgoel.core.data.Note
import com.mukundgoel.core.repository.NoteRepository
import com.mukundgoel.core.usecase.AddNote
import com.mukundgoel.core.usecase.GetAllNotes
import com.mukundgoel.core.usecase.GetNote
import com.mukundgoel.core.usecase.RemoveNote
import com.mukundgoel.memorynotes.framework.RoomNoteDataSource
import com.mukundgoel.memorynotes.framework.usecase.UseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val repository = NoteRepository(
        RoomNoteDataSource(
            application
        )
    );

    val useCases = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository)
    )

    // This is a live data alert Note Fragment when note has been saved in database
    val saved = MutableLiveData<Boolean>()

    fun saveNote(note: Note) {
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }
}