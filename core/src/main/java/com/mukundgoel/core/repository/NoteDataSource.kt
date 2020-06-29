package com.mukundgoel.core.repository

import com.mukundgoel.core.data.Note

// data source interface
// we dont need to know where we are getting that data, if someone implements data source we are good

interface NoteDataSource {
    // we use suspend here since we will be using coroutines to call these functions
    // input is a Note class that goes in the note variable
    suspend fun add(note: Note)

    // input is Long type id and function returns a nullable Note
    suspend fun get(id: Long): Note?

    // get all the nodes present in the data source
    suspend fun getAll(): List<Note>

    suspend fun remove(note: Note)
}