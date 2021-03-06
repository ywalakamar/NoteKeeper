package com.ywalakamar.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_list);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent=new Intent(NoteListActivity.this, NoteActivity.class);
            startActivity(intent);
        });

        initializeDisplayContent();
    }

    private void initializeDisplayContent(){
        /*use final to make variable accessible from an anonymous class*/
        final ListView listNotes=findViewById(R.id.list_notes);
        List<NoteInfo> notes=DataManager.getInstance().getNotes();

        /*create adapter*/
        ArrayAdapter<NoteInfo> notesAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);

        listNotes.setAdapter(notesAdapter);

        /*When a user makes a selection*/
        /*Create an onclick event that uses an anonymous class*/
        listNotes.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent=new Intent(NoteListActivity.this, NoteActivity.class);

            /*pass the selected item position as an Intent EXTRA*/
            intent.putExtra(NoteActivity.NOTE_POSITION, position);

            /*Launch activity*/
            startActivity(intent);
        });
    }
}