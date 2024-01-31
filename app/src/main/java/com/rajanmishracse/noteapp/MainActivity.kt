package com.rajanmishracse.noteapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rajanmishracse.noteapp.db.Note
import com.rajanmishracse.noteapp.db.NoteDatabase

class MainActivity : AppCompatActivity(),NotesAdapter.ClickListener {

    private lateinit var repo: Repo
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var notesViewModelFactory: NotesViewModelFactory
    private lateinit var notesDatabase: NoteDatabase
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var rv:RecyclerView
    private lateinit var fab:FloatingActionButton
    private lateinit var dialog: Dialog
    private lateinit var edtTitle:EditText
    private lateinit var edtDes:EditText
    private lateinit var btnSave:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        notesViewModel.getAllNote().observe(this){
            notesAdapter = NotesAdapter(it,this)
            rv.adapter = notesAdapter
            rv.layoutManager = LinearLayoutManager(this)
        }
        fab.setOnClickListener(){
            openDialog(comingFromFAB = true)
        }


    }

    private fun openDialog(comingFromFAB:Boolean){
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.layout_dialog)

        edtTitle = dialog.findViewById(R.id.edt_title)
        edtDes = dialog.findViewById(R.id.edt_des)
        btnSave = dialog.findViewById(R.id.btn_save)

        btnSave.setOnClickListener{
            val note = Note(
                noteName = edtTitle.text.toString(),
                noteContent = edtDes.text.toString()

            )
            if (comingFromFAB){
                notesViewModel.insert(note)
            }else{
                notesViewModel.update(note)
            }

            dialog.dismiss()
        }
           dialog.show()
    }

    private fun init(){
        notesDatabase = NoteDatabase(this)
        repo=Repo(notesDatabase.getNoteDao())
        notesViewModelFactory= NotesViewModelFactory(repo)

        notesViewModel = ViewModelProvider(this,notesViewModelFactory).get(NotesViewModel::class.java)
        rv= findViewById(R.id.rv)
        fab = findViewById(R.id.fab)
    }

    override fun updateNote(note: Note) {
        //here logic for deleting note
          openDialog(comingFromFAB = false)
    }

    override fun delete(note: Note) {
        notesViewModel.delete(note)
    }

}