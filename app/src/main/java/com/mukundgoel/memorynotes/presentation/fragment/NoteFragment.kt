package com.mukundgoel.memorynotes.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.mukundgoel.core.data.Note
import com.mukundgoel.memorynotes.R
import com.mukundgoel.memorynotes.framework.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note.*

class NoteFragment : Fragment() {

    private lateinit var viewModel: NoteViewModel
    private val currentNote = Note("", "", 0L, 0L)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This is how we link the ViewModel to the current View
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        // here we want to go back to where we were previously
        // "it" refers to the checkButton view
        checkButton.setOnClickListener {
            val time = System.currentTimeMillis();
            if (titleView.text.toString() != "" || contentView.text.toString() != "") {
                currentNote.title = titleView.text.toString()
                currentNote.content = contentView.text.toString()
                currentNote.updateTime = time;

                // check if id = 0, this means this is a new note that we just created
                if (currentNote.id === 0L) {
                    currentNote.creationTime = time
                }

                viewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }

        observeViewModel();
    }

    private fun observeViewModel() {
        viewModel.saved.observe(this, Observer {
            if (it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                Navigation.findNavController(titleView).popBackStack()
            } else {
                Toast.makeText(context, "Something went wrong, please try again", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(titleView.windowToken, 0)
    }
}