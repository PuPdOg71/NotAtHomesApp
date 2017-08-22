package com.TheAbstractLightbulb.cohen.not_at_homes_app;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;
import android.widget.ResourceCursorAdapter;

/**
 * Created by cohen on 25/06/2017.
 */

public class CustomCursorAdapter extends ResourceCursorAdapter {

    public CustomCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
        super(context, layout, cursor, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        EditText location = (EditText)view.findViewById(R.id.ListName2);
        location.setText(cursor.getString(cursor.getColumnIndex("Location")));

        EditText map_no = (EditText)view.findViewById(R.id.mapNoDisplay2);
        map_no.setText(cursor.getString(cursor.getColumnIndex("Map_no")));

        EditText date = (EditText)view.findViewById(R.id.dateDisplay2);
        date.setText(cursor.getString(cursor.getColumnIndex("Date")));

        EditText notathomes = (EditText)view.findViewById(R.id.textNotAtHomes2);
        notathomes.setText(cursor.getString(cursor.getColumnIndex("Not_at_homes")));
    }
}
