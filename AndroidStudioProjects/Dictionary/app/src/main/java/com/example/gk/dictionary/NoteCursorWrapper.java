package com.example.gk.dictionary;

import android.database.Cursor;
import android.database.CursorWrapper;

public class NoteCursorWrapper extends CursorWrapper {
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    // to get each data from each field

    /**
     *
     * @return
     */
    public Note getNote() {
        String genericName = getString(getColumnIndex(NoteSchema.NoteTable.Cols.GENERIC_NAME));
        String note = getString(getColumnIndex(NoteSchema.NoteTable.Cols.NOTE));


        Note note1 = new Note(genericName, note);

        return note1;
    }

}
