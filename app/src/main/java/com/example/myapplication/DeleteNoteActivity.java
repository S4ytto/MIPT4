package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class DeleteNoteActivity extends Activity {

    private Spinner spinnerNotes;
    private Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        Log.d("DeleteNoteActivity", "onCreate called");

        spinnerNotes = findViewById(R.id.spinnerNotes);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gauna pasirinkto užrašo pavadinimą
                String selectedNote = spinnerNotes.getSelectedItem().toString();

                if (selectedNote.isEmpty()) {
                    Toast.makeText(DeleteNoteActivity.this, "No note selected", Toast.LENGTH_SHORT).show();
                } else {
                    // Čia ištrink užrašą
                    Log.d("DeleteNoteActivity", "Note deleted: " + selectedNote);
                    finish();
                }
            }
        });
    }
}
