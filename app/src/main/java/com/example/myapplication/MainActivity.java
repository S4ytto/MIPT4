package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final int REQUEST_ADD_NOTE = 1;
    private ArrayList<String> notes;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Log message for debugging
        Log.d("MainActivity", "onCreate called");

        // Setting up the ListView to display notes
        ListView listView = findViewById(R.id.listViewNotes);
        notes = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(adapter);

        // Set up Add Note button
        Button buttonAddNote = findViewById(R.id.buttonAddNote);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, REQUEST_ADD_NOTE);
            }
        });

        // Set up Delete Note button
        Button buttonDeleteNote = findViewById(R.id.buttonDeleteNote);
        buttonDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!notes.isEmpty()) {
                    notes.remove(notes.size() - 1); // Remove the last note
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Last note deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No notes to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD_NOTE && resultCode == RESULT_OK) {
            String newNote = data.getStringExtra("newNote");
            if (newNote != null && !newNote.isEmpty()) {
                notes.add(newNote);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Note cannot be empty", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
