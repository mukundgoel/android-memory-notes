package com.mukundgoel.memorynotes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mukundgoel.core.data.Note
import com.mukundgoel.memorynotes.R
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.SimpleDateFormat
import java.util.*

class NotesListAdapter(var notes: ArrayList<Note>): RecyclerView.Adapter<NotesListAdapter.NoteViewHolder>() {

    fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder = NoteViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
    )

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    // extends from RecyclerView.ViewHolder and takes a parameter called view
    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val layout = view.noteLayout
        private val noteTitle = view.title
        private val noteContent = view.content
        private val noteDate = view.date

        fun bind(note: Note) {
            noteTitle.text = note.title
            noteContent.text = note.content

            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
            val resultDate = Date(note.updateTime)
            noteDate.text = "Last updated: ${sdf.format(resultDate)}"
        }
    }
}