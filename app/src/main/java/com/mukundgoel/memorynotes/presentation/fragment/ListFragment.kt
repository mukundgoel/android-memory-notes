package com.mukundgoel.memorynotes.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mukundgoel.memorynotes.R
import com.mukundgoel.memorynotes.framework.viewmodel.ListViewModel
import com.mukundgoel.memorynotes.presentation.fragment.ListFragmentDirections
import com.mukundgoel.memorynotes.presentation.adapter.NotesListAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val noteListAdapter =
        NotesListAdapter(
            arrayListOf()
        )
    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesListView.apply {
            layoutManager = LinearLayoutManager(context) // we want linear list here
            adapter = noteListAdapter

        }

        addNote.setOnClickListener { goToNoteDetails() }

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        loadingView.visibility = View.VISIBLE
        notesListView.visibility = View.GONE
        viewModel.getAllNotes()
    }

    fun observeViewModel() {
        viewModel.notes.observe(this, Observer { notesList ->
            loadingView.visibility = View.GONE
            notesListView.visibility = View.VISIBLE
            noteListAdapter.updateNotes(notesList.sortedByDescending { it.updateTime })
        })
    }

    private fun goToNoteDetails(id: Long = 0L) {
        val action =
            ListFragmentDirections.actionGoToNote(
                id
            )
        Navigation.findNavController(notesListView).navigate(action)
    }
}