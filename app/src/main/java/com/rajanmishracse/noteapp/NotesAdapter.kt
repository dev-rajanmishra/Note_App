package com.rajanmishracse.noteapp

import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.rajanmishracse.noteapp.db.Note

class NotesAdapter(
    private  val listOfNotes:List<Note>,
    private val clickListener: ClickListener

):RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    class NotesViewHolder(
        itemView: View
    ):RecyclerView.ViewHolder(itemView){
        val texName:TextView = itemView.findViewById(R.id.text_title)
        val texCon:TextView = itemView.findViewById(R.id.text_des)
        val icon:ImageView  = itemView.findViewById(R.id.del_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_notes,parent,false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfNotes.size

        }
    interface ClickListener{
        fun updateNote(note: Note)
        fun delete(note: Note)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = listOfNotes[position]
        holder.texName.text = currentNote.noteName
        holder.texCon.text = currentNote.noteContent

        holder.itemView.setOnClickListener{
            clickListener.updateNote(currentNote)
        }
        holder.icon.setOnClickListener{
            clickListener.delete(currentNote)
        }


    }

}